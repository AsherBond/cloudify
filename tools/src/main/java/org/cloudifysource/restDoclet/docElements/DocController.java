package org.cloudifysource.restDoclet.docElements;

import java.util.Map.Entry;
import java.util.SortedMap;

public class DocController {
	private String name;
	private String uri;
	private String description;
	private SortedMap<String, DocMethod> methods;

	public DocController(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SortedMap<String, DocMethod> getMethods() {
		return methods;
	}

	public void setMethods(SortedMap<String, DocMethod> methods) {
		this.methods = methods;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Controller " + name + " uri = " + uri + "\n");
		if (description != null && !description.isEmpty())
			builder.append(description + "\n");		
		if (methods != null) {
			for (Entry<String, DocMethod> entry : methods.entrySet()) {
				builder.append(entry.getValue().toString() + "\n");
			}
		}
		return builder.toString();
	}

}
