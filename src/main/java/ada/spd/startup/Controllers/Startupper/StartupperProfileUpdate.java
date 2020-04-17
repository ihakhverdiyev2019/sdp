package ada.spd.startup.Controllers.Startupper;

import ada.spd.startup.Domains.User;
import ada.spd.startup.Repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

public class StartupperProfileUpdate {

    private UserRepository startupperRepository;

    public StartupperProfileUpdate(UserRepository startupperRepository) {
        this.startupperRepository = startupperRepository;
    }

    @PostMapping(value = "startupper/update", consumes = "application/json", produces = "application/json")
    public void updateProfile(@RequestBody User startupper, HttpSession session) {
        User startupper1 = (User) session.getAttribute("User");

        startupper1.setName(startupper.getName());
        startupper1.setSurname(startupper.getSurname());
        startupper1.setAddress(startupper.getAddress());
        startupper1.setCountry(startupper.getCountry());
        startupper1.setCity(startupper.getCity());
        startupper1.setNationallity(startupper.getNationallity());
        startupperRepository.save(startupper1);


    }


}
