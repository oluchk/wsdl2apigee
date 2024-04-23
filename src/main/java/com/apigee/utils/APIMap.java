package com.apigee.utils;

public class APIMap {

	private String jsonBody;
	private String soapBody;
	private String resourcePath;
	private String verb;
	private String soapAction;
	private String rootElement;
	private boolean otherNamespaces;
	
	public APIMap(String jsonBody, String soapBody, String resourcePath,
				  String verb, String rootElement, boolean otherNamespace) {
		this.jsonBody = jsonBody;
		this.soapBody = soapBody;
		this.resourcePath = resourcePath;
		this.verb = verb;
		this.rootElement = rootElement;
		this.otherNamespaces = otherNamespace;
	}

	public String getJsonBody() {
		return jsonBody;
	}
	
	public void setJsonBody(String jb) {
		jsonBody = jb;
	}
	
	public String getSoapBody() {
		return soapBody;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public String getVerb() {
		return verb;
	}
	
	public String getSoapAction() {
		return soapAction;
	}

	public void setSoapAction(String s) {
		soapAction = s;
	}
	
	public boolean getOthernamespaces() {
		return otherNamespaces;
	}
	
	public String getRootElement () {
		return rootElement;
	}
}
