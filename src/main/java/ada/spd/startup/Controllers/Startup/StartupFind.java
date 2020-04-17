package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.DAO.UserDAO;
import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Repositories.StartupRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class StartupFind {


    private StartupRepository startupRepository;

    public StartupFind(StartupRepository startupRepository) {
        this.startupRepository = startupRepository;
    }

//    @PostMapping(value = "startup/find", produces = "application/json")
//    public Iterable<Startup> findByUserId(HttpSession httpSession) {
//        UserDAO startupperDAO = (UserDAO) httpSession.getAttribute("User");
//        return startupRepository.findByUserId(startupperDAO.getId());
//    }

    @RequestMapping(value = "startup/all")
    public Iterable<Startup> findAll() {
        return startupRepository.findAll();
    }


}
