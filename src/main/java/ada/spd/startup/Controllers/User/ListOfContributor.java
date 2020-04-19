//package ada.spd.startup.Controllers.User;
//
//import ada.spd.startup.Domains.User;
//
//import ada.spd.startup.Repositories.UserRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class ListOfContributor {
//
//    private UserRepository userRepository;
//
//
//    public ListOfContributor(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping(value = "/contributor")
//    public String listOfUser(Model model, HttpSession httpSession) {
//        model.addAttribute("user", (User) httpSession.getAttribute("user"));
//        model.addAttribute("contributor", userRepository.findAll());
//        return "";
//    }
//
//}
