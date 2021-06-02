package pl.tul.emailsemotions.userservice.utils;

import org.springframework.stereotype.Component;

@Component("utilities")
public class Utilities {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9-_]+(\\.[a-zA-Z0-9-_]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$";
}
