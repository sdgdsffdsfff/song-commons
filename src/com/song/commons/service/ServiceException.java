package com.song.commons.service;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 7792046967019579772L;

	private String errDesc;

	private String errNotice;

	private ErrorInfo errInfo;

	public ServiceException(ErrorInfo errInfo, String errDesc) {
		super(errInfo.getErrCode() + ":" + errDesc);
		this.errInfo = errInfo;
		this.errDesc = errDesc;
		this.errNotice = errDesc;
	}

	public ServiceException(ErrorInfo errInfo, String errDesc, String errNotice) {
		this(errInfo, errDesc);
		this.errNotice = errNotice;
	}

	public ServiceException(ErrorInfo errInfo, String errDesc, Throwable e) {
		super(errInfo.getErrCode() + ":" + errDesc, e);
		this.errInfo = errInfo;
		this.errDesc = errDesc;
		this.errNotice = errDesc;
	}

	public ServiceException(ErrorInfo errInfo, String errDesc,
			String errNotice, Throwable e) {
		this(errInfo, errDesc, e);
		this.errNotice = errNotice;
	}

	public String getErrCode() {
		return errInfo.getErrCode();
	}

	public ErrorInfo getErrInfo() {
		return errInfo;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public String getErrNotice() {
		return errNotice;
	}

}
