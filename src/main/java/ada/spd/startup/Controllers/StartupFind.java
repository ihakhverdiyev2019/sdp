package ada.spd.startup.Controllers;


import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.Repositories.StartupRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StartupFind {


    private StartupRepository startupRepository;

    public StartupFind(StartupRepository startupRepository) {
        this.startupRepository = startupRepository;
    }

    @PostMapping(
            value = "startup/find", consumes = "application/json", produces = "application/json")
    public List<Startup> findByID(@RequestBody Startupper startupper) {
        return  startupRepository.findByStartupperId(startupper.getId());
    }




}
