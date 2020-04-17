package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class ListOfContributor {

    private UserStartupRepository userStartupRepository;
    private StartupRepository startupRepository;


    public ListOfContributor(UserStartupRepository userStartupRepository, StartupRepository startupRepository) {
        this.userStartupRepository = userStartupRepository;
        this.startupRepository = startupRepository;
    }

    @GetMapping(value = "/startup/{tid}/contributor")
    public String listOfStartup(@PathVariable String tid, Model model, HttpSession httpSession) {

        model.addAttribute("startup", startupRepository.findById(Long.parseLong(tid)).get());
        model.addAttribute("user", (User) httpSession.getAttribute("user"));
        model.addAttribute("contributor", userStartupRepository.findByStartupIdAndUserRights(Long.parseLong(tid), RoleENUM.Contributor));
        return "usersList";
    }


}
