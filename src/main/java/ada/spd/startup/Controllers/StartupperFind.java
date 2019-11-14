package ada.spd.startup.Controllers;

import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.Repositories.StartupperRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class StartupperFind {
    private StartupperRepository startupperRepository;

    public StartupperFind(StartupperRepository startupperRepository) {
        this.startupperRepository = startupperRepository;
    }

    @PostMapping(
            value = "startupper/find", consumes = "application/json", produces = "application/json")
    public Optional<Startupper> findByID(@RequestBody Startupper startupper) {
        return startupperRepository.findById(startupper.getId());

    }
    @PostMapping(
            value = "startupper/findall", consumes = "application/json", produces = "application/json")
    public Iterable <Startupper> findAll() {
        return startupperRepository.findAll();
    }
}
