package ada.spd.startup.Controllers;


import ada.spd.startup.DAO.StartupperDAO;
import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.Repositories.StartupRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class StartupFind {


    private StartupRepository startupRepository;

    public StartupFind(StartupRepository startupRepository) {
        this.startupRepository = startupRepository;
    }




    @PostMapping(value = "startup/find", produces = "application/json")
    public Iterable<Startup> findByStartupperID (HttpSession httpSession) {
        StartupperDAO startupperDAO = (StartupperDAO) httpSession.getAttribute("Startupper");
        return startupRepository.findByStartupperId(startupperDAO.getId());
    }




}