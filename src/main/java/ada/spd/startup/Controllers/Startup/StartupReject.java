package ada.spd.startup.Controllers.Startup;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StartupInvitation;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartupReject {

    private StartupRepository startupRepository;
    private UserRepository userRepository;


    public StartupReject(StartupRepository startupRepository, UserRepository userRepository) {
        this.startupRepository = startupRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "startup/{id}/reject")
    public String rejectStartup(@PathVariable String id) {
        Startup startup = startupRepository.findById(Long.parseLong(id)).get();
        User user = userRepository.findById(Long.parseLong(String.valueOf(1))).get();



        return "";
    }
}
