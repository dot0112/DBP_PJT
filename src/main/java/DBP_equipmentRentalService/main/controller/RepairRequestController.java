package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.service.search.ItemSearchService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Controller
public class RepairRequestController {
    private final ItemSearchService itemSearchService;

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
    public String searchRepair(@RequestParam(name="way", required = false, defaultValue = "") String way, @RequestParam(name = "searchRepairItem", required = false, defaultValue = "") String searchRepairItem,  Model model, HttpSession session){
        List<Item> searchedItems = new ArrayList<>();

        switch (way){
            case "id":
                Optional<Item> optionalItem = itemSearchService.searchById(searchRepairItem);
                searchedItems = optionalItem.map(Collections::singletonList).orElse(Collections.emptyList());
                break;
            case "name":
                searchedItems = itemSearchService.searchByName(searchRepairItem);
                break;
            case "type":
                searchedItems = itemSearchService.searchByType(searchRepairItem);
                break;
            default:
                searchedItems = new ArrayList<>();
                break;
        }

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if(isLoggedIn == null){
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "repairRequest");
        model.addAttribute("searchRepairItem", searchRepairItem);
        model.addAttribute("items", searchedItems);
        return "layout";
    }

    @PostMapping ("/applyRepair")
    public String applyRepair(@RequestParam("repairItems") String[]  repairItems, HttpSession session){
        RepairRequest repairRequest = new RepairRequest();

        for(String repairItem : repairItems){
            String[] item = repairItem.split(",");
            repairRequest.setItemId(item[0]);
            repairRequest.setUserId((String) session.getAttribute("ID"));
            repairRequest.setItemName(item[1]);
        }
        return "/";
    }
}
