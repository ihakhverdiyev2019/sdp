package ada.spd.startup.Controllers;


import ada.spd.startup.DAO.StartupperDAO;
import ada.spd.startup.Domains.SMS;
import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.ENUMS.Status;
import ada.spd.startup.Repositories.StartupperRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class StartupperVerify {
    private StartupperRepository startupperRepository;


    public StartupperVerify(StartupperRepository startupperRepository) {
        this.startupperRepository = startupperRepository;
    }


    @PostMapping(value = "startupper/verify", produces = "application/json")
    public void verifyProfile (@RequestBody SMS sms, HttpSession httpSession) {
        Startupper startupper = (Startupper) httpSession.getAttribute("Startupper");
if(startupper.getCode()==sms.getSmsCode()){
    startupper.setStatus(Status.Active);
    startupperRepository.save(startupper);
}

    }
}
