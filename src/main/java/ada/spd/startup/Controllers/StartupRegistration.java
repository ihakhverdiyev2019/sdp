package ada.spd.startup.Controllers;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.StartupperRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StartupRegistration {


    private StartupRepository startupRepository;
    private StartupperRepository startupperRepository;

    public StartupRegistration(StartupRepository startupRepository, StartupperRepository startupperRepository) {
        this.startupRepository = startupRepository;
        this.startupperRepository = startupperRepository;
    }

//    @PostMapping(
//            value = "startup/registration", consumes = "application/json", produces = "application/json")
//    public void registerStartup(@RequestBody Startup startup, Startupper startupper) {
//
//        startup.setStartupper(startupperRepository.findById(startupper.getId()));
//        startupRepository.save(startup);
//
//
//    }


}


