package ada.spd.startup.Controllers.Investor;

import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class InvestorStartupList {
    private UserStartupRepository userStartupRepository;
    private StartupRepository startupRepository;

    public InvestorStartupList(UserStartupRepository userStartupRepository, StartupRepository startupRepository) {
        this.userStartupRepository = userStartupRepository;
        this.startupRepository = startupRepository;
    }

    @RequestMapping(value = "/istartups")
    public String listOfStartupsForInvestor(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("investor");

        if (user != null) {

            model.addAttribute("user", user);

            model.addAttribute("startupList", startupRepository.findAll());
            model.addAttribute("startupSession", userStartupRepository.findByStartupAndRights(user.getId(), StartupJoin.Joined));
            model.addAttribute("result", ((Collection<?>) startupRepository.findAll()).size());
            return "listOfStartupsForInvestor";
        } else
            return "redirect:/login";
    }

}
