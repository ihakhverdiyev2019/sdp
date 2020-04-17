package ada.spd.startup.Controllers.Startupper;


import ada.spd.startup.Domains.VerificationToken;
import ada.spd.startup.Others.GenerateCode;
import ada.spd.startup.Others.SendEmail;
import ada.spd.startup.Repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartupperInvite {
    @Autowired
    private SendEmail sendEmail;
    private VerificationTokenRepository uniqueIDRepository;


    public StartupperInvite(VerificationTokenRepository uniqueIDRepository) {
        this.uniqueIDRepository = uniqueIDRepository;
    }



    @GetMapping(value = "startupper/invite")
    public void inviteMember(@RequestParam("refferalCode") String refCode,@RequestParam("email") String email) {
        VerificationToken verificationToken =new VerificationToken();
        int uniqueID =GenerateCode.codeSMS();
        System.out.println(uniqueID);
        verificationToken.setToken(uniqueID);
        uniqueIDRepository.save(verificationToken);
        String text = "http://localhost:8080/startup/"+refCode +"/" +uniqueID;
        sendEmail.sendMail(email,text);

    }
}
