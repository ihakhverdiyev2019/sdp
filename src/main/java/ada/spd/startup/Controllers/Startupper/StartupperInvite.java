package ada.spd.startup.Controllers.Startupper;


import ada.spd.startup.Others.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class StartupperInvite {

    @Autowired
    private SendEmail sendEmail;

    @GetMapping(value = "startupper/invite")
    public void inviteMember(@RequestParam("refferalCode") String refCode,@RequestParam("email") String email) {
        String text = "http://localhost:8080/startup/"+refCode;
        sendEmail.sendMail(email,text);

    }
}
