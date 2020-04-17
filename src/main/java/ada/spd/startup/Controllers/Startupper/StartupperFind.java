package ada.spd.startup.Controllers.Startupper;

import ada.spd.startup.Domains.User;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class StartupperFind {
    private UserRepository startupperRepository;

    public StartupperFind(UserRepository startupperRepository) {
        this.startupperRepository = startupperRepository;
    }

    @GetMapping(
            value = "startupper/find", consumes = "application/json", produces = "application/json")
    public Optional<User> findByID(@RequestBody User startupper) {
        return startupperRepository.findById(startupper.getId());

    }

    @GetMapping(
            value = "startupper/findall", consumes = "application/json", produces = "application/json")
    public Iterable<User> findAll() {
        return startupperRepository.findAll();
    }
}
