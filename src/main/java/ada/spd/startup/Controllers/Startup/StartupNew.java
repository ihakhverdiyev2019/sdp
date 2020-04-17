package ada.spd.startup.Controllers.Startup;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller

public class StartupNew {


    @RequestMapping("/startup/create")
    public String newTodo(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        Startup startup = new Startup();

        model.addAttribute("startup", startup);
        model.addAttribute("user", user);

        return "addNewProject";

    }
}
