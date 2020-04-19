package ada.spd.startup.Controllers.User;


import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Controller
public class RequestController {

    private UserStartupRepository userStartupRepository;
    private StartupRepository startupRepository;

    public RequestController(UserStartupRepository userStartupRepository, StartupRepository startupRepository) {
        this.userStartupRepository = userStartupRepository;
        this.startupRepository = startupRepository;
    }


    @RequestMapping(value = "/requests")
    public String listOfStartupsForUser(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("startupList", startupRepository.findAll());
            model.addAttribute("startupSession", userStartupRepository.findByUserAndStartupJoin(user.getId(), StartupJoin.WantToJoin));
            model.addAttribute("request", userStartupRepository.findByUserAndStartupJoin(user.getId(), StartupJoin.Waiting));

            model.addAttribute("result", ((Collection<?>) startupRepository.findAll()).size());
            return "requests";
        } else
            return "redirect:/login";
    }


    @RequestMapping(value = "/request/startup/{id}/accept")
    public String acceptRequest(@PathVariable String id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            Startup startup = startupRepository.findById(Long.parseLong(id)).get();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
            LocalDateTime now = LocalDateTime.now();
            UserStartup userStartup = userStartupRepository.findByStartupAndUser(startup.getId(), user.getId());
            userStartup.setStartupJoin(StartupJoin.Joined);
            userStartup.setDate(dtf.format(now));

            userStartupRepository.save(userStartup);
            return "redirect:/requests";
        } else
            return "redirect:/login";
    }

    @RequestMapping(value = "/request/startup/{id}/reject")
    public String rejectRequest(@PathVariable String id, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            Startup startup = startupRepository.findById(Long.parseLong(id)).get();
            UserStartup userStartup = userStartupRepository.findByStartupAndUser(startup.getId(), user.getId());
            userStartupRepository.delete(userStartup);
            return "redirect:/requests";
        } else
            return "redirect:/login";
    }

}
