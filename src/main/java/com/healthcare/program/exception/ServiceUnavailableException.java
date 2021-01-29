package com.healthcare.program.exception;
public class ServiceUnavailableException extends CommonException {

	private static final long serialVersionUID = 4459310379862673537L;

	public ServiceUnavailableException() {
		super();
	}

	public ServiceUnavailableException(String detail) {
		super(detail);
	}
	
	public ServiceUnavailableException(String detail, Throwable cause) {
		super(detail, cause);
	}

	public ServiceUnavailableException(String code, String message, String detail, String moreInfo) {
		super(detail);
		super.setCode(code);
		super.setMessage(message);
		super.setDetail(detail);
		super.setMoreInfo(moreInfo);
	}
}