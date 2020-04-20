package ada.spd.startup.Controllers.User;

import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.Status;
import ada.spd.startup.Others.GenerateCode;
import ada.spd.startup.Others.SMSSender;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class UserRegistration {


    private UserRepository userRepository;

    public UserRegistration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/user/registration")
    public String registerUser(@Valid @ModelAttribute User user, Model model, HttpSession httpSession) {

        int smsCode = GenerateCode.codeSMS();

        user.setStatus(Status.Hold);
        user.setCode(smsCode);
        if(user.getGender().equals("Man"))
            user.setAvatar("https://thumbs.dreamstime.com/b/happy-smiling-geek-hipster-beard-man-cool-avatar-geek-man-avatar-104871313.jpg");
        else
            user.setAvatar("https://cdn0.iconfinder.com/data/icons/faces-general/100/female_curly_flat-512.png");

        httpSession.setAttribute("User", user);
        SMSSender.smsSender(user.getPhoneNo(), smsCode);

        model.addAttribute("user", user);
        httpSession.setAttribute("newUser", user);
        userRepository.save(user);

        return "redirect:/verify";

    }


    @RequestMapping(value = "/verify")
    public String verify(HttpSession httpSession) {
        if (httpSession.getAttribute("newUser") != null)

            return "Registration_sendCode";
        else
            return "redirect:/login";

    }

    @RequestMapping(value = "/resend")
    public String resend(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("newUser");
        int smsCode = GenerateCode.codeSMS();

        user.setCode(smsCode);

        SMSSender.smsSender(user.getPhoneNo(), smsCode);
        userRepository.save(user);
        return "redirect:/verify";

    }


    @RequestMapping(value = "/verify/done")
    public String verifyDone(HttpSession httpSession, @RequestParam("code") String code) {
        User user = (User) httpSession.getAttribute("newUser");
        if (user.getCode() == Integer.parseInt(code)) {
            user.setStatus(Status.Active);


        }
        return "redirect:/login";


    }


    @RequestMapping(value = "/register")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "Registration";

    }
}
