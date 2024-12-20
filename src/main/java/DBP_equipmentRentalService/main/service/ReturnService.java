package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.DTO.RentalWithItemName;
import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.domain.Returns;
import DBP_equipmentRentalService.main.repository.item.ItemRepository;
import DBP_equipmentRentalService.main.repository.procedure.ProcedureRepository;
import DBP_equipmentRentalService.main.repository.rental.RentalRepository;
import DBP_equipmentRentalService.main.repository.returns.ReturnsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ReturnService {
    private final ReturnsRepository returnsRepository;
    private final ItemRepository itemRepository;
    private final RentalRepository rentalRepository;
    private final ProcedureRepository procedureRepository;

    @Autowired
    public ReturnService(ReturnsRepository returnsRepository, ItemRepository itemRepository, RentalRepository rentalRepository, ProcedureRepository procedureRepository) {
        this.returnsRepository = returnsRepository;
        this.itemRepository = itemRepository;
        this.rentalRepository = rentalRepository;
        this.procedureRepository = procedureRepository;
    }

    public List<RentalWithItemName> setList(String userId) {
        List<RentalWithItemName> rentalWithItemNames = new ArrayList<>();

        Map<String, Object> userCriteria = Map.of("userId", userId);
        List<Rental> userRentals = rentalRepository.findByCriteria(userCriteria); //find by userId at rental
        List<Returns> userReturns = returnsRepository.findByCriteria(userCriteria); //find by userId ad return

        userRentals.removeIf(rental -> userReturns.stream()
                .anyMatch(returns -> returns.getRentalId().equals(rental.getRentalId())));  //not returned rentals

        for (Rental userRental : userRentals) {
            Optional<Item> optionalItem = itemRepository.findById(userRental.getItemId());
            String itemName = optionalItem.map(Item::getItemName).orElse("Item not found");

            RentalWithItemName rentalWithItemName = new RentalWithItemName(userRental, itemName, (int) ChronoUnit.DAYS.between(userRental.getReturnDate(), LocalDate.now()));

            rentalWithItemNames.add(rentalWithItemName);
        }

        return rentalWithItemNames;
    }

    public void join(Returns returns) {
        returnsRepository.save(returns);
    }

    public void setBorrowLimit(String userId) {
        procedureRepository.setBorrowLimit(userId);
    }

    public List<Returns> findAll() {
        return returnsRepository.findAll();
    }
}
