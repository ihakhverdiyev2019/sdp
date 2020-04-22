package ada.spd.startup.Controllers.Investor;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.ENUMS.RoleENUM;
import ada.spd.startup.ENUMS.StartupJoin;
import ada.spd.startup.Repositories.StartupRepository;
import ada.spd.startup.Repositories.UserStartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class InvestorStartupView {
    private StartupRepository startupRepository;
    private UserStartupRepository userStartupRepository;


    public InvestorStartupView(StartupRepository startupRepository, UserStartupRepository userStartupRepository) {
        this.startupRepository = startupRepository;
        this.userStartupRepository = userStartupRepository;
    }

    @RequestMapping(value = "/istartup/{id}/view", method = RequestMethod.GET)
    public String startupProfileDisplayforInvestor(@PathVariable String id, Model model, HttpSession httpSession) throws IOException {
        User user = (User) httpSession.getAttribute("investor");

        if (user != null) {


            model.addAttribute("user", user);


            Startup startup = startupRepository.findById(Long.parseLong(id)).get();
            int click = startup.getClickcount();

            try {
                if (userStartupRepository.findByStartupAndUser(startup.getId(), user.getId()).getStartup().getId() != startup.getId())
                    startup.setClickcount(click + 1);
            } catch (Exception e) {
                startup.setClickcount(click + 1);

                System.out.println(e.toString());
            }


            URL url = new URL("http://31.171.108.141:1801/" + startup.getStartupName() + "/2");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine = in.readLine();
            String[] arrOfStr = inputLine.split(",");

            model.addAttribute("similar1", startupRepository.findById(Long.parseLong(arrOfStr[0])).get());

            model.addAttribute("similar2", startupRepository.findById(Long.parseLong(arrOfStr[1])).get());


            in.close();

            //print result
            System.out.println(inputLine.toString());

            model.addAttribute("startup", startup);

            model.addAttribute("founder", userStartupRepository.findFounderByStartupIdAndUserRights(startup.getId(), RoleENUM.Founder));

            model.addAttribute("contributor", userStartupRepository.findUserStartupByStartupId(startup.getId(), RoleENUM.Contributor, StartupJoin.Joined));

            model.addAttribute("investor", userStartupRepository.findUserStartupByStartupId(startup.getId(), RoleENUM.Investor, StartupJoin.Joined));


            startupRepository.save(startup);
            return "startupDisplayInvestor";

        } else
            return "redirect:/login";
    }

}
