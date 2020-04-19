package ada.spd.startup.Controllers.User;

import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.Status;
import ada.spd.startup.Others.GenerateCode;
import ada.spd.startup.Others.SMSSender;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class UserRegistration {


    private UserRepository userRepository;

    public UserRegistration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/user/registration")
    public String registerUser(@RequestBody User user, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            int smsCode = GenerateCode.codeSMS();

            user.setStatus(Status.Hold);
            user.setCode(smsCode);

            httpSession.setAttribute("User", user);
            SMSSender.smsSender(user.getPhoneNo(), smsCode);

            userRepository.save(user);

            return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }
}
