package ada.spd.startup.Controllers.ToDo;


import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.ToDo;
import ada.spd.startup.ENUMS.ToDoEnum;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.ToDoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class ToDoSave {


    private ToDoRepository toDoRepository;
    private StartupRepository startupRepository;

    public ToDoSave(ToDoRepository toDoRepository, StartupRepository startupRepository) {
        this.toDoRepository = toDoRepository;
        this.startupRepository = startupRepository;
    }

    @PostMapping("/startup/{id}/todo/save")
    public String saveCourse(@PathVariable String id, @Valid @ModelAttribute ToDo toDo) {
        Startup startup = startupRepository.findById(Long.parseLong(id)).get();

        toDo.setStartup(startup);
        toDo.setProgress(ToDoEnum.New);
        toDoRepository.save(toDo);

        return "redirect:/startup/" + id + "/todo";

    }

}
