package ada.spd.startup.Controllers.User;


import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StatusEnum;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class UserLogout {

    private UserRepository userRepository;

    public UserLogout(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/logout")
    public String logoutUser(HttpSession session) {
        User user = null;
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
        }
        if (session.getAttribute("investor") != null) {
            user = (User) session.getAttribute("investor");
        }
        user.setStatusEnum(StatusEnum.Offline);
        userRepository.save(user);
        session.invalidate();

        return "redirect:/login";


    }
}
