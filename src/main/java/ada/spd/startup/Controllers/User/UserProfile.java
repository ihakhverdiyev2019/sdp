package ada.spd.startup.Controllers.User;

import ada.spd.startup.Domains.QuizCertificate;
import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.ENUMS.UserRoleInit;
import ada.spd.startup.Repositories.BadgeUserRepository;
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
    private BadgeUserRepository badgeUserRepository;

    public UserProfile(UserRepository userRepository, QuizCertificateRepository quizCertificateRepository, BadgeUserRepository badgeUserRepository) {
        this.userRepository = userRepository;
        this.quizCertificateRepository = quizCertificateRepository;
        this.badgeUserRepository = badgeUserRepository;
    }

    @RequestMapping(value = "/user/profile")
    public String findStartupProfile(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", userRepository.findById(user.getId()).get());
            model.addAttribute("quizResult", quizCertificateRepository.findQuizCertificateByUserIdAAndGrade(user.getId()));
            model.addAttribute("badge", badgeUserRepository.findBadgeUserById(user.getId()));
            model.addAttribute("sesUser", user);
            return "userProfile";

        } else
            return "redirect:/login";
    }


    @RequestMapping(value = "/profile/{id}")
    public String findUserbyProfile(@PathVariable String id, Model model, HttpSession httpSession) {
        User user1 = (User) httpSession.getAttribute("user");

        System.out.println(user1.getName());
        User user = userRepository.findById(Long.parseLong(id)).get();

        model.addAttribute("user", user);
        model.addAttribute("quizResult", quizCertificateRepository.findQuizCertificateByUserIdAAndGrade(user.getId()));
        model.addAttribute("badge", badgeUserRepository.findBadgeUserById(user.getId()));
        model.addAttribute("sesUser", user1);

        return "userProfile";


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
