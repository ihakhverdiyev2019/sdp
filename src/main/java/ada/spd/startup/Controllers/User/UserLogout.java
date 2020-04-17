package ada.spd.startup.Controllers.User;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class UserLogout {

    @GetMapping(value = "/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();

        return "redirect:/login";


    }
}
