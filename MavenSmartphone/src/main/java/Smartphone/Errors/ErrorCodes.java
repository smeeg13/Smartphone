package Smartphone.Errors;

public enum ErrorCodes {


    CONTACT_ALREADY_EXIST_ERROR(200),
    CONTACT_INFORMATIONS_EMPTY(202),
    IO_ERROR(301),
    REQUEST_FAIL(401),
    TERMINALCOMMAND_ERROR(402);




    private final int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
