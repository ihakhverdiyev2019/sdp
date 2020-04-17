package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.User;
import ada.spd.startup.Repositories.JoinStartupRepository;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListOfInvitationStartup {


    private UserRepository userRepository;
    private JoinStartupRepository joinStartupRepository;


    public ListOfInvitationStartup(UserRepository userRepository, JoinStartupRepository joinStartupRepository) {
        this.userRepository = userRepository;
        this.joinStartupRepository = joinStartupRepository;
    }


    @RequestMapping(value = "startup/list/invitation")
    public String listOfInvitation(Model model) {
        User user = userRepository.findById(Long.parseLong(String.valueOf(1))).get();

        model.addAttribute("invitationList", joinStartupRepository.findByUser(user));

        return "";

    }


}
