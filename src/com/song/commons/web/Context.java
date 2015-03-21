package com.song.commons.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Context {

	private static ApplicationContext context;

	private Context() {
	}

	public static ApplicationContext getContext(HttpServletRequest req) {
		if (context == null)
			context = WebApplicationContextUtils
					.getRequiredWebApplicationContext(req.getSession()
							.getServletContext());
		return context;
	}

	public static ApplicationContext getContext(ServletContext sc) {
		if (context == null)
			context = WebApplicationContextUtils
					.getRequiredWebApplicationContext(sc);
		return context;
	}
}
