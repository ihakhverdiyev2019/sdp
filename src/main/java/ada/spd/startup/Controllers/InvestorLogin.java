package ada.spd.startup.Controllers;


import ada.spd.startup.DAO.InvestorDAO;
import ada.spd.startup.Domains.Investor;
import ada.spd.startup.Repositories.InvestorRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class InvestorLogin {


    private InvestorRepository investorRepository;


    public InvestorLogin(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }





    @PostMapping(value = "investor/login", consumes = "application/json", produces = "application/json")
    public InvestorDAO loginInvestor
            (@RequestBody Investor investor, HttpSession session) {
        Optional<Investor> investor1 = investorRepository.findByCredentials(investor.getLogin(), investor.getPassword());
        InvestorDAO investorDAO = new InvestorDAO();
        if ((!investor1.isEmpty()) && (investor.getLogin().equals(investor1.get().getLogin())) && investor.getPassword().equals(investor1.get().getPassword()))
            investorDAO.setId(investor1.get().getId());
        investorDAO.setName(investor1.get().getName());
        investorDAO.setSurname(investor1.get().getSurname());
        session.setAttribute("Investor", investorDAO);
        return investorDAO;

    }
}
