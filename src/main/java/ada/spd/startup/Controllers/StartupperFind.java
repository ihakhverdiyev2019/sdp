package ada.spd.startup.Controllers;

import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.Repositories.StartupperRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class StartupperFind {
    private StartupperRepository startupperRepository;

    public StartupperFind(StartupperRepository startupperRepository) {
        this.startupperRepository = startupperRepository;
    }

    @GetMapping(
            value = "startupper/find", consumes = "application/json", produces = "application/json")
    public Optional<Startupper> findByID(@RequestBody Startupper startupper) {
        return startupperRepository.findById(startupper.getId());

    }
    @GetMapping(
            value = "startupper/findall", consumes = "application/json", produces = "application/json")
    public Iterable <Startupper> findAll() {
        return startupperRepository.findAll();
    }
}
