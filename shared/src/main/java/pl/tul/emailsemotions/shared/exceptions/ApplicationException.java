package pl.tul.emailsemotions.shared.exceptions;

public abstract class ApplicationException extends RuntimeException {
    private final String code;

    ApplicationException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
