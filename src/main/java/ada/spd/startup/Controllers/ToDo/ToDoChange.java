package ada.spd.startup.Controllers.ToDo;

import ada.spd.startup.Domains.ToDo;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class ToDoChange {


    private ToDoRepository toDoRepository;
    private StartupRepository startupRepository;

    public ToDoChange(ToDoRepository toDoRepository, StartupRepository startupRepository) {
        this.toDoRepository = toDoRepository;
        this.startupRepository = startupRepository;
    }

    @GetMapping(value = "/startup/{id}/edit/{tid}")
    public String changeTodo(HttpSession httpSession, @PathVariable(name = "tid", required = true) String tid, @PathVariable(name = "id", required = true) String id, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("startup", startupRepository.findById(Long.parseLong(id)).get());
        model.addAttribute("todo", toDoRepository.findById(Long.parseLong(tid)).get());
        return "addTodo";


    }

}
