package com.healthcare.program.exception;

public class NotFoundException extends CommonException {

	private static final long serialVersionUID = 4459310379862673537L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String detail) {
		super(detail);
	}
	
	public NotFoundException(String detail, Throwable cause) {
		super(detail, cause);
	}

	public NotFoundException(String code, String message, String detail, String moreInfo) {
		super(detail);
		super.setCode(code);
		super.setMessage(message);
		super.setDetail(detail);
		super.setMoreInfo(moreInfo);
	}
}