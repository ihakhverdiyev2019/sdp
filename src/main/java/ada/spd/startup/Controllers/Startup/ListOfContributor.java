package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("contributor", userStartupRepository.findUserStartupByStartupId(Long.parseLong(tid), RoleENUM.Contributor));
        return "usersList";
    }

    @GetMapping(value = "/startup/{tid}/contributor/{cid}/delete")
    public String contributorDelete(@PathVariable String tid, @PathVariable String cid, HttpSession httpSession) {


        userStartupRepository.delete(userStartupRepository.findById(Long.parseLong(cid)).get());

        return "redirect:/startup/" + tid + "/contributor";
    }

    @GetMapping(value = "/startup/{tid}/contributor/{cid}/edit")
    public String contributorEdit(@PathVariable String tid, @PathVariable String cid, Model model, HttpSession httpSession) {

        UserStartup userStartup = userStartupRepository.findById(Long.parseLong(cid)).get();
        model.addAttribute("userStartup", userStartup);

        model.addAttribute("contributor", userStartup.getUser());

        model.addAttribute("startup", userStartup.getStartup());

        model.addAttribute("user", httpSession.getAttribute("user"));

        return "contributorRoleEdit";
    }


    @GetMapping(value = "/startup/{tid}/contributor/{cid}/save")
    public String contributorSave(@PathVariable String tid, @PathVariable String cid, @RequestParam("role") String role, Model model, HttpSession httpSession) {

        UserStartup userStartup = userStartupRepository.findById(Long.parseLong(cid)).get();
        userStartup.setRole(role);
        userStartupRepository.save(userStartup);
        return "redirect:/startup/" + tid + "/contributor";
    }

}
