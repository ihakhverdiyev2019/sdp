package ada.spd.startup.Controllers.ToDo;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class ToDoList {

    private ToDoRepository toDoRepository;
    private StartupRepository startupRepository;

    public ToDoList(ToDoRepository toDoRepository, StartupRepository startupRepository) {
        this.toDoRepository = toDoRepository;
        this.startupRepository = startupRepository;
    }


    @GetMapping(value = "/startup/{id}/todo")
    public String registerToDo(@PathVariable String id, Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            Startup startup = startupRepository.findById(Long.parseLong(id)).get();
            User user = (User) httpSession.getAttribute("user");
            model.addAttribute("user", user);
            model.addAttribute("detail", startup);
            model.addAttribute("toDo", toDoRepository.findAllByStartup(startup));
            return "todoList";
        } else
            return "redirect:/login";
    }

}
