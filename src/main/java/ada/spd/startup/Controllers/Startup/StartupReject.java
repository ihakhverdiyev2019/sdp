package ada.spd.startup.Controllers.Startup;

import ada.spd.startup.Domains.JoinStartup;
import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StartupInvitation;
import ada.spd.startup.Repositories.JoinStartupRepository;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserRepository;
import ada.spd.startup.Repositories.VerificationTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartupReject {

    private StartupRepository startupRepository;
    private UserRepository userRepository;
    private JoinStartupRepository joinStartupRepository;


    public StartupReject(StartupRepository startupRepository, UserRepository userRepository, JoinStartupRepository joinStartupRepository) {
        this.startupRepository = startupRepository;
        this.userRepository = userRepository;
        this.joinStartupRepository = joinStartupRepository;
    }

    @RequestMapping(value = "startup/{id}/reject")
    public String rejectStartup(@PathVariable String id) {
        Startup startup = startupRepository.findById(Long.parseLong(id)).get();
        User user = userRepository.findById(Long.parseLong(String.valueOf(1))).get();
        User expected = joinStartupRepository.findByStartup(startup);

        if (user == expected) {

            JoinStartup joinStartup = joinStartupRepository.findByUserAndStartup(user, startup);
            joinStartup.setStartupInvitation(StartupInvitation.Cancel);

            joinStartupRepository.save(joinStartup);
        }


        return "";
    }
}
