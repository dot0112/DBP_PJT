package DBP_equipmentRentalService.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class RepairRequestController {
    @GetMapping ("/repairRequest")
    public String repairRequest(Model model){
        model.addAttribute("contentFragment", "repairRequest");
        return "layout";
    }
}
