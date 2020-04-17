package ada.spd.startup.Controllers.Startup;


import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Repositories.CategoryRepository;
import ada.spd.startup.Repositories.StartupRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
public class StartupProfileEdit {


    private StartupRepository startupRepository;
    private CategoryRepository categoryRepository;


    public StartupProfileEdit(StartupRepository startupRepository, CategoryRepository categoryRepository) {
        this.startupRepository = startupRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "/startup/{id}/edit")
    public String findStartupProfileEdit(@PathVariable String id, Model model, HttpSession httpSession) {
        model.addAttribute("user", (User) httpSession.getAttribute("user"));
        model.addAttribute("startup", startupRepository.findById(Long.parseLong(id)).get());
        model.addAttribute("category", categoryRepository.findAll());
        return "startupEdit";


    }


    @RequestMapping(value = "/startup/{id}/post")
    public String StartupProfileEditSave(@Valid @ModelAttribute Startup startup, @RequestParam("category") String category, @PathVariable String id) {
        startup.setCategory(category);
        startupRepository.save(startup);

        return "redirect:/startup/" + id + "/profile";


    }
}

