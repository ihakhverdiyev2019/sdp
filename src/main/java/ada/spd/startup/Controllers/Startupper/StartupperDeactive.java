package ada.spd.startup.Controllers.Startupper;

import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.ENUMS.Status;
import ada.spd.startup.Repositories.StartupperRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class StartupperDeactive {

private StartupperRepository startupperRepository;


    public StartupperDeactive(StartupperRepository startupperRepository) {
        this.startupperRepository = startupperRepository;
    }

    @PostMapping(value = "startupper/deactive", consumes = "application/json", produces = "application/json")
    public void deactiveStartupper(@RequestBody Startupper startupper) {
        Startupper startupper1 = startupperRepository.findById(startupper.getId()).get();
        startupper1.setStatus(Status.Deactive);
        startupperRepository.save(startupper1);
    }
}
