package ada.spd.startup.Controllers.Investor;


import ada.spd.startup.Domains.Investor;
import ada.spd.startup.Domains.Startupper;
import ada.spd.startup.Repositories.InvestorRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestorRegistration {

private InvestorRepository investorRepository;


    public InvestorRegistration(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @PostMapping(
            value = "investor/registration", consumes = "application/json", produces = "application/json")
    public Investor registerInvestor(@RequestBody Investor investor) {
        return investorRepository.save(investor);
    }


}


