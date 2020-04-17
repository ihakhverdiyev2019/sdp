package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ListOfContributor {

    private UserStartupRepository userStartupRepository;


    public ListOfContributor(UserStartupRepository userStartupRepository) {
        this.userStartupRepository = userStartupRepository;
    }





    @GetMapping(value = "/project/{tid}/contributor/list")
    public String listOfStartup(@PathVariable String tid, Model model) {


        model.addAttribute("contributor", userStartupRepository.findByStartupIdAndUserRights(Long.parseLong(tid), RoleENUM.Contributor));
        return "contributorList";
    }


}
