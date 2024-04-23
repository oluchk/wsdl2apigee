package com.apigee.utils;

import java.util.List;

public class Operation {

	private String verb;
	private String pattern;
	private String location;
	private List<String> steps;

	/**
	 * @return the verb
	 */
	public String getVerb() {
		return verb;
	}

	/**
	 * @return the name
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	public List<String> getSteps() {
		return steps;
	}

	Operation(String pattern, String location, String verb, List<String> steps) {
		this.pattern = pattern;
		this.verb = verb;
		this.location = location;
		this.steps = steps;
	}
}
