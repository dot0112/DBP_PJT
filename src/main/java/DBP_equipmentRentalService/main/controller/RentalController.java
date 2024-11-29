package DBP_equipmentRentalService.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class RentalController {
    @GetMapping ("/rental")
    public String rental(Model model){
        model.addAttribute("contentFragment", "rental");
        return "layout";
    }
}
