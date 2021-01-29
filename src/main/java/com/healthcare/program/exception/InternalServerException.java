package com.healthcare.program.exception;
public class InternalServerException extends CommonException {

	private static final long serialVersionUID = 4459310379862673537L;

	public InternalServerException() {
		super();
	}

	public InternalServerException(String detail) {
		super(detail);
	}
	
	public InternalServerException(String detail, Throwable cause) {
		super(detail, cause);
	}

	public InternalServerException(String code, String message, String detail, String moreInfo) {
		super(detail);
		super.setCode(code);
		super.setMessage(message);
		super.setDetail(detail);
		super.setMoreInfo(moreInfo);
	}
}