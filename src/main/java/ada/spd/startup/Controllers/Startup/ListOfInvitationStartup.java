package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.User;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ListOfInvitationStartup {


    private UserRepository userRepository;


    public ListOfInvitationStartup(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "startup/list/invitation")
    public String listOfInvitation(Model model) {
        User user = userRepository.findById(Long.parseLong(String.valueOf(1))).get();


        return "";

    }


}
