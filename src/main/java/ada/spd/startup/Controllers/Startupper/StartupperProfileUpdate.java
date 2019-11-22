package ada.spd.startup.Controllers.Startupper;

import ada.spd.startup.DAO.StartupperDAO;
import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.Repositories.StartupperRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

public class StartupperProfileUpdate {

    private StartupperRepository startupperRepository;

    public StartupperProfileUpdate(StartupperRepository startupperRepository) {
        this.startupperRepository = startupperRepository;
    }

  @PostMapping(value = "startupper/update", consumes = "application/json", produces = "application/json")
    public void updateProfile(@RequestBody Startupper startupper, HttpSession session) {
        Startupper startupper1 = (Startupper) session.getAttribute("Startupper");

      startupper1.setName(startupper.getName());
      startupper1.setSurname(startupper.getSurname());
      startupper1.setAddress(startupper.getAddress());
      startupper1.setCountry(startupper.getCountry());
      startupper1.setCity(startupper.getCity());
      startupper1.setNationallity(startupper.getNationallity());
      startupperRepository.save(startupper1);




    }



}
