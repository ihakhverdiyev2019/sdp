package ada.spd.startup.Controllers.Startup;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.Status;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartupContributor {


    private UserStartupRepository userStartupRepository;

    public StartupContributor(UserStartupRepository userStartupRepository) {
        this.userStartupRepository = userStartupRepository;
    }

    @RequestMapping(value = "/startup/{id}/chat")
    public String findAll(@PathVariable String id, Model model) {
        model.addAttribute("contributor", userStartupRepository.findByStartupIdAndUserRights(1, RoleENUM.Contributor));
        return "chat";

    }


}
