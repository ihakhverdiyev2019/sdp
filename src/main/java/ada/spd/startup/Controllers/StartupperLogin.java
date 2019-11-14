package ada.spd.startup.Controllers;

import ada.spd.startup.DAO.StartupperDAO;
import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.Repositories.StartupperRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RestController
public class StartupperLogin {


    private StartupperRepository startupperRepository;

    public StartupperLogin(StartupperRepository startupperRepository) {
        this.startupperRepository = startupperRepository;
    }

    @PostMapping(
            value = "startupper/login", consumes = "application/json", produces = "application/json")
    public StartupperDAO loginStartupper(@RequestBody Startupper startupper, HttpSession session) {
        Optional<Startupper> startupper1 = startupperRepository.findByCredentials(startupper.getLogin(), startupper.getPassword());
        StartupperDAO startupperDAO = new StartupperDAO();
        if ((!startupper1.isEmpty()) && (startupper.getLogin().equals(startupper1.get().getLogin())) && startupper.getPassword().equals(startupper1.get().getPassword()))
            startupperDAO.setId(startupper1.get().getId());
        startupperDAO.setName(startupper1.get().getName());
        startupperDAO.setSurname(startupper1.get().getSurname());
        session.setAttribute("Startupper", startupperDAO);
        return startupperDAO;

    }


}
