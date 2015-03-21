package com.song.commons.service;


/**
 * 统一身份认证服务
 * 
 * @author songzigw
 *
 * @param <A>
 */
public interface SSOAuthCallback<A> {

	/**
	 * 创建一个客户端SESSION
	 * 
	 * @return
	 */
	public String createClientSession();

	/**
	 * 判断客户端SESSION是否存在于服务端
	 * 
	 * @param sessionId
	 * @return
	 */
	public boolean isClientSessionId(String sessionId);

	/**
	 * 获取用户身份信息
	 * 
	 * @param sessionId
	 * @return
	 */
	public A getAuth(String sessionId);

	/**
	 * 登入成功后，SESSION中保存在线用户身份信息
	 * 
	 * @param account
	 * @param password
	 * @param sessionId
	 * @return
	 */
	public String login(String account, String password, String sessionId);

	/**
	 * 退出登入
	 * 
	 * @param sessionId
	 */
	public void logout(String sessionId);
}
