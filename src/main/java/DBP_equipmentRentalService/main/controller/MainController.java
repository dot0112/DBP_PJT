package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.service.search.ItemSearchService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    private final ItemSearchService itemSearchService;
    //    private EquipmentHistoryService equipmentHistoryService;
    private boolean isSearched;

//    @Autowired
//    public MainController(ItemSearchService itemSearchService, EquipmentHistoryService equipmentHistoryService){
//        this.itemSearchService = itemSearchService;
//        this.equipmentHistoryService = equipmentHistoryService;
//    }

    @Autowired
    public MainController(ItemSearchService itemSearchService) {
        this.itemSearchService = itemSearchService;
    }


    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        List<Item> allItems = itemSearchService.searchAll();
        Collections.shuffle(allItems);
        List<Item> items = allItems.subList(0, Math.min(10, allItems.size()));

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        isSearched = false;
        String role = (String) session.getAttribute("role");

        if (isLoggedIn == null) {
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("role", role);
        model.addAttribute("contentFragment", "main");
        model.addAttribute("isSearched", isSearched);
        model.addAttribute("items", items);
        model.addAttribute("equipment", "");
        model.addAttribute("how", "type");
        return "layout";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "how", required = false, defaultValue = "type") String how, @RequestParam(name = "equipment", required = false, defaultValue = "") String equipment, Model model, HttpSession session) {
        isSearched = true;
        List<Item> items = new ArrayList<>();

        switch (how) {
            case "id":
                Optional<Item> optionalItem = itemSearchService.searchById(equipment);
                items = optionalItem.map(Collections::singletonList).orElse(Collections.emptyList());
                break;
            case "name":
                items = itemSearchService.searchByName(equipment);
                break;
            case "type":
                items = itemSearchService.searchByType(equipment);
                break;
            default:
                items = new ArrayList<>();
                break;
        }

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if (isLoggedIn == null) {
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "main");
        model.addAttribute("isSearched", isSearched);
        model.addAttribute("equipment", equipment);
        model.addAttribute("items", items);
        return "layout";
    }

    @GetMapping("/{itemId}")
    public String getItemHistory(@PathVariable String itemId, Model model) {
        return "itemLog";
    }
}
