package adopet.api.provider;

import adopet.api.exceptions.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailProvider implements IEmailProvider {
    private static final Logger logger = LoggerFactory.getLogger(EmailProvider.class);
    private final JavaMailSender emailSender;
    @Value("${email.default.from}")
    private String defaultFrom;

    public EmailProvider(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void send(String from, String to, String subject, String body) {
        try {
            if (from == null || from.isEmpty()) {
                from = defaultFrom;
            }
            validateEmailParams(to, subject, body);

            SimpleMailMessage email = createEmailMessage(from, to, subject, body);
            emailSender.send(email);

            logger.info("Email sent successfully from {} to {}", from, to);
        } catch (Exception e) {
            logger.error("Failed to send email from {} to {}", from, to, e);
            throw new EmailException("Failed to send email", e);
        }
    }

    private SimpleMailMessage createEmailMessage(String from, String to, String subject, String body) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject(subject);
        email.setText(body);
        return email;
    }

    private void validateEmailParams(String to, String subject, String body) {
        if (to == null || to.isEmpty()) {
            throw new IllegalArgumentException("Recipient email address cannot be null or empty");
        }
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Email subject cannot be null or empty");
        }
        if (body == null || body.isEmpty()) {
            throw new IllegalArgumentException("Email body cannot be null or empty");
        }
    }
}
