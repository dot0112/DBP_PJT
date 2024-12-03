package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.service.search.ItemSearchService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class RepairRequestController {
    private final ItemSearchService itemSearchService;
    private List<Item> searchedItems;

    @Autowired
    public RepairRequestController(ItemSearchService itemSearchService){
        this.itemSearchService = itemSearchService;
    }

    @GetMapping ("/repairRequest")
    public String repairRequest(Model model, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if(isLoggedIn == null){
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "repairRequest");
        return "layout";
    }

    @GetMapping ("/searchRepair")
    public String searchRepair(@RequestParam(name="type", required = false, defaultValue = "") String type, Model model, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        searchedItems = itemSearchService.searchByType(type);

        if(isLoggedIn == null){
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "repairRequest");
        model.addAttribute("items", searchedItems);
        return "layout";
    }

    @PostMapping ("/applyRepair")
    public String applyRepair(){
        return "layout";
    }
}
