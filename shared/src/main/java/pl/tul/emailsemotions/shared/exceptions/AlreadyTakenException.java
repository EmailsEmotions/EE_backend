package pl.tul.emailsemotions.shared.exceptions;

public class AlreadyTakenException extends ApplicationException {
    private static final String CODE = "already_taken";

    public AlreadyTakenException(String msg) {
        super(CODE, msg);
    }
}
