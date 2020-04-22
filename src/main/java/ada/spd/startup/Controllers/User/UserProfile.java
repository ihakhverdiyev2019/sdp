package ada.spd.startup.Controllers.User;

import ada.spd.startup.Domains.QuizCertificate;
import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.ENUMS.UserRoleInit;
import ada.spd.startup.Repositories.QuizCertificateRepository;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class UserProfile {

    private UserRepository userRepository;
    private QuizCertificateRepository quizCertificateRepository;


    public UserProfile(UserRepository userRepository, QuizCertificateRepository quizCertificateRepository) {
        this.userRepository = userRepository;
        this.quizCertificateRepository = quizCertificateRepository;
    }

    @RequestMapping(value = "/user/profile")
    public String findStartupProfile(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("quizResult", quizCertificateRepository.findQuizCertificateByUserIdAAndGrade(user.getId()));
            return "userProfile";

        } else
            return "redirect:/login";
    }


    @RequestMapping(value = "/user/profile/edit")
    public String userProfileEdit(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (httpSession.getAttribute("user") != null) {
            model.addAttribute("user", userRepository.findById(user.getId()).get());

            return "userProfileEdit";

        } else
            return "redirect:/login";
    }


    @RequestMapping(value = "/user/profile/save")
    public String userProfileSave(@Valid @ModelAttribute User user, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            userRepository.save(user);
            return "redirect:/user/profile";
        } else
            return "redirect:/login";

    }
}
