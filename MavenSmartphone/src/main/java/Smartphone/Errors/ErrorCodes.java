package Smartphone.Errors;

/**
 * This class provides all Error Codes that can be used.
 * @author Mégane Solliard
 * @version
 */
public enum ErrorCodes {

    CONTACT_ALREADY_EXIST_ERROR(200),
    CONTACT_INFORMATIONS_EMPTY(202),
    CONTACT_NOT_FOUND(203),
    IO_ERROR(301),
    REQUEST_FAIL(401),
    TERMINALCOMMAND_ERROR(402),
    BAD_PARAMETER(403);

    private final int code;
    /**
     * This constructor set the error Code .
     * @param code – the code used
     */
    ErrorCodes(int code) {
        this.code = code;
    }

    /**
     * This Method get the error Code.
     * @return - the code used
     */
    public int getCode() {
        return code;
    }

}
