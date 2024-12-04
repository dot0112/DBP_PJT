package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.Returns;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ReturnController {
    @GetMapping ("/return")
    public String returnitem(Model model, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if(isLoggedIn == null){
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "return");
        return "layout";
    }
}
