package ada.spd.startup.Controllers.Email;

import ada.spd.startup.Domains.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.*;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Controller
public class EmailFetchController {


    @RequestMapping("/email/inbox")
    public String InboxFetch(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        String host = "mail" + user.getEmail().substring(user.getEmail().lastIndexOf("@") + 1);

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

        return "email";

    }


}
