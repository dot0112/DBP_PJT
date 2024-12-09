package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.service.RentalService;
import DBP_equipmentRentalService.main.service.search.RentalSearchService;
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
import java.util.List;

@Controller
public class RentalController {
    private final RentalSearchService rentalSearchService;
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalSearchService rentalSearchService, RentalService rentalService) {
        this.rentalSearchService = rentalSearchService;
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

        List<Item> searchedItems = switch (option) {
            case "id" -> rentalSearchService.searchById(searchRentalItem);
            case "name" -> rentalSearchService.searchByName(searchRentalItem);
            case "type" -> rentalSearchService.searchByType(searchRentalItem);
            default -> new ArrayList<>();
        };

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
