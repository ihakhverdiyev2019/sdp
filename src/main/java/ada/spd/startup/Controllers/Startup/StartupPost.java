package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.*;

import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.*;

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
    private BadgeRepository badgeRepository;
    private BadgeUserRepository badgeUserRepository;


    public StartupPost(StartupRepository startupRepository, UserStartupRepository userStartupRepository, UserRepository userRepository, BadgeRepository badgeRepository, BadgeUserRepository badgeUserRepository) {
        this.startupRepository = startupRepository;
        this.userStartupRepository = userStartupRepository;
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
        this.badgeUserRepository = badgeUserRepository;
    }

    @RequestMapping(value = "/startup/post")
    public String registerStartup(@Valid @ModelAttribute Startup startup, @RequestParam("category") String category, Model model, HttpSession httpSession) {
        User startupper = (User) httpSession.getAttribute("user");
        if (startupper != null) {
            startup.setCategory(category);
            UserStartup userStartup = new UserStartup();
            BadgeUser badgeUser = new BadgeUser();
            userStartup.setRights(RoleENUM.Founder);
            userStartup.setRole("CEO");
            userStartup.setStartupJoin(StartupJoin.Joined);
            startup.setClickcount(0);
            startup.setInvested(0);
            userStartup.setStartup(startup);
            userStartup.setUser(startupper);
            startupper.setNumberOfStartup(startupper.getNumberOfStartup() + 1);


            System.out.println(startupper.getNumberOfStartup());
            if ((startupper.getNumberOfStartup()) == 1) {
                badgeUser.setUser(startupper);

                badgeUser.setBadge(badgeRepository.findById(Long.parseLong(String.valueOf(1))).get());
                badgeUserRepository.save(badgeUser);

            } else if ((startupper.getNumberOfStartup()) == 3) {
                badgeUser.setUser(startupper);

                badgeUser.setBadge(badgeRepository.findById(Long.parseLong(String.valueOf(2))).get());
                badgeUserRepository.save(badgeUser);


            } else if ((startupper.getNumberOfStartup()) == 5) {
                badgeUser.setUser(startupper);

                badgeUser.setBadge(badgeRepository.findById(Long.parseLong(String.valueOf(3))).get());
                badgeUserRepository.save(badgeUser);


            }
            startupRepository.save(startup);
            userRepository.save(startupper);
            userStartupRepository.save(userStartup);
            return "redirect:/startup/list";
        } else

            return "redirect:/login";


    }


}


