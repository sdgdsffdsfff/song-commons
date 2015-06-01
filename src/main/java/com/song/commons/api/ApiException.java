package com.song.commons.api;

public class ApiException extends Exception {

	private static final long serialVersionUID = 9060028080362777992L;

	private String errCode;

	private String errDesc;

	private String errNotice;

	public ApiException(String errCode, String errDesc) {
		super(errCode + ":" + errDesc);
		this.errCode = errCode;
		this.errDesc = errDesc;
		this.errNotice = errDesc;
	}

	public ApiException(String errCode, String errDesc, String errNotice) {
		this(errCode, errDesc);
		this.errNotice = errNotice;
	}

	public ApiException(String errCode, String errDesc, Throwable e) {
		super(errCode + ":" + errDesc, e);
		this.errCode = errCode;
		this.errDesc = errDesc;
		this.errNotice = errDesc;
	}

	public ApiException(String errCode, String errDesc, String errNotice,
			Throwable e) {
		this(errCode, errDesc, e);
		this.errNotice = errNotice;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public String getErrNotice() {
		return errNotice;
	}

}
