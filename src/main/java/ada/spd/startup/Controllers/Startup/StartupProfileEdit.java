package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Repositories.StartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class StartupProfileEdit {


    private StartupRepository startupRepository;


    public StartupProfileEdit(StartupRepository startupRepository) {
        this.startupRepository = startupRepository;
    }


    @RequestMapping(value = "/startup/{id}/edit")
    public String findStartupProfileEdit(@PathVariable String id, Model model) {
        model.addAttribute("startup", startupRepository.findById(Long.parseLong(id)).get());
        return "startupEdit";


    }


    @RequestMapping(value = "/startup/{id}/post")
    public String StartupProfileEditSave(@Valid @ModelAttribute Startup startup, @PathVariable String id) {

        startupRepository.save(startup);

        return "redirect:/startup/" + id + "/profile";


    }
}

