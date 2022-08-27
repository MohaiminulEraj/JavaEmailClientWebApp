package mail;

import java.nio.file.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtility {
    private static Properties props = new Properties(); // SMTP Properties
    // Peoperties of SMTP Server
    static {
        props.put("mail.smtp.host", "smtp.gmail.com");
        // props.put("mail.smtp.port", "465");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
    }

    public static void sendMail(EmailMessage emailMessageDTO) {
        // Authentication parameters
        try {
            Path filename = Path.of("Your_Password_Directory");
            String pass = Files.readString(filename);
            String sender_email = "your_email@gmail.com";
            String sender_email_pass = pass;
            // Connection to Mail Server to get the session object..
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender_email, sender_email_pass);
                }
            });
            session.setDebug(true);
            // Create message
            Message msg = new MimeMessage(session);
            InternetAddress addressFrom = new InternetAddress(sender_email);
            msg.setFrom(addressFrom);
            // Set To addresses
            String[] emailIds = new String[0];
            if (emailMessageDTO.getTo() != null) {
                emailIds = emailMessageDTO.getTo().split(",");
            }
            // Set CC addresses
            String[] emailIdsCc = new String[0];
            if (emailMessageDTO.getCc() != null) {
                emailIdsCc = emailMessageDTO.getCc().split(",");
            }
            String[] emailIdsBcc = new String[0];

            if (emailMessageDTO.getBcc() != null) {
                emailIdsBcc = emailMessageDTO.getBcc().split(",");
            }
            // To addresses
            InternetAddress[] addressTo = new InternetAddress[emailIds.length];
            for (int i = 0; i < emailIds.length; i++) {
                addressTo[i] = new InternetAddress(emailIds[i]);
            }
            // CC addresses
            InternetAddress[] addressCc = new InternetAddress[emailIdsCc.length];
            for (int i = 0; i < emailIdsCc.length; i++) {
                addressCc[i] = new InternetAddress(emailIdsCc[i]);
            }
            // BCC addresses
            InternetAddress[] addressBcc = new InternetAddress[emailIdsBcc.length];
            for (int i = 0; i < emailIdsBcc.length; i++) {
                addressBcc[i] = new InternetAddress(emailIdsBcc[i]);
            }

            if (addressTo.length > 0) {
                msg.setRecipients(Message.RecipientType.TO, addressTo);
            }

            if (addressCc.length > 0) {
                msg.setRecipients(Message.RecipientType.CC, addressCc);
            }

            if (addressBcc.length > 0) {
                msg.setRecipients(Message.RecipientType.BCC, addressBcc);
            }
            // Setting the subject and Content type
            msg.setSubject(emailMessageDTO.getSubject());
            // Set message MIME type
            switch (emailMessageDTO.getMessageType()) {
                case EmailMessage.HTML_MSG:
                    msg.setContent(emailMessageDTO.getMessage(), "text/html");
                    break;
                case EmailMessage.TEXT_MSG:
                    msg.setContent(emailMessageDTO.getMessage(), "text/plain");
                    break;
            }
            // Function to send mail
            Transport.send(msg);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
