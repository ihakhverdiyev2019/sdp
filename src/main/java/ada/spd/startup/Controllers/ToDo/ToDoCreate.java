package ada.spd.startup.Controllers.ToDo;


import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.ToDo;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.ToDoEnum;
import ada.spd.startup.Repositories.StartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class ToDoCreate {
    private StartupRepository startupRepository;


    public ToDoCreate(StartupRepository startupRepository) {
        this.startupRepository = startupRepository;
    }

    @RequestMapping("/startup/{id}/todo/create")
    public String newTodo(@PathVariable String id, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
if (user != null) {
    ToDo toDo = new ToDo();
    toDo.setProgress(ToDoEnum.New);
    model.addAttribute("user", user);
    Startup startup = startupRepository.findById(Long.parseLong(id)).get();

    model.addAttribute("todo", toDo);
    model.addAttribute("startup", startup);


    return "addTodo";
}else
    return "redirect:/login";

    }


}
