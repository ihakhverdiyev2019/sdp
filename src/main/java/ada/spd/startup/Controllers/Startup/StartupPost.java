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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class StartupPost {


    private StartupRepository startupRepository;
    private UserStartupRepository userStartupRepository;
    private UserRepository userRepository;

    public StartupPost(StartupRepository startupRepository, UserStartupRepository userStartupRepository, UserRepository userRepository) {
        this.startupRepository = startupRepository;
        this.userStartupRepository = userStartupRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/startup/post")
    public String registerStartup(@Valid @ModelAttribute Startup startup, @RequestParam("category") String category, Model model, HttpSession httpSession) {
        User startupper = (User) httpSession.getAttribute("user");
        if (startupper != null) {
            startup.setCategory(category);
            UserStartup userStartup = new UserStartup();
            userStartup.setRights(RoleENUM.Founder);
            userStartup.setRole("CEO");
            userStartup.setStartupJoin(StartupJoin.Joined);
            userStartup.setStartup(startup);
            userStartup.setUser(startupper);
            startupRepository.save(startup);
            userStartupRepository.save(userStartup);
            return "redirect:/startup/list";
        } else

            return "redirect:/login";


    }


}


