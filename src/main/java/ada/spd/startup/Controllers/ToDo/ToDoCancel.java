package ada.spd.startup.Controllers.ToDo;

import ada.spd.startup.Domains.ToDo;
import ada.spd.startup.ENUMS.ToDoEnum;
import ada.spd.startup.Repositories.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ToDoCancel {
    private ToDoRepository toDoRepository;

    public ToDoCancel(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;

    }

    @GetMapping(value = "/startup/{id}/cancel/{tid}")
    public String registerStartup(@PathVariable String id,@PathVariable String tid) {

        ToDo toDo = toDoRepository.findById(Long.parseLong(tid)).get();
        toDo.setProgress(ToDoEnum.Cancel);


        toDoRepository.save(toDo);

        return "redirect:/startup/"+id+"/todo";
    }
}
