package ada.spd.startup.Controllers.User;


import ada.spd.startup.Domains.SMS;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.Status;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserVerify {
    private UserRepository userRepository;


    public UserVerify(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping(value = "user/verify", produces = "application/json")
    public void verifyProfile(@RequestBody SMS sms, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("User");
        if (user.getCode() == sms.getSmsCode()) {
            user.setStatus(Status.Active);
            userRepository.save(user);
        }

    }
}
