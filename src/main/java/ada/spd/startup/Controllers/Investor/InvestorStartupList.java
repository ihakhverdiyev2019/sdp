package ada.spd.startup.Controllers.Investor;

import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String listOfStartupsForInvestor(@RequestParam(value = "projectName", required = false) String name,
                                            @RequestParam(value = "category", required = false) String category,
                                            Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("investor");

        if (user != null) {
            if (name != null) {
                model.addAttribute("startupList", startupRepository.findByStartupNameContainingIgnoreCase(name));
                model.addAttribute("result", ((Collection<?>) startupRepository.findByStartupNameContainingIgnoreCase(name)).size());

            } else if (category != null && !category.equals("All")) {
                model.addAttribute("startupList", startupRepository.findByCategory(category));
                model.addAttribute("result", ((Collection<?>) startupRepository.findByCategory(category)).size());

            } else {
                model.addAttribute("startupList", startupRepository.findAll());
                model.addAttribute("result", ((Collection<?>) startupRepository.findAll()).size());
            }
            model.addAttribute("user", user);

            model.addAttribute("startupSession", userStartupRepository.findByStartupAndRights(user.getId(), StartupJoin.Joined));
            return "listOfStartupsForInvestor";
        } else
            return "redirect:/login";
    }

}
