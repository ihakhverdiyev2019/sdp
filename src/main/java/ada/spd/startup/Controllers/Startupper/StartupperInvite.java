package ada.spd.startup.Controllers.Startupper;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class StartupperInvite {


    @GetMapping(value = "startupper/logout")
    public void logoutStartupper(HttpSession session) {
        session.invalidate();

    }
}
