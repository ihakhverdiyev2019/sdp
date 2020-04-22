package ada.spd.startup.Controllers.Investor;

import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class investorRequestCancel {

    private UserStartupRepository userStartupRepository;

    public investorRequestCancel(UserStartupRepository userStartupRepository) {
        this.userStartupRepository = userStartupRepository;
    }

    @GetMapping(value = "/invest/{tid}/cancel")
    public String cancelInvestment(@PathVariable String tid, @PathVariable String cid, HttpSession httpSession) {
        if (httpSession.getAttribute("investor") != null) {


            userStartupRepository.delete(userStartupRepository.findById(Long.parseLong(cid)).get());

            return "redirect:/invest";
        } else
            return "redirect:/login";
    }
}
