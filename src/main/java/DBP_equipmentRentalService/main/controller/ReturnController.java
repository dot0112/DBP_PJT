package DBP_equipmentRentalService.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ReturnController {
    @GetMapping ("/return")
    public String returnitem(Model model){
        model.addAttribute("contentFragment", "return");
        return "layout";
    }
}
