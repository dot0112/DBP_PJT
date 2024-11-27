package DBP_equipmentRentalService.main.repository.item;

import DBP_equipmentRentalService.main.domain.Item;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemRepository {
    Item save(Item item);
    Optional<Item> findById(String id);
    List<Item> findByCriteria(Map<String, Object> criteria);
    List<Item> findAll();
}
