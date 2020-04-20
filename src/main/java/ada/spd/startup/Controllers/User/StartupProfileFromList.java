//package ada.spd.startup.Controllers.User;
//
//
//import org.python.core.PyObject;
//import org.python.util.PythonInterpreter;
//import ada.spd.startup.Domains.Startup;
//import ada.spd.startup.Domains.User;
//import ada.spd.startup.ENUMS.RoleENUM;
//import ada.spd.startup.ENUMS.StartupJoin;
//import ada.spd.startup.Repositories.StartupRepository;
//import ada.spd.startup.Repositories.UserStartupRepository;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Controller
//public class StartupProfileFromList {
//
//    private StartupRepository startupRepository;
//    private UserStartupRepository userStartupRepository;
//
////    interface PyFunction {
////        public List<Integer> sumFunction(String name, int n);
////    }
//
//    public StartupProfileFromList(StartupRepository startupRepository, UserStartupRepository userStartupRepository) {
//        this.startupRepository = startupRepository;
//        this.userStartupRepository = userStartupRepository;
//    }
//
//    @RequestMapping(value = "/startup/{id}/view")
//    public String startupDetails(@PathVariable String id, Model model, HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//        if (user != null) {
//            model.addAttribute("user", user);
//            Startup startup = startupRepository.findById(Long.parseLong(id)).get();
//            model.addAttribute("startup", startup);
//            model.addAttribute("founder", userStartupRepository.findFounderByStartupIdAndUserRights(startup.getId(), RoleENUM.Founder));
//            model.addAttribute("userStartup", userStartupRepository.findByStartupAndUser(startup.getId(), user.getId()));
//            model.addAttribute("contributor", userStartupRepository.findUserStartupByStartupId(startup.getId(), RoleENUM.Contributor, StartupJoin.Joined));
//            model.addAttribute("investor", userStartupRepository.findUserStartupByStartupId(startup.getId(), RoleENUM.Investor, StartupJoin.Joined));
//
////        PythonInterpreter interpreter = new PythonInterpreter();
////
////
////        try {
////            interpreter.execfile("kmeans.py");
////            PyObject sumFunction = interpreter.get("recommend");
////            PyFunction function = (PyFunction) sumFunction.__tojava__(PyFunction.class);
////            System.out.println( function.sumFunction(startup.getStartupName(), 2));
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            e.getMessage();
////            e.toString();
////        }
//            return "StartupProfileFromList";
//        } else
//            return "redirect:/login";
//
//
//    }
//}
