package ada.spd.startup.Controllers.ToDo;

import ada.spd.startup.Domains.ToDo;
import ada.spd.startup.ENUMS.ToDoEnum;
import ada.spd.startup.Repositories.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
public class ToDoDone {
    private ToDoRepository toDoRepository;

    public ToDoDone(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;

    }

    @GetMapping(value = "/startup/{id}/done/{tid}")
    public String registerStartup(@PathVariable String id, @PathVariable String tid, HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            ToDo toDo = toDoRepository.findById(Long.parseLong(tid)).get();
            toDo.setProgress(ToDoEnum.Complete);


            toDoRepository.save(toDo);

            return "redirect:/startup/" + id + "/todo";
        } else
            return "redirect:/login";
    }
}
