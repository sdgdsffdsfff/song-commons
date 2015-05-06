package com.song.commons.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.song.commons.client.ClientSession;
import com.song.commons.service.SSOAuthCallback;
import com.song.commons.service.ServiceException;

/**
 * 统一身份认证
 * 
 * @author songzigw
 *
 */
public class SSOAuth<A> {

	private HttpServletRequest request;

	private HttpServletResponse response;

	private SSOAuthCallback<A> authCallback;

	public SSOAuth(HttpServletRequest request, HttpServletResponse response,
			SSOAuthCallback<A> acb) {
		this.request = request;
		this.response = response;
		this.authCallback = acb;
	}

	/**
	 * 获取客户端唯一Id
	 * 
	 * @return
	 */
	public String getSessionId() {
		Cookie c = CookieUtil
				.getCookieByName(request, ClientSession.CLIENT_KEY);
		if (c == null) {
			return request.getParameter("sessionId");
		}
		return c.getValue();
	}

	/**
	 * 获取当前在线用户身份信息
	 * 
	 * @return
	 * @throws Exception 
	 */
	public A getCurrAuth() throws ServiceException {
		String sessionId = this.getSessionId();
		if (sessionId == null) {
			return null;
		}
		return authCallback.getAuth(sessionId);
	}

	/**
	 * 创建客户端标示
	 * @param isCreate 是否真的创建
	 * @throws Exception 
	 */
//	public void createSession(boolean isCreate) {
//		String sessionId = this.getSessionId();
//		if (StringUtil.isEmptyOrNull(sessionId) && isCreate) {
//			sessionId = authCallback.createClientSession();
//			CookieUtil.addCookie(response, ClientSession.CLIENT_KEY, sessionId,
//					0);
//		} else {
//			authCallback.isClientSessionId(sessionId);
//		}
//	}

	/**
	 * 更新SESSION的最近访问时间
	 */
	public void updateSessionTime() {
		String sessionId = this.getSessionId();
		authCallback.isClientSessionId(sessionId);
	}

	/**
	 * 登入
	 * 
	 * @param account
	 * @param password
	 * @return 返回null登入失败，反侧登入失败
	 * @throws Exception 
	 */
	public A login(String account, String password) {
		String sessionId = this.getSessionId();
		sessionId = authCallback.login(account, password, sessionId);
		CookieUtil.addCookie(response, ClientSession.CLIENT_KEY, sessionId, 0);
		return authCallback.getAuth(sessionId);
	}

	/**
	 * 退出
	 * @throws Exception 
	 */
	public void logout() {
		String sessionId = this.getSessionId();
		authCallback.logout(sessionId);
		CookieUtil.removeCookie(response, sessionId);
	}

}
