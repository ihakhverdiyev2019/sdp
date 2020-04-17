package ada.spd.startup.Controllers.Startup;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Repositories.StartupRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public class StartupRefferal {
private StartupRepository startupRepository;


    public StartupRefferal(StartupRepository startupRepository) {
        this.startupRepository = startupRepository;
    }

//    @GetMapping(value = "startup/{refCode}", produces = "application/json")
//    public Startup findByRefferalCode (@PathVariable(name = "refCode") String refCode) {
//        return startupRepository.findByRefferalCode(refCode);
//    }
}
