package modulesforusers;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public SendEmail(String to, String header, String mess) {

        final String username = "duynguyen24th@gmail.com";
        final String password = "ysrorgznhwuatxok";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            javax.mail.Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress("duynguyen24th@gmail.com"));
            message.setRecipients(
            		javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(header);
            message.setText(mess);
            
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}