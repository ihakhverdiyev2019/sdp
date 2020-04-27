package ada.spd.startup.Controllers.User;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.ENUMS.ToDoEnum;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.ToDoRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.Collection;

@Controller
public class EnterProject {


    private StartupRepository startupRepository;
    private ToDoRepository toDoRepository;
    private UserStartupRepository userStartupRepository;
    private static DecimalFormat df2 = new DecimalFormat("#.##");


    public EnterProject(StartupRepository startupRepository, ToDoRepository toDoRepository, UserStartupRepository userStartupRepository) {
        this.startupRepository = startupRepository;
        this.toDoRepository = toDoRepository;
        this.userStartupRepository = userStartupRepository;
    }

    @GetMapping(value = "/startup/{tid}")
    public String projectWorkplace(@PathVariable String tid, Model model, HttpSession httpSession) {
        User user = null;
        if (httpSession.getAttribute("investor") != null) {
            user = (User) httpSession.getAttribute("investor");
        }
        if (httpSession.getAttribute("user") != null) {
            user = (User) httpSession.getAttribute("user");
        }


        if (user != null) {
            Startup startup = startupRepository.findById(Long.parseLong(tid)).get();
            model.addAttribute("startup", startupRepository.findById(Long.parseLong(tid)).get());
            model.addAttribute("user", user);
            model.addAttribute("allTodo", toDoRepository.findAllTodo(Long.parseLong(tid)).size());
            model.addAttribute("newTodo", toDoRepository.findByStartup(Long.parseLong(tid), ToDoEnum.New).size());
            model.addAttribute("completeTod", toDoRepository.findByStartup(Long.parseLong(tid), ToDoEnum.Complete).size());
            model.addAttribute("cancelTodo", toDoRepository.findByStartup(Long.parseLong(tid), ToDoEnum.Cancel).size());
            model.addAttribute("contributor", userStartupRepository.findByStartupIdAndUserRights(Long.parseLong(tid), RoleENUM.Contributor).size());
            model.addAttribute("percentage", df2.format((startup.getInvested() * 100) / startup.getInvestAmount()));

            model.addAttribute("cont", userStartupRepository.findUserStartupByStartupId(Long.parseLong(tid), RoleENUM.Contributor, StartupJoin.Joined));

            return "projectDashboard";
        } else
            return "redirect:/login";

    }
}