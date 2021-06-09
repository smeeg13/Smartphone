package Smartphone.Errors;

public class BusinessException extends Exception{


    private ErrorCodes errorCode;

    public BusinessException(String msg, ErrorCodes errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public BusinessException(String msg, Throwable cause, ErrorCodes errorCode) {
        super(msg, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode.getCode();
    }


}

