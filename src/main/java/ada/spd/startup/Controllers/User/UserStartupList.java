package ada.spd.startup.Controllers.User;


import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class UserStartupList {

    private UserStartupRepository userStartupRepository;
    private StartupRepository startupRepository;

    public UserStartupList(UserStartupRepository userStartupRepository, StartupRepository startupRepository) {
        this.userStartupRepository = userStartupRepository;
        this.startupRepository = startupRepository;
    }

    @RequestMapping(value = "/startups")
    public String listOfStartupsForUser(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("startupList", startupRepository.findAll());
        model.addAttribute("startupSession" , userStartupRepository.findByStartupAndRights(user.getId(), StartupJoin.Joined));

        return "listOfStartups";
    }

//    @GetMapping(value = "/startups/filter")
//    public String startupFilter(Model model, HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//        model.addAttribute("user", user);
//        model.addAttribute("startupList", userStartupRepository.findAll());
//        return "projectList";
//    }

}
