package com.wings.intentbased.Intent.yamlFiles;

import java.util.Map;

public class AGVYaml {
	
	private String name;
	private String description;
	private String version;
	private String identity;
	private Map<String, Object>[] parameters;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public Map<String, Object>[] getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, Object>[] parameters) {
		this.parameters = parameters;
	}
	
}
