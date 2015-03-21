package com.song.commons.api;

import java.io.Serializable;

/**
 * TOPAPI基础响应信息。
 * 
 * @author 张松
 */
public abstract class Result implements Serializable {

	private static final long serialVersionUID = 6785550014828467801L;

	private String errCode;
	
	private String errDesc;
	
	private String errNotice;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public String getErrNotice() {
		if (errNotice == null) {
			errNotice = errDesc;
		}
		return errNotice;
	}

	public void setErrNotice(String errNotice) {
		this.errNotice = errNotice;
	}
	
}
