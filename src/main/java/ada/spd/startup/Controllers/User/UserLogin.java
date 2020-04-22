package ada.spd.startup.Controllers.User;


import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StatusEnum;
import ada.spd.startup.ENUMS.UserRoleInit;
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
        User user1 = userRepository.findByCredentials(user.getLogin(), user.getPassword());
        if (user1 != null) {


            if (user1.getUserRoleInit() == (UserRoleInit.Investor)) {
                httpSession.setAttribute("investor", user1);

            } else {
                httpSession.setAttribute("user", user1);
            }

            user1.setStatusEnum(StatusEnum.Online);
            userRepository.save(user1);
            if (user1.getUserRoleInit() == (UserRoleInit.Investor))

                return "redirect:/indashboard";
            else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";

    }

    @RequestMapping(value = "/login")
    public String login(HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null)
            return "redirect:/dashboard";
        else
            return "login";

    }


}
