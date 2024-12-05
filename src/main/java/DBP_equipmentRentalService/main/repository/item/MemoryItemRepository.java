package DBP_equipmentRentalService.main.repository.item;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
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

    @Override
    public Boolean update(Map<String, Object> criteria, Map<String, Object> changeValues) {
        List<Item> objectsToChange = findByCriteria(criteria);
        for (Item object : objectsToChange) {
            for (Map.Entry<String, Object> entry : changeValues.entrySet()) {
                String fieldName = entry.getKey();
                Object newValue = entry.getValue();

                try {
                    // 필드 접근 및 값 설정
                    Field field = Item.class.getDeclaredField(fieldName);
                    field.setAccessible(true); // private 필드 접근 허용
                    field.set(object, newValue); // 새로운 값 설정
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException("Error updating field: " + fieldName, e);
                }
            }
        }
        return true;
    }

    public void clearStore() {
        store.clear();
    }
}
