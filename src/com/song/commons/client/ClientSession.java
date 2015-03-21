package com.song.commons.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ClientSession {

	/** 客户端唯一标示符 */
	public static final String CLIENT_KEY = "client_key";
	
	private String id;
	
	private Map<String, Object> attribute;

	private Date creationTime;
	
	private Date lastAccessedTime;
	
	public ClientSession(String sessionId) {
		this.id = sessionId;
		creationTime = new Date();
		lastAccessedTime = creationTime;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getAttribute(String name) {
		if (attribute == null) {
			return null;
		}
		return attribute.get(name);
	}

	public void setAttribute(String name, Object value) {
		if (attribute == null) {
			attribute = new HashMap<String, Object>();
		}
		attribute.put(name, value);
	}
	
	public long getCreationTime() {
		return creationTime.getTime();
	}
	
	public long getLastAccessedTime() {
		return lastAccessedTime.getTime();
	}
	
	public void updateLastAccessedTime() {
		lastAccessedTime = new Date();
	}
	
}
