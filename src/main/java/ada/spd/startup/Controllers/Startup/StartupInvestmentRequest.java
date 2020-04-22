package ada.spd.startup.Controllers.Startup;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Domains.UserStartup;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class StartupInvestmentRequest {
    private StartupRepository startupRepository;
    private UserStartupRepository userStartupRepository;


    public StartupInvestmentRequest(StartupRepository startupRepository, UserStartupRepository userStartupRepository) {
        this.startupRepository = startupRepository;
        this.userStartupRepository = userStartupRepository;
    }

    @RequestMapping(value = "/request/{id}")
    public String investList(@PathVariable String id, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("startupList", startupRepository.findAll());
            model.addAttribute("startupSession", userStartupRepository.findByUserAndStartupJoin(user.getId(), StartupJoin.WantToJoin));
            model.addAttribute("request", userStartupRepository.findUserStartupByStartupId(Long.parseLong(id), RoleENUM.Investor, StartupJoin.WantToJoin));

            model.addAttribute("result", ((Collection<?>) startupRepository.findAll()).size());
            return "startupRequest";
        } else
            return "redirect:/login";
    }


    @RequestMapping(value = "/request/{id}/invest/{cid}/accept")
    public String acceptInvest(@PathVariable String id, @PathVariable String cid, HttpSession httpSession) {
        Startup startup = startupRepository.findById(Long.parseLong(id)).get();
        UserStartup userStartup = userStartupRepository.findById(Long.parseLong(cid)).get();

        startup.setInvested(startup.getInvested() + userStartup.getInvest());
        startupRepository.save(startup);
        userStartup.setStartupJoin(StartupJoin.Joined);
        userStartupRepository.save(userStartup);


        return "redirect:/request/" + id;
    }

    @RequestMapping(value = "/request/{id}/invest/{cid}/reject")
    public String rejectReject(@PathVariable String id, @PathVariable String cid, HttpSession httpSession) {

        UserStartup userStartup = userStartupRepository.findById(Long.parseLong(cid)).get();

        userStartupRepository.delete(userStartup);


        return "redirect:/request/" + id;
    }

}
