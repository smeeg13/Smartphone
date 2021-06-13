package Smartphone.Errors;

/**
 * This class provides all ErrorCode that can be used for the BusinessExceptions,
 * @author Mégane Solliard
 * @version
 */
public enum ErrorCodes {

    CONTACT_ALREADY_EXIST_ERROR(200),
    CONTACT_INFORMATIONS_EMPTY(202),
    IO_ERROR(301),
    REQUEST_FAIL(401),
    TERMINALCOMMAND_ERROR(402);

    private final int code;
    /**
     * This constructor set the error Code .
     * @param code – the code used
     */
    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
