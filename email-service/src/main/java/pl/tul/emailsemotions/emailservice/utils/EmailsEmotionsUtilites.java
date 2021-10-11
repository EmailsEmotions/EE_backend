package pl.tul.emailsemotions.emailservice.utils;

import org.springframework.stereotype.Component;

@Component("EmailsEmotionsUtilites")
public class EmailsEmotionsUtilites {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9-_]+(\\.[a-zA-Z0-9-_]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$";
}
