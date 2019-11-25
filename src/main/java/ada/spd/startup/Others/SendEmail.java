package ada.spd.startup.Others;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class SendEmail {

    private JavaMailSender javaMailSender;

    public SendEmail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String toEmail, String message) {

        var mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject("Test");
        mailMessage.setText(message);

        mailMessage.setFrom("ihakhverdiyev2019@gmail.com.com");

        javaMailSender.send(mailMessage);
    }
}