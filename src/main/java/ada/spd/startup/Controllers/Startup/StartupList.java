package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.User;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class StartupList {

    private UserStartupRepository userStartupRepository;

    public StartupList(UserStartupRepository userStartupRepository) {
        this.userStartupRepository = userStartupRepository;
    }

    @GetMapping(value = "/startup/list")
    public String listOfStartup(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("startupList", userStartupRepository.findByUserId(user.getId()));
            return "projectList";
        } else
            return "redirect:/login";
    }

}
