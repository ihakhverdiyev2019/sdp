package ada.spd.startup.Controllers.User;

import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.ENUMS.StatusEnum;
import ada.spd.startup.ENUMS.UserRoleInit;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class UserContributorList {

    private UserStartupRepository userStartupRepository;
    private UserRepository userRepository;

    public UserContributorList(UserStartupRepository userStartupRepository, UserRepository userRepository) {
        this.userStartupRepository = userStartupRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/contributors")
    public String listOfContributorForUser(@RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "gender", required = false) String gender,
                                           @RequestParam(value = "status", required = false) StatusEnum status,
                                           @RequestParam(value = "nationality", required = false) String nationality,
                                           Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            if (nationality != null && status != null) {

                model.addAttribute("contributorList", userRepository.findByUserRoleInitAndNationallityAndStatus(UserRoleInit.User, nationality, status));
                model.addAttribute("result", userRepository.findByUserRoleInitAndNationallityAndStatus(UserRoleInit.User, nationality, status).size());
            } else if (nationality != null && gender != null) {

                model.addAttribute("contributorList", userRepository.findByUserRoleInitAndNationallityAndGender(UserRoleInit.User, nationality, gender));
                model.addAttribute("result", userRepository.findByUserRoleInitAndNationallityAndGender(UserRoleInit.User, nationality, gender).size());
            } else if (gender != null) {

                model.addAttribute("contributorList", userRepository.findByUserRoleInitandGender(UserRoleInit.User, gender));
                model.addAttribute("result", userRepository.findByUserRoleInitandGender(UserRoleInit.User, gender).size());
            } else if (nationality != null) {

                model.addAttribute("contributorList", userRepository.findByUserRoleInitAndNationallity(UserRoleInit.User, nationality));
                model.addAttribute("result", userRepository.findByUserRoleInitAndNationallity(UserRoleInit.User, nationality).size());
            } else if (status != null) {

                model.addAttribute("contributorList", userRepository.findByUserRoleInitAndStatusEnum(UserRoleInit.User, status));
                model.addAttribute("result", userRepository.findByUserRoleInitAndStatusEnum(UserRoleInit.User, status).size());

            } else if (name != null) {
                model.addAttribute("contributorList", userRepository.findByNameContainingIgnoreCaseAndUserRoleInit(name, UserRoleInit.User));
                model.addAttribute("result", userRepository.findByNameContainingIgnoreCaseAndUserRoleInit(name, UserRoleInit.User).size());

            } else {
                model.addAttribute("contributorList", userRepository.findByUserRoleInit(UserRoleInit.User));
                model.addAttribute("result", userRepository.findByUserRoleInit(UserRoleInit.User).size());

            }
            model.addAttribute("user", user);
            return "listOfContributor";
        } else
            return "redirect:/login";
    }
}
