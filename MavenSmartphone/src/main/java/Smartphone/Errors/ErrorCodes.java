package Smartphone.Errors;

public enum ErrorCodes {


    CONTACT_ALREADY_EXIST_ERROR(200),
    CONTACTLIST_FULL(201),
    IO_ERROR(301);




    private final int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
