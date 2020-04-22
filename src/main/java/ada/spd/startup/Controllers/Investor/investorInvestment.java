package ada.spd.startup.Controllers.Investor;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class investorInvestment {
    private StartupRepository startupRepository;
    private UserStartupRepository userStartupRepository;


    public investorInvestment(StartupRepository startupRepository, UserStartupRepository userStartupRepository) {
        this.startupRepository = startupRepository;
        this.userStartupRepository = userStartupRepository;
    }

    @RequestMapping(value = "/startup/{id}/invest")
    public String investMoney(@PathVariable String id, @RequestParam("amount") String amount, HttpSession httpSession) {
        if (httpSession.getAttribute("investor") != null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
            LocalDateTime now = LocalDateTime.now();
            Startup startup = startupRepository.findById(Long.parseLong(id)).get();
            User user = (User) httpSession.getAttribute("investor");
            UserStartup userStartup = new UserStartup();
            userStartup.setUser(user);
            userStartup.setRole("Investor");
            userStartup.setRights(RoleENUM.Investor);
            userStartup.setInvest(Double.parseDouble(amount));
            userStartup.setStartup(startup);
            userStartup.setDate(dtf.format(now));
            userStartup.setStartupJoin(StartupJoin.WantToJoin);
            userStartup.setInvest(Double.parseDouble(amount));
            userStartupRepository.save(userStartup);


            return "redirect:/istartup/" + id + "/view";
        } else
            return "redirect:/login";
    }
}
