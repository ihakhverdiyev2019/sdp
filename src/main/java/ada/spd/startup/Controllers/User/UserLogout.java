package ada.spd.startup.Controllers.User;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserLogout {

    @GetMapping(value = "/user/logout")
    public void logoutUser(HttpSession session) {
        session.invalidate();

    }
}
