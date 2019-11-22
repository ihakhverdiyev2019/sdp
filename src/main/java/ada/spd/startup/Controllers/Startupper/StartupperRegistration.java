package ada.spd.startup.Controllers.Startupper;


import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.ENUMS.Status;
import ada.spd.startup.Others.GenerateCode;
import ada.spd.startup.Others.SMSSender;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.web.bind.annotation.*;
import ada.spd.startup.Repositories.StartupperRepository;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class StartupperRegistration {
    private StartupperRepository startupperRepository;

    public StartupperRegistration(StartupperRepository startupperRepository) {
        this.startupperRepository = startupperRepository;

    }
    @PostMapping(value = "startupper/registration", consumes = "application/json", produces = "application/json")
    public Startupper registerStartupper(@RequestBody Startupper startupper, HttpSession httpSession) {
        int smsCode;
        smsCode = GenerateCode.codeSMS();

        startupper.setStatus(Status.Hold);
        startupper.setCode(smsCode);
        httpSession.setAttribute("Startupper",startupper);
        SMSSender.smsSender(startupper.getPhoneNo(),smsCode);

        return startupperRepository.save(startupper);
    }


}
