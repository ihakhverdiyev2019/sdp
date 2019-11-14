package ada.spd.startup.Controllers;


import ada.spd.startup.Domains.Startupper;
import org.springframework.web.bind.annotation.*;
import ada.spd.startup.Repositories.StartupperRepository;


@RestController
public class StartupperRegistration {
    private StartupperRepository startupperRepository;

    public StartupperRegistration(StartupperRepository startupperRepository) {
        this.startupperRepository = startupperRepository;
    }

    @PostMapping(
            value = "startupper/registration", consumes = "application/json", produces = "application/json")
    public Startupper registerStartupper(@RequestBody Startupper startupper) {
        return startupperRepository.save(startupper);
    }


}
