package pl.tul.emailsemotions.shared.exceptions;

import org.apache.commons.lang3.StringUtils;

public class ObjectNotFoundException extends ApplicationException {
    private static final String CODE = "object_not_found";

    public ObjectNotFoundException() {
        super(CODE, StringUtils.EMPTY);
    }

    public ObjectNotFoundException(String msg) {
        super(CODE, msg);
    }
}
