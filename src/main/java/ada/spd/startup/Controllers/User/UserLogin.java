package ada.spd.startup.Controllers.User;


import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StatusEnum;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserLogin {

    private UserRepository userRepository;

    public UserLogin(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/user/login")
    public String loginStartupper(@Valid @ModelAttribute User user, HttpSession httpSession) {
        User user1 = userRepository.findByCredentials(user.getLogin(), user.getPassword()).get();
//        if (userRightsRepository.findByUser(user1)== RoleENUM.Founder)
        httpSession.setAttribute("user", user1);
        user1.setStatusEnum(StatusEnum.Online);
        userRepository.save(user1);


        return "redirect:/dashboard";
    }

    @RequestMapping(value = "/login")
    public String login() {


        return "login";
    }


}
