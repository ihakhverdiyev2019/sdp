package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.Repositories.StartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class StartupSave {

    private StartupRepository startupRepository;


    public StartupSave(StartupRepository startupRepository) {
        this.startupRepository = startupRepository;
    }

    @PostMapping(value = "/startup/save")
    public String registerStartup(@Valid @ModelAttribute Startup startup) {

        startupRepository.save(startup);
        return "redirect:";


    }
}
