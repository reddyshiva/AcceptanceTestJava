package com.healthcare.program.exception;
import java.util.List;

public class BadRequestException extends CommonException {

    private static final long serialVersionUID = -8068412424122586890L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String detail) {
        super(detail);
    }

    public BadRequestException(String detail, Throwable cause) {
        super(detail, cause);
    }

    public BadRequestException(String code, String message, String detail, String moreInfo) {
        super(detail);
        super.setCode(code);
        super.setMessage(message);
        super.setDetail(detail);
        super.setMoreInfo(moreInfo);
    }

    public BadRequestException(String code, String message, List<String> details, String moreInfo) {
        super(details);
        super.setCode(code);
        super.setMessage(message);
        super.setDetails(details);
        super.setMoreInfo(moreInfo);
    }
}
