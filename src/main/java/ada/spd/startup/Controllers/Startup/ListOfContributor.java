package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ListOfContributor {

    private UserStartupRepository userStartupRepository;
    private StartupRepository startupRepository;
    private UserRepository userRepository;


    public ListOfContributor(UserStartupRepository userStartupRepository, StartupRepository startupRepository, UserRepository userRepository) {
        this.userStartupRepository = userStartupRepository;
        this.startupRepository = startupRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/startup/{tid}/contributor")
    public String listOfContributor(@PathVariable String tid, Model model, HttpSession httpSession) {
        User user = null;

        if (httpSession.getAttribute("user") != null) {
            user = (User) httpSession.getAttribute("user");
        } else if (httpSession.getAttribute("investor") != null) {
            user = (User) httpSession.getAttribute("investor");
        }
        if (user != null) {
            model.addAttribute("startup", startupRepository.findById(Long.parseLong(tid)).get());
            model.addAttribute("user", user);
            model.addAttribute("contributor", userStartupRepository.findUserStartupByStartupId(Long.parseLong(tid), RoleENUM.Contributor, StartupJoin.Joined));
            model.addAttribute("cont", userStartupRepository.findUserStartupByStartupId(Long.parseLong(tid), RoleENUM.Contributor, StartupJoin.Joined));

            return "usersList";
        } else
            return "redirect:/login";
    }

    @GetMapping(value = "/startup/{tid}/contributor/send")
    public String sendInvitation(@PathVariable String tid, @RequestParam("role") String role, @RequestParam("email") String email, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
            LocalDateTime now = LocalDateTime.now();
            UserStartup userStartup = new UserStartup();
            User user = userRepository.findByEmail(email);
            Startup startup = startupRepository.findById(Long.parseLong(tid)).get();
            if (userStartupRepository.findByStartupAndUser(startup.getId(), user.getId()) == null) {
                userStartup.setRole(role);
                userStartup.setUser(user);
                userStartup.setStartup(startup);
                userStartup.setDate(dtf.format(now));
                userStartup.setRights(RoleENUM.Contributor);
                userStartup.setStartupJoin(StartupJoin.Waiting);
                userStartupRepository.save(userStartup);
            }
            return "redirect:/startup/" + tid + "/contributor";
        } else
            return "redirect:/login";
    }

    @GetMapping(value = "/startup/{tid}/contributor/waiting")
    public String listOfWaitingContributor(@PathVariable String tid, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {

            model.addAttribute("startup", startupRepository.findById(Long.parseLong(tid)).get());
            model.addAttribute("user", (User) httpSession.getAttribute("user"));
            model.addAttribute("contributor", userStartupRepository.findUserStartupByStartupId(Long.parseLong(tid), RoleENUM.Contributor, StartupJoin.WantToJoin));
            model.addAttribute("cont", userStartupRepository.findUserStartupByStartupId(Long.parseLong(tid), RoleENUM.Contributor, StartupJoin.Joined));
            return "pendingRequests";
        } else
            return "redirect:/login";
    }

    @GetMapping(value = "/startup/{tid}/contributor/{cid}/delete")
    public String contributorDelete(@PathVariable String tid, @PathVariable String cid, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {


            userStartupRepository.delete(userStartupRepository.findById(Long.parseLong(cid)).get());

            return "redirect:/startup/" + tid + "/contributor";
        } else
            return "redirect:/login";
    }

    @GetMapping(value = "/startup/{tid}/contributor/{cid}/edit")
    public String contributorEdit(@PathVariable String tid, @PathVariable String cid, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {

            UserStartup userStartup = userStartupRepository.findById(Long.parseLong(cid)).get();
            model.addAttribute("userStartup", userStartup);

            model.addAttribute("contributor", userStartup.getUser());

            model.addAttribute("startup", userStartup.getStartup());

            model.addAttribute("user", httpSession.getAttribute("user"));

            return "contributorRoleEdit";
        } else
            return "redirect:/login";
    }


    @GetMapping(value = "/startup/{tid}/contributor/{cid}/save")
    public String contributorSave(@PathVariable String tid, @PathVariable String cid, @RequestParam("role") String role, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {

            UserStartup userStartup = userStartupRepository.findById(Long.parseLong(cid)).get();
            userStartup.setRole(role);
            userStartupRepository.save(userStartup);
            return "redirect:/startup/" + tid + "/contributor";
        } else
            return "redirect:/login";
    }

}
