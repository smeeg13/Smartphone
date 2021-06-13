package Smartphone.Errors;

/**
 * This class provides contructors for all the BusinessExceptions,
 * @author Mégane Solliard
 * @version
 */
public class BusinessException extends Exception{


    private ErrorCodes errorCode;
    /**
     * This constructor set the gallery display on another class (by a panel).
     * @param msg – the error message that should be displayed
     * @param errorCode – the Error Code linked to this message
     */
    public BusinessException(String msg, ErrorCodes errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
    /**
     * This constructor set the gallery display on another class (by a panel).
     * @param msg – the error message that should be displayed
     * @param cause – the CardLayout needed for {@code showAllPicture()}
     * @param errorCode – the Picture needed for {@code showAllPicture()}
     */
    public BusinessException(String msg, Throwable cause, ErrorCodes errorCode) {
        super(msg, cause);
        this.errorCode = errorCode;
    }
    /**
     * This constructor set the gallery display on another class (by a panel).
     * @param msg – the error message that should be displayed
     */
    public BusinessException(String msg){
        super(msg);
    }

    public int getErrorCode() {
        return errorCode.getCode();
    }


}

