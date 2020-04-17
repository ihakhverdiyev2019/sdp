package ada.spd.startup.Controllers.Startup;

import ada.spd.startup.Domains.Startup;
import ada.spd.startup.Domains.User;
import ada.spd.startup.Repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller

public class StartupNew {

    private CategoryRepository categoryRepository;

    public StartupNew(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/startup/create")
    public String newTodo(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        Startup startup = new Startup();


        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("startup", startup);
        model.addAttribute("user", user);


        return "addNewProject";

    }
}
