package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.service.LectureRoomService;
import DBP_equipmentRentalService.main.service.ManageItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Controller
public class ManageItemController {
    private final LectureRoomService lectureRoomService;
    private final ManageItemService manageItemService;

    @Autowired
    public ManageItemController(LectureRoomService lectureRoomService, ManageItemService manageItemService){
        this.lectureRoomService = lectureRoomService;
        this.manageItemService = manageItemService;
    }

    @GetMapping("/manageItem")
    public String manageItem(@RequestParam(required = false) String selected, Model model, HttpSession session){
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        String role = (String) session.getAttribute("role");
        List<LectureRoom> locations = lectureRoomService.findAll();

        if(isLoggedIn == null){
            isLoggedIn = false;
        }

        if(selected == null)
            selected = "itemRegistration";

        model.addAttribute("selected", selected);
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("role", role);
        model.addAttribute("locations", locations);
        model.addAttribute("contentFragment", "manageItem");

        return "layout";
    }

    @PostMapping("/registerItems")
    public String registerItems(@RequestParam(required = false) String itemId, @RequestParam String itemName, @RequestParam String itemType, @RequestParam Integer counts, @RequestParam String location, HttpSession session){
        String buildingName, roomNumber;

        String[] locations = location.split("-");
        buildingName = locations[0];
        roomNumber = locations[1];

        try{
            manageItemService.manageItems((String) session.getAttribute("ID"), counts, itemName, itemType, roomNumber, buildingName);
        }
        catch (Exception e){
            return "redirect:/?registerItemsError=true&registerItemsErrorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }
        return "redirect:/?registerItemsSuccess";
    }
}
