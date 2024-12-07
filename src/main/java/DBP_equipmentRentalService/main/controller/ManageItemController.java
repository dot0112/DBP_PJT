package DBP_equipmentRentalService.main.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManageItemController {

    @GetMapping("/manageItem")
    public String manageItem(@RequestParam(required = false) String selected, Model model, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        String role = (String) session.getAttribute("role");

        if(isLoggedIn == null){
            isLoggedIn = false;
        }

        if(selected == null)
            selected = "itemRegistration";

        model.addAttribute("selected", selected);
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("role", role);
        model.addAttribute("contentFragment", "manageItem");

        return "layout";
    }
}
