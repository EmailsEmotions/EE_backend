package pl.tul.emailsemotions.emailservice.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.tul.emailsemotions.emailservice.domain.MailObject;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderImpl implements EmailSender {

    private final String NO_REPLY_ADDRESS = "emailsemotions@gmail.com";
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String to, String title, String content) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setReplyTo(NO_REPLY_ADDRESS);
            helper.setFrom(NO_REPLY_ADDRESS);
            helper.setSubject(title);
            helper.setText(content, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }

    @Override
    public boolean verifyMailMinimalMap(MailObject mailObject) {
        boolean status = true;
        if (!mailObject.getTemplateMap().containsKey("header")) {
            status = false;
        }
        if (!mailObject.getTemplateMap().containsKey("title")) {
            status = false;
        };
        if (!mailObject.getTemplateMap().containsKey("description")) {
            status = false;
        };
        if (mailObject.getRecipient().isEmpty()) {
            status = false;
        };
        if (mailObject.getSubject().isEmpty()) {
            status = false;
        };
        return status;
    }
}
