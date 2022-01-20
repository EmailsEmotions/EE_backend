package pl.tul.emailsemotions.shared.exceptions;

public class IllegalActionException extends ApplicationException {
    private static final String CODE = "illegal_action";

    public IllegalActionException(String msg) {
        super(CODE, msg);
    }
}
