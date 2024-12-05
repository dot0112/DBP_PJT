package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.domain.Returns;
import DBP_equipmentRentalService.main.repository.item.ItemRepository;
import DBP_equipmentRentalService.main.repository.rental.RentalRepository;
import DBP_equipmentRentalService.main.repository.repairRequest.RepairRequestRepository;
import DBP_equipmentRentalService.main.repository.returns.ReturnsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TestService {
    private final ItemRepository itemRepository;
    private final RentalRepository rentalRepository;
    private final RepairRequestRepository repairRequestRepository;
    private final ReturnsRepository returnsRepository;

    @Autowired
    public TestService(ItemRepository itemRepository, RentalRepository rentalRepository, RepairRequestRepository repairRequestRepository, ReturnsRepository returnsRepository) {
        this.itemRepository = itemRepository;
        this.rentalRepository = rentalRepository;
        this.repairRequestRepository = repairRequestRepository;
        this.returnsRepository = returnsRepository;
    }

    public void testItemCreate() {
        Item item = new Item();
        item.setItemName("test");
        item.setItemType("test");
        itemRepository.save(item);
    }

    public void testRentalCreate() {
        Rental rental = new Rental();
        rental.setUserId("user001");
        rental.setItemId("drXN7aD4");
        rental.setRentalDate(LocalDate.now());
        rentalRepository.save(rental);
    }

    public void testRepairRequestCreate() {
        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setItemId("EjbMNw6u");
        repairRequest.setUserId("user001");
        repairRequest.setItemName("파미가구 아이언");
        repairRequestRepository.save(repairRequest);
    }

    public void testReturnsCreate() {
        Returns returns = new Returns();
        returns.setRentalId("RENT00080");
        returns.setUserId("user001");
        returns.setItemId("gkYAbeIW");
        returnsRepository.save(returns);
    }
}
