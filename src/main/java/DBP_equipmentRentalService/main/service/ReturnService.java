package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.repository.item.ItemRepository;
import DBP_equipmentRentalService.main.repository.rental.RentalRepository;
import DBP_equipmentRentalService.main.repository.returns.ReturnsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
