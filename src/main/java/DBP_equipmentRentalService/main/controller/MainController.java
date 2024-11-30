package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class MainController {
    private final ItemService itemService;

    @Autowired
    public MainController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session){
        List<Item> items = itemService.findItems();
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if(isLoggedIn == null){
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "main");
        model.addAttribute("items", items);
        return "layout";
    }
}
