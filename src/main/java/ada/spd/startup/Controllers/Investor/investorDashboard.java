package ada.spd.startup.Controllers.Investor;

import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class investorDashboard {

    private UserStartupRepository userStartupRepository;

    public investorDashboard(UserStartupRepository userStartupRepository) {
        this.userStartupRepository = userStartupRepository;
    }

    @GetMapping(value = "/indashboard")
    public String userDashboard(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("investor");
        if (user != null) {
            model.addAttribute("investor", user);
            model.addAttribute("invested", userStartupRepository.findUserStartupByStartupId(user.getId(), RoleENUM.Investor, StartupJoin.Joined).size());
            model.addAttribute("investing", userStartupRepository.findUserStartupByStartupId(user.getId(), RoleENUM.Investor, StartupJoin.WantToJoin).size());
            model.addAttribute("investingDetail", userStartupRepository.findUserStartupByStartupId(user.getId(), RoleENUM.Investor, StartupJoin.Joined));


            return "investorDashboard";
        } else
            return "redirect:/login";
    }
}
