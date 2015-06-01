package com.song.commons.client;

import java.util.HashMap;
import java.util.Map;

public class ClientManager {

	/** 本类存在的唯一实例 */
	private static ClientManager instance = new ClientManager();

	private Map<String, ClientSession> csMap;

	private ClientManager() {
		csMap = new HashMap<String, ClientSession>();
	}

	public static ClientManager getInstance() {
		return instance;
	}

	public void addSession(ClientSession cs) {
		csMap.put(cs.getId(), cs);
	}

	public void delSession(String sessionId) {
		csMap.remove(sessionId);
	}

	public ClientSession getSession(String sessionId) {
		if (sessionId == null) {
			return null;
		}
		ClientSession cs = csMap.get(sessionId);
		if (cs != null) {
			cs.updateLastAccessedTime();
		}
		return cs;
	}
}
