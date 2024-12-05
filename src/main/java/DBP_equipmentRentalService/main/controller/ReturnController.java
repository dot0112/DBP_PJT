package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.DTO.RentalWithItemName;
import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.domain.Returns;
import DBP_equipmentRentalService.main.repository.rental.RentalRepository;
import DBP_equipmentRentalService.main.service.RentalService;
import DBP_equipmentRentalService.main.service.ReturnService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ReturnController {
    private final ReturnService returnService;
    private final RentalRepository rentalRepository;

    @Autowired
    public ReturnController(ReturnService returnService, RentalRepository rentalRepository){
        this.returnService = returnService;
        this.rentalRepository = rentalRepository;
    }

    @GetMapping ("/return")
    public String returnitem(Model model, HttpSession session){
        List<RentalWithItemName> rentalWithItemNames = returnService.setList((String) session.getAttribute("ID"));
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if(isLoggedIn == null){
            isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("contentFragment", "return");
        model.addAttribute("items", rentalWithItemNames);
        return "layout";
    }

    @PostMapping ("/returnitem")
    public String returnitem(@RequestParam(value = "returnAndRepair", required = false) Boolean returnAndRepair, @RequestParam(value = "returnBtn") String rentalId, HttpSession session){
        Returns returns = new Returns();
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);
        Rental rental = new Rental();

        if(optionalRental.isPresent()){
            rental = optionalRental.get();
        }

        try {
            returns.setUserId((String) session.getAttribute("ID"));
            returns.setItemId(rental.getItemId());
            returns.setRentalId(rental.getRentalId());
            returns.setReturnId("y");
            returns.setActualReturnDate(LocalDate.now());
            if(returnAndRepair != null && returnAndRepair){
                returns.setRepairRequest("Y");
            }
            else {
                returns.setRepairRequest("N");
            }

            returnService.join(returns);
        }
        catch (Exception e){
            System.out.println(e);
            return "redirect:/?returnError";
        }
        return "redirect:/?returnSuccess";
    }
}
