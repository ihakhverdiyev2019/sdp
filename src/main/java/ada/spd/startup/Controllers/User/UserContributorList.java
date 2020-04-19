package ada.spd.startup.Controllers.User;

import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class UserContributorList {

    private UserStartupRepository userStartupRepository;
    private UserRepository userRepository;

    public UserContributorList(UserStartupRepository userStartupRepository, UserRepository userRepository) {
        this.userStartupRepository = userStartupRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/contributors")
    public String listOfContributorForUser(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("contributorList", userRepository.findAll());
            model.addAttribute("result", ((Collection<?>) userRepository.findAll()).size());
            return "listOfContributor";
        } else
            return "redirect:/login";
    }
}
