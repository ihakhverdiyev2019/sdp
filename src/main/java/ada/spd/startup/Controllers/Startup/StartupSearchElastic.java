//package ada.spd.startup.Controllers.Startup;
//
//
//import ada.spd.startup.Domains.Startup;
//import ada.spd.startup.Repositories.StartupElasticSearchRepo;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class StartupSearchElastic {
//
//    private StartupElasticSearchRepo startupElasticSearchRepo;
//
//    public StartupSearchElastic(StartupElasticSearchRepo startupElasticSearchRepo) {
//        this.startupElasticSearchRepo = startupElasticSearchRepo;
//    }
//
//
//    @RequestMapping(value = "/startup/search/{name}")
//    public String findByName(@PathVariable String name, Model model) {
////        model.addAttribute("startup", startupElasticSearchRepo.findByStartupName(name));
//        return "";
//    }
//
//
//}
