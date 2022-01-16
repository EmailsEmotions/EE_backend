package pl.tul.emailsemotions.exceptions;

public class ValidationException extends ApplicationException {
    private static final String CODE = "validation";

    public ValidationException(String msg) {
        super(CODE, msg);
    }
}
