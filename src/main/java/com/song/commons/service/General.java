package com.song.commons.service;

/**
 * 通用错误码（与具体的业务无关）
 * 
 * @author songzigw
 *
 */
public enum General implements ErrorInfo {
	/** 未知异常 */
	GEN_001("GEN_001"),
	/** 参数输入有误 */
	GEN_002("GEN_002"),
	/** HTTP请求异常 */
	GEN_003("GEN_003"),
	/** 网络连接异常 */
	GEN_004("GEN_004");

	private final String errCode;

	public String getErrCode() {
		return errCode;
	}

	private General(String errCode) {
		this.errCode = errCode;
	}

	public General getInstance(String errCode) {
		for (General g : General.values()) {
			if (g.getErrCode().equals(errCode)) {
				return g;
			}
		}
		return null;
	}

}
