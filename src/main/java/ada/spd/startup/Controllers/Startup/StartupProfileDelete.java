package ada.spd.startup.Controllers.Startup;

import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class StartupProfileDelete {


    private StartupRepository startupRepository;
    private UserStartupRepository userStartupRepository;


    public StartupProfileDelete(StartupRepository startupRepository, UserStartupRepository userStartupRepository) {
        this.startupRepository = startupRepository;
        this.userStartupRepository = userStartupRepository;
    }

    @RequestMapping(value = "/startup/{id}/delete")
    public String findStartupProfileDelete(@PathVariable String id) {

        userStartupRepository.deleteAll(userStartupRepository.findUserStartupByStartupId(Long.parseLong(id)));
        startupRepository.delete(startupRepository.findById(Long.parseLong(id)).get());

        return "redirect:/startup/list";


    }
}
