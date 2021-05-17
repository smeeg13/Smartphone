package Smartphone.Errors;

public enum ErrorCodes {


    CONTACT_ALREADY_EXIST_ERROR(200);




    private final int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
