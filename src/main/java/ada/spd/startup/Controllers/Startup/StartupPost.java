package ada.spd.startup.Controllers.Startup;

import ada.spd.startup.DAO.StartupperDAO;
import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.StartupperRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RestController
public class StartupPost {


    private StartupRepository startupRepository;
    private StartupperRepository startupperRepository;

    public StartupPost(StartupRepository startupRepository, StartupperRepository startupperRepository) {
        this.startupRepository = startupRepository;
        this.startupperRepository = startupperRepository;
    }

    @PostMapping(value = "startup/post",  produces = "application/json")
    public void registerStartup(@RequestBody Startup startup, HttpSession httpSession) {
        StartupperDAO startupperDAO = (StartupperDAO) httpSession.getAttribute("Startupper");
        if (startupperRepository.findById(startupperDAO.getId()).isPresent()) {
            startup.setStartupper(startupperRepository.findById(startupperDAO.getId()).get());
            startup.getStartupper().addStartup(startup);
            startupRepository.save(startup);
            startupperRepository.save(startup.getStartupper());

        }
    }


}


