package pl.tul.emailsemotions.exceptions;

public class WrongArgumentException extends ApplicationException {
    private static final String CODE = "wrong_argument";

    public WrongArgumentException(String msg) {
        super(CODE, msg);
    }
}
