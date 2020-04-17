package ada.spd.startup.Controllers.ToDo.Filter;


import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.ToDoEnum;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TodoFilter {

    private ToDoRepository toDoRepository;
    private StartupRepository startupRepository;

    public TodoFilter(ToDoRepository toDoRepository, StartupRepository startupRepository) {
        this.toDoRepository = toDoRepository;
        this.startupRepository = startupRepository;
    }

    @RequestMapping("/startup/{id}/todo/done")
    public String doneFilter(@PathVariable String id, Model model , HttpSession httpSession) {
        Startup startup = startupRepository.findById(Long.parseLong(id)).get();
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("startup", startup);
        model.addAttribute("doneToDo", toDoRepository.findByStartup(Long.parseLong(id), ToDoEnum.Complete)
        );

        return "completeToDo";

    }

    @RequestMapping("/startup/{id}/todo/cancel")
    public String cancelFilter(@PathVariable String id, Model model,HttpSession httpSession) {
        Startup startup = startupRepository.findById(Long.parseLong(id)).get();
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("startup", startup);
        model.addAttribute("cancelToDo", toDoRepository.findByStartup(Long.parseLong(id), ToDoEnum.Cancel)
        );

        return "cancelToDo";

    }

    @RequestMapping("/startup/{id}/todo/new")
    public String newFilter(@PathVariable String id, Model model,HttpSession httpSession) {
        Startup startup = startupRepository.findById(Long.parseLong(id)).get();
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("startup", startup);
        model.addAttribute("newToDo", toDoRepository.findByStartup(Long.parseLong(id), ToDoEnum.New));

        return "newToDo";

    }
}
