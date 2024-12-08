package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.service.RentalService;
import DBP_equipmentRentalService.main.service.search.ItemSearchService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class RentalController {
    private final ItemSearchService itemSearchService;
    private final RentalService rentalService;

    @Autowired
    public RentalController(ItemSearchService itemSearchService, RentalService rentalService) {
        this.itemSearchService = itemSearchService;
        this.rentalService = rentalService;
    }

    @GetMapping("/rental")
    public String rental(Model model, HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        String role = (String) session.getAttribute("role");
        List<Rental> rentals = rentalService.findAll();

        if (isLoggedIn == null) {
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "rental");
        model.addAttribute("role", role);
        model.addAttribute("rentals", rentals);
        return "layout";
    }

    @GetMapping("/SearchRentItem")
    public String SearchRentItem(@RequestParam(name = "option", required = false, defaultValue = "") String option, @RequestParam(name = "searchRentalItem", required = false, defaultValue = "") String searchRentalItem, Model model, HttpSession session) {
        List<Item> searchedItems = new ArrayList<>();

        // 검색에서 대여 가능한 물품을 걸러야함
        switch (option) {
            case "id":
                Optional<Item> optionalItem = itemSearchService.searchById(searchRentalItem);
                searchedItems = optionalItem.map(Collections::singletonList).orElse(Collections.emptyList());
                break;
            case "name":
                searchedItems = itemSearchService.searchByName(searchRentalItem);
                break;
            case "type":
                searchedItems = itemSearchService.searchByType(searchRentalItem);
                break;
            default:
                searchedItems = new ArrayList<>();
                break;
        }

//        List<String> acceptableStatuses = Arrays.asList("대여가능", "대여 가능");
//        searchedItems = searchedItems.stream()
//                .filter(item -> acceptableStatuses.contains(item.getRentableStatus()))
//                .collect(Collectors.toList());

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if (isLoggedIn == null) {
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "rental");
        model.addAttribute("searchRepairItem", searchRentalItem);
        model.addAttribute("items", searchedItems);
        return "layout";
    }

    @PostMapping("/applyRental")
    public String applyRental(@RequestParam(value = "rentalItem", required = false, defaultValue = "") String rentalItem, @RequestParam(value = "returnDate", required = false, defaultValue = "") LocalDate returnDate, HttpSession session) {
        Rental rental = new Rental();

        try {
            rental.setUserId((String) session.getAttribute("ID"));
            rental.setItemId(rentalItem);
            rental.setRentalDate(LocalDate.now());
            rental.setReturnDate(returnDate);

            rentalService.join(rental);
        } catch (Exception e) {
            return "redirect:/?rentalError=true&rentalErrorMessage=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }
        return "redirect:/?rentalSuccess";
    }
}
