package pl.tul.emailsemotions.emailservice.mail;

import pl.tul.emailsemotions.emailservice.domain.MailObject;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
    boolean verifyMailMinimalMap(MailObject mailObject);
}
