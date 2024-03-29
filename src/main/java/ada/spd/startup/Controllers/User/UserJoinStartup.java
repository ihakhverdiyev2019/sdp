package ada.spd.startup.Controllers.User;


import ada.spd.startup.Domains.BadgeUser;
import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class UserJoinStartup {

    private StartupRepository startupRepository;
    private UserStartupRepository userStartupRepository;
    private UserRepository userRepository;
    private BadgeUserRepository badgeUserRepository;
    private BadgeRepository badgeRepository;


    public UserJoinStartup(StartupRepository startupRepository, UserStartupRepository userStartupRepository, UserRepository userRepository, BadgeUserRepository badgeUserRepository, BadgeRepository badgeRepository) {
        this.startupRepository = startupRepository;
        this.userStartupRepository = userStartupRepository;
        this.userRepository = userRepository;
        this.badgeUserRepository = badgeUserRepository;
        this.badgeRepository = badgeRepository;
    }

    @RequestMapping(value = "/startup/{id}/send")
    public String sendRequestFrJoin(@PathVariable String id, @RequestParam("role") String role, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
            LocalDateTime now = LocalDateTime.now();
            Startup startup = startupRepository.findById(Long.parseLong(id)).get();
            User user = (User) httpSession.getAttribute("user");
            UserStartup userStartup = new UserStartup();
            userStartup.setUser(user);
            userStartup.setRights(RoleENUM.Contributor);
            userStartup.setRole(role);
            userStartup.setStartup(startup);
            userStartup.setDate(dtf.format(now));
            userStartup.setStartupJoin(StartupJoin.WantToJoin);
            userStartupRepository.save(userStartup);


            return "redirect:/startups";
        } else
            return "redirect:/login";
    }


    @RequestMapping(value = "/startup/{id}/contributor/{cid}/accept")
    public String acceptStartup(@PathVariable String id, @PathVariable String cid, HttpSession httpSession) {
        Startup startup = startupRepository.findById(Long.parseLong(id)).get();
        User user = (User) httpSession.getAttribute("user");
        UserStartup userStartup = userStartupRepository.findByStartupAndUser(startup.getId(), Long.parseLong(cid));

        userStartup.setStartupJoin(StartupJoin.Joined);
        userStartupRepository.save(userStartup);

        User contributor = userStartup.getUser();
        BadgeUser badgeUser = new BadgeUser();
        badgeUser.setUser(contributor);

        if (userStartup.getUser().getNumberOfJoinedStartup() + 1 == 1) {
            badgeUser.setBadge(badgeRepository.findById(Long.parseLong(String.valueOf(4))).get());
        } else if (userStartup.getUser().getNumberOfJoinedStartup() + 1 == 3) {
            badgeUser.setBadge(badgeRepository.findById(Long.parseLong(String.valueOf(5))).get());

        } else if (userStartup.getUser().getNumberOfJoinedStartup() + 1 == 5) {
            badgeUser.setBadge(badgeRepository.findById(Long.parseLong(String.valueOf(6))).get());

        }
        contributor.setNumberOfJoinedStartup(contributor.getNumberOfJoinedStartup() + 1);

        badgeUserRepository.save(badgeUser);
        userRepository.save(contributor);

        return "redirect:/startup/" + id + "/contributor";
    }

    @RequestMapping(value = "/startup/{id}/contributor/{cid}/reject")
    public String rejectStartup(@PathVariable String id, @PathVariable String cid, HttpSession httpSession) {
        Startup startup = startupRepository.findById(Long.parseLong(id)).get();
        User user = (User) httpSession.getAttribute("user");
        UserStartup userStartup = userStartupRepository.findByStartupAndUser(startup.getId(), Long.parseLong(cid));

        userStartupRepository.delete(userStartup);


        return "redirect:/startup/" + id + "/contributor";
    }

}
