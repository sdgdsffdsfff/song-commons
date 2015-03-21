package com.song.commons.api.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class GsonUtil {

	private static Gson gson = new Gson();

	public static String toJson(Object obj, Type type) {	
		return gson.toJson(obj, type);
	}

	public static Object fromJson(String str,Type type){
		return gson.fromJson(str, type);
	}

/*	public static void main(String[] args) {
		User u = new User();
		u.setErrCode("dddd");
		u.setErrDesc("errDesc");
		u.setErrNotice("errNotice");
		String a = "{\"errCode\":\"dddd\",\"errDesc\":\"errDesc\",\"errNotice\":\"errNotice\"}";
		
		System.out.println(fromJson(a, User.class));
	}
*/}  
