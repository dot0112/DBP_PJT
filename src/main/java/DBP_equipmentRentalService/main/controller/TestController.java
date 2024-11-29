package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.service.AdminService;
import DBP_equipmentRentalService.main.service.search.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestController {
    private final AdminService adminService;
    private final ItemSearchService itemSearchService;

    @Autowired
    public TestController(AdminService adminService, ItemSearchService itemSearchService){
        this.adminService = adminService;
        this.itemSearchService = itemSearchService;
    }


    // for test
    @GetMapping("/")
    @ResponseBody
    public String home(){
        List<Item> itemList = itemSearchService.searchByName("%파미가구%");

        for (Item item : itemList) {
            System.out.println(item.getItemId());
        }
        return "home";
    }
}
