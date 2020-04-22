package ada.spd.startup.Controllers.Investor;

import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class investorRequestes {

    private UserStartupRepository userStartupRepository;

    public investorRequestes(UserStartupRepository userStartupRepository) {
        this.userStartupRepository = userStartupRepository;
    }

    @RequestMapping(value = "/invest")
    public String listOfStartupsForUser(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("investor");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("request", userStartupRepository.findByUserAndStartupJoin(user.getId(), StartupJoin.WantToJoin));

            return "requestInvestor";
        } else
            return "redirect:/login";
    }
}
