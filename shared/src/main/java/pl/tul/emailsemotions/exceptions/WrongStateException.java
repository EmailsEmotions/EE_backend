package pl.tul.emailsemotions.exceptions;

public class WrongStateException extends ApplicationException {

    private static final String CODE = "wrong_state";

    public WrongStateException(String msg) {
        super(CODE, msg);
    }
}
