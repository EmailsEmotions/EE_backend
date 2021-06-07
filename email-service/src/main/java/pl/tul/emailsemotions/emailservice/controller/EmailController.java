package pl.tul.emailsemotions.emailservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pl.tul.emailsemotions.emailservice.mail.EmailSender;
import pl.tul.emailsemotions.emailservice.domain.MailObject;

@RestController
@AllArgsConstructor
@Slf4j
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @PostMapping("/sendMail")
    public String send(@RequestBody MailObject mailObject) {
        if (!emailSender.verifyMailMinimalMap(mailObject)) {
            String msg = "Cannot sent mail, critical information is missing";
            log.error(msg);
            return msg;
        }
        String body = getMinimalMail(mailObject);
        emailSender.sendEmail(mailObject.getRecipient(), mailObject.getSubject(), body);
        String msg = "Mail to user "+mailObject.getRecipient()+ " has been sent";
        log.info(msg);
        return msg;
    }

    @PostMapping("/previewMailHtml")
    public String showHtml(@RequestBody MailObject mailObject) {
        if (!emailSender.verifyMailMinimalMap(mailObject)) {
            String msg = "Cannot preview mail, critical information is missing";
            log.error(msg);
            return msg;
        }
        String body = getMinimalMail(mailObject);
        return body;
    }

    private String getMinimalMail(@RequestBody MailObject mailObject) {
        Context context = new Context();
        context.setVariable("header", mailObject.getTemplateMap().get("header"));
        context.setVariable("title", mailObject.getTemplateMap().get("title"));
        context.setVariable("description", mailObject.getTemplateMap().get("description"));
        return templateEngine.process("template", context);
    }
}
