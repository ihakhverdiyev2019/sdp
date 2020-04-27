package ada.spd.startup.Controllers.Startup;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.Status;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class StartupContributor {


    private UserStartupRepository userStartupRepository;
    private StartupRepository startupRepository;

    public StartupContributor(UserStartupRepository userStartupRepository, StartupRepository startupRepository) {
        this.userStartupRepository = userStartupRepository;
        this.startupRepository = startupRepository;
    }

    @RequestMapping(value = "/startup/{id}/chat")
    public String findAll(@PathVariable String id, HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");

        model.addAttribute("user", user);
        model.addAttribute("startup", startupRepository.findById(Long.parseLong(id)));
//        model.addAttribute("contributor", userStartupRepository.findByStartupIdAndUserRights(1, RoleENUM.Contributor));
        return "chat";

    }


}
