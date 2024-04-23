package com.apigee.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections.CollectionUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;


public class OpsMap {

	private static final Logger LOGGER = Logger.getLogger(OpsMap.class.getName());

	static {
		LOGGER.setLevel(Level.INFO);
		ConsoleHandler handler = new ConsoleHandler();
		// PUBLISH this level
		handler.setLevel(Level.INFO);
		LOGGER.addHandler(handler);
	}

	private List<Operation> opsMap = new ArrayList<Operation>();
	private String defaultOps = "GET";

	//TODO
	private List<String> DEF_GET_STEPS  = Arrays.asList();
	private List<String> DEF_POST_STEPS = Arrays.asList();

	public OpsMap(String defaultOps) {
		this.defaultOps = defaultOps;
	}
	
	public OpsMap(String OPSMAPPING_TEMPLATE, String defaultOps) throws Exception {

		this.defaultOps = defaultOps;
		readOperationsMap(OPSMAPPING_TEMPLATE);
	}

	private void addOps (String operationName, String location, String verb, List<String> steps) {
		Operation o = new Operation(operationName, location, verb, steps);
		opsMap.add(o);
	}
	
	public String getVerb(String operationName, HashMap<String, SelectedOperation> selectedOperations) {
		String lcOperationName = operationName.toLowerCase();
		
		if (selectedOperations != null) {
			if (selectedOperations.containsKey(operationName)) {
				return selectedOperations.get(operationName).getVerb();
			}
		}
		
		for (Operation o : opsMap) {
			//found key in the operation name
			if (lcOperationName.contains(o.getPattern())) {
				if (o.getLocation().equalsIgnoreCase("beginsWith")) {
					if (lcOperationName.startsWith(o.getPattern())) {
						return o.getVerb();
					}
				} else if (o.getLocation().equalsIgnoreCase("endsWith")) {
					if (lcOperationName.endsWith(o.getPattern())) {
						return o.getVerb();
					}
				} else { //assume contains
					return o.getVerb();
				}
			}
		}
		return defaultOps;
	}
	
	public String getResourcePath (String operationName, HashMap<String, SelectedOperation> selectedOperations) {

		String resourcePath = operationName;
		
		if (selectedOperations != null) {
			if (selectedOperations.containsKey(operationName)) {
				return selectedOperations.get(operationName).getResourcePath();
			}
		}		
		
		for (Operation o : opsMap) {
			if (operationName.toLowerCase().startsWith(o.getPattern()) && !operationName.toLowerCase().startsWith("address") 
					&& !operationName.equalsIgnoreCase(o.getPattern())) { //don't replace the entire resource
				resourcePath = operationName.toLowerCase().replaceFirst(o.getPattern(), "");
				LOGGER.fine("Replacing " + operationName + " with " + resourcePath.toLowerCase());
				return "/" + resourcePath.toLowerCase();
			}
		}
		return "/" + resourcePath.toLowerCase();
	}
	
	private void readOperation (String verb, Document opsMappingXML) throws Exception {
		LOGGER.entering(OpsMap.class.getName(), new Object() {
		}.getClass().getEnclosingMethod().getName());

		Node verbNode = null;
		NodeList elements = opsMappingXML.getElementsByTagName(verb);
		if (elements != null) {
			verbNode = elements.item(0);
		}
		if (verbNode != null) {
			NodeList getOpsList = verbNode.getChildNodes();
			for (int i = 0; i < getOpsList.getLength(); i++) {
				if (getOpsList.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element operation = (Element)getOpsList.item(i);
					String name = operation.getElementsByTagName("pattern").item(0).getTextContent();
					String location = operation.getElementsByTagName("pattern").item(0).getTextContent();

					List<String> list = new ArrayList<>();
					NodeList steps = operation.getElementsByTagName("step");
					for (int j = 0; j < steps.getLength(); j++) {
						String step = steps.item(i).getTextContent();
						list.add(step);
					}
					if ( list.isEmpty() ){
						list = "GET".equalsIgnoreCase(verbNode.getTextContent()) ? DEF_GET_STEPS : DEF_POST_STEPS;
					}
					addOps (name, location, verb.toUpperCase(), list);
				}
			}
		}
		LOGGER.exiting(OpsMap.class.getName(), new Object() {
		}.getClass().getEnclosingMethod().getName());
	}
	
	public void readOperationsMap(String OPSMAPPING_TEMPLATE) throws Exception {
		
		LOGGER.entering(OpsMap.class.getName(), new Object() {
		}.getClass().getEnclosingMethod().getName());

		XMLUtils xmlUtils = new XMLUtils();
		Document opsMappingXML = null; 
		String[] verbs = {"get", "post", "put", "delete"};
		
		try {
			//first try to read it as a file containing xml
			opsMappingXML = xmlUtils.readXML(OPSMAPPING_TEMPLATE);
	    }
		catch (SAXParseException e){
			LOGGER.severe( e.getMessage() );
		}
		catch (Exception e) {
			//second try to read it as a string containing xml
			try {
				opsMappingXML = xmlUtils.getXMLFromString(OPSMAPPING_TEMPLATE);
			} catch (Exception ex) {
				//third try to read it as a string containing json representation of xml
//				opsMappingXML = xmlUtils.getXMLFromJSONString(OPSMAPPING_TEMPLATE);
			}
		}

					
		for (String v : verbs) {
			readOperation(v, opsMappingXML);
		}

		LOGGER.exiting(OpsMap.class.getName(), new Object() {
		}.getClass().getEnclosingMethod().getName());

	}
	
}
