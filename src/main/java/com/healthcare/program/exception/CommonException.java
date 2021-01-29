package com.healthcare.program.exception;
import java.util.List;

public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private String detail;
    private String moreInfo;
    
    private List<String> details;

    public CommonException() {
        super();
    }

    public CommonException(String detail) {
        super(detail);
        this.setDetail(detail);
    }

    public CommonException(String detail, Throwable cause) {
        super(detail, cause);
        this.setDetail(detail);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getLocalizedMessage() {
        return detail;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return detail;
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public CommonException(List<String> details) {
        this.setDetails(details);
    }

    public CommonException(List<String> details, Throwable cause) {
        super(details.get(0), cause);
        this.setDetails(details);
    }
}