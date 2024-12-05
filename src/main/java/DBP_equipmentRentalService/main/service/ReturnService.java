package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.DTO.RentalWithItemName;
import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.domain.Returns;
import DBP_equipmentRentalService.main.repository.item.ItemRepository;
import DBP_equipmentRentalService.main.repository.rental.RentalRepository;
import DBP_equipmentRentalService.main.repository.returns.ReturnsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReturnService {
    private final ReturnsRepository returnsRepository;
    private final ItemRepository itemRepository;
    private final RentalRepository rentalRepository;

    @Autowired
    public ReturnService(ReturnsRepository returnsRepository, ItemRepository itemRepository, RentalRepository rentalRepository){
        this.returnsRepository = returnsRepository;
        this.itemRepository = itemRepository;
        this.rentalRepository = rentalRepository;
    }

    public List<RentalWithItemName> setList(String userId){
        List<RentalWithItemName> rentalWithItemNames = new ArrayList<>();

        Map<String, Object> userCriteria = Map.of("userId", userId);
        List<Rental> userRentals = rentalRepository.findByCriteria(userCriteria); //find by userId at rental
        List<Returns> userReturns = returnsRepository.findByCriteria(userCriteria); //find by userId ad return

        userRentals.removeIf(rental -> userReturns.stream()
                .anyMatch(returns -> returns.getRentalId().equals(rental.getRentalId())));  //not returned rentals

        for (Rental userRental : userRentals) {
            Optional<Item> optionalItem = itemRepository.findById(userRental.getItemId());
            String itemName = optionalItem.map(Item::getItemName).orElse("Item not found");

            RentalWithItemName rentalWithItemName = new RentalWithItemName(userRental, itemName);

            rentalWithItemNames.add(rentalWithItemName);
        }

        return rentalWithItemNames;
    }

    @Transactional
    public void join(Returns returns){returnsRepository.save(returns);}
}
