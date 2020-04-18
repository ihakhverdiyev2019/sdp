package ada.spd.startup.Controllers.Email;

import ada.spd.startup.Domains.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Controller
public class EmailFetchController {


    @RequestMapping("/email")
    public String InboxFetch(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        String host = "mail." + user.getEmail().substring(user.getEmail().lastIndexOf("@") + 1);

        try {

            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("pop3s");

            store.connect(host, "ihakhverdiyev@elixir.az", "Ismayil214-");

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            List<Message> messages = Arrays.asList(emailFolder.getMessages());
            model.addAttribute("email", messages);

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        model.addAttribute("user", user);
        return "email";

    }

    @RequestMapping("/email/sent")
    public String sentFetch(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        String host = "mail." + user.getEmail().substring(user.getEmail().lastIndexOf("@") + 1);

        try {

            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("pop3s");

            store.connect(host, "ihakhverdiyev@elixir.az", "Ismayil214-");

            Folder emailFolder = store.getFolder("SENT");
            emailFolder.open(Folder.READ_ONLY);

            List<Message> messages = Arrays.asList(emailFolder.getMessages());
            model.addAttribute("email", messages);

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        model.addAttribute("user", user);
        return "sentEmail";

    }


    @RequestMapping("/email/send")
    public String sendEmail(HttpSession httpSession, @RequestParam("emailto") String emailto, @RequestParam("emailsubject") String emailsubject, @RequestParam("emailcc") String emailcc, @RequestParam("emailbcc") String emailbcc, @RequestParam("message") String emailmessage, @RequestParam("emailattach") File emailattach) {
        User user = (User) httpSession.getAttribute("user");
        String host = "mail." + user.getEmail().substring(user.getEmail().lastIndexOf("@") + 1);

        final String username = user.getEmail();
        final String password = user.getEmailPAss();

        Properties properties = new Properties();

        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailcc)
            );
            message.setSubject(emailsubject);
            message.setText(emailmessage);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "redirect:/email";
    }


}
