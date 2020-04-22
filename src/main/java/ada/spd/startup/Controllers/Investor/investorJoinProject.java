package ada.spd.startup.Controllers.Investor;

import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class investorJoinProject {

    private UserStartupRepository userStartupRepository;

    public investorJoinProject(UserStartupRepository userStartupRepository) {
        this.userStartupRepository = userStartupRepository;
    }

    @GetMapping(value = "/invested/projects")
    public String investedProjectList(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("investor");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("startupList", userStartupRepository.findByUserAndStartupJoin(user.getId(), StartupJoin.Joined));
            return "investedProjectList";
        } else
            return "redirect:/login";
    }
}
