package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.service.ItemService;
import DBP_equipmentRentalService.main.service.search.ItemSearchService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    private final ItemService itemService;
    private final ItemSearchService itemSearchService;
    private boolean isSearched;

    @Autowired
    public MainController(ItemService itemService, ItemSearchService itemSearchService){
        this.itemService = itemService;
        this.itemSearchService = itemSearchService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session){
        List<Item> items = itemService.findItems();
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        isSearched = false;

        if(isLoggedIn == null){
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "main");
        model.addAttribute("isSearched", isSearched);
        model.addAttribute("items", items);
        return "layout";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "type", required = false, defaultValue = "") String type, Model model, HttpSession session){
        isSearched = true;
        List<Item> items = itemSearchService.searchByType(type);
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if(isLoggedIn == null){
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "main");
        model.addAttribute("isSearched", isSearched);
        model.addAttribute("items", items);
        return "layout";
    }
}
