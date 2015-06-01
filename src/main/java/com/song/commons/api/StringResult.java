package com.song.commons.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StringResult extends Result {

	private static final long serialVersionUID = 4383693770761101276L;

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
