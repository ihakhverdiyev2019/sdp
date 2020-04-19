package ada.spd.startup.Controllers.User;

import ada.spd.startup.Domains.User;
import ada.spd.startup.Repositories.StartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class EnterProject {


    private StartupRepository startupRepository;

    public EnterProject(StartupRepository startupRepository) {
        this.startupRepository = startupRepository;
    }


    @GetMapping(value = "/startup/{tid}")
    public String projectWorkplace(@PathVariable String tid, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("startup", startupRepository.findById(Long.parseLong(tid)).get());
            model.addAttribute("user", user);

            return "projectDashboard";
        } else
            return "redirect:/login";

    }
}