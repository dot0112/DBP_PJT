package DBP_equipmentRentalService.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model){
        boolean isLoggedIn = false;
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "main");
        return "layout";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
