package services;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {
    private static final Logger LOGGER = Logger.getLogger(EmailService.class.getName());

    public static void sendEmail(String recipient, String subject, String content) {
        final String fromEmail = System.getenv("EMAIL_USERNAME"); // Retrieve from environment variables
        final String password = System.getenv("EMAIL_PASSWORD"); // Retrieve from environment variables
        if (fromEmail == null || password == null) {
            throw new IllegalStateException("Email credentials are not set in the environment variables.");
        }
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);

            // Check if INFO level logging is enabled before logging
            if (LOGGER.isLoggable(Level.INFO)) {
                LOGGER.info(String.format("Email sent successfully to %s", recipient));
            }

        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, "Failed to send email", e);
        }
    }
}
