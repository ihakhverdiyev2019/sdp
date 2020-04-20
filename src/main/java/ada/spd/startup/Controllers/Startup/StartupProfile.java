package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class StartupProfile {

    private StartupRepository startupRepository;
    private UserStartupRepository userStartupRepository;


    public StartupProfile(StartupRepository startupRepository, UserStartupRepository userStartupRepository) {
        this.startupRepository = startupRepository;
        this.userStartupRepository = userStartupRepository;
    }

    @RequestMapping(value = "/startup/{id}/profile")
    public String findStartupProfile(@PathVariable String id, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            Startup startup = startupRepository.findById(Long.parseLong(id)).get();
            model.addAttribute("startup", startup);
            model.addAttribute("founder", userStartupRepository.findFounderByStartupIdAndUserRights(startup.getId(), RoleENUM.Founder));
            model.addAttribute("userStartup", userStartupRepository.findByStartupAndUser(startup.getId(), user.getId()));
            return "startupProfile";

        } else
            return "redirect:/login";
    }


    @RequestMapping(value = "/startup/{id}/view")
    public String startupProfileDisplay(@PathVariable String id, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            Startup startup = startupRepository.findById(Long.parseLong(id)).get();
            model.addAttribute("startup", startup);
            model.addAttribute("founder", userStartupRepository.findFounderByStartupIdAndUserRights(startup.getId(), RoleENUM.Founder));
            model.addAttribute("contributor", userStartupRepository.findUserStartupByStartupId(startup.getId(), RoleENUM.Contributor, StartupJoin.Joined));
            model.addAttribute("investor", userStartupRepository.findUserStartupByStartupId(startup.getId(), RoleENUM.Investor, StartupJoin.Joined));

            return "startupDisplayContributor";

        } else
            return "redirect:/login";
    }
}
