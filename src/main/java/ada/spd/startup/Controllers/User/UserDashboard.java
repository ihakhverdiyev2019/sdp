package ada.spd.startup.Controllers.User;

import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.ENUMS.StatusEnum;
import ada.spd.startup.ENUMS.ToDoEnum;
import ada.spd.startup.Repositories.ToDoRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserDashboard {

    private UserStartupRepository userStartupRepository;
    private ToDoRepository toDoRepository;


    public UserDashboard(UserStartupRepository userStartupRepository, ToDoRepository toDoRepository) {
        this.userStartupRepository = userStartupRepository;
        this.toDoRepository = toDoRepository;
    }

    @GetMapping(value = "/dashboard")
    public String userDashboard(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            List<UserStartup> userStartup = userStartupRepository.findUserStartupByuserID(user.getId(), RoleENUM.Founder);
            int goal = 0;
            int invested = 0;
            int view = 0;
            int contributor = 0;
            int allTodo = 0;
            int newTodo = 0;
            int cancelTodo = 0;
            int completeTodo = 0;
            for (int i = 0; i < userStartup.size(); i++) {
                view += userStartup.get(i).getStartup().getClickcount();
                goal += userStartup.get(i).getStartup().getInvestAmount();
                invested += userStartup.get(i).getStartup().getInvested();
                allTodo += toDoRepository.findAllTodo(userStartup.get(i).getStartup().getId()).size();
                newTodo += toDoRepository.findByStartup(userStartup.get(i).getStartup().getId(), ToDoEnum.New).size();
                cancelTodo += toDoRepository.findByStartup(userStartup.get(i).getStartup().getId(), ToDoEnum.Cancel).size();
                completeTodo += toDoRepository.findByStartup(userStartup.get(i).getStartup().getId(), ToDoEnum.Complete).size();
                contributor += userStartupRepository.findByStartupIdAndUserRights(userStartup.get(i).getStartup().getId(), RoleENUM.Contributor).size();

            }
            model.addAttribute("view", view);
            model.addAttribute("goal", goal);
            model.addAttribute("invested", invested);
            model.addAttribute("contributor", contributor);
            model.addAttribute("allTodo", allTodo);
            model.addAttribute("newTodo", newTodo);
            model.addAttribute("cancelTodo", cancelTodo);
            model.addAttribute("completeTodo", completeTodo);
            if (goal != 0) {
                model.addAttribute("percentage", (invested * 100) / goal);
            } else {
                model.addAttribute("percentage", 0);

            }


            return "dashboard";
        } else
            return "redirect:/login";
    }
}
