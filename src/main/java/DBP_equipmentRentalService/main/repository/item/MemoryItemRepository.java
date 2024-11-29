package DBP_equipmentRentalService.main.repository.item;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryItemRepository extends MemoryGenericRepository<Item> implements ItemRepository {
    private static final Map<String, Item> store = new HashMap<>();

    @Override
    public Item save(Item item) {
        store.put(item.getItemId(), item);
        return item;
    }

    @Override
    public Optional<Item> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Item> findByCriteria(Map<String, Object> criteria) {
        return store.values().stream()
                .filter(item -> matchesCriteria(item, criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
