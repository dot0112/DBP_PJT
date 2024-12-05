package DBP_equipmentRentalService.main.repository.returns;

import DBP_equipmentRentalService.main.domain.Returns;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryReturnsRepository extends MemoryGenericRepository<Returns> implements ReturnsRepository {
    private static final Map<String, Returns> store = new HashMap<>();


    @Override
    public Returns save(Returns returns) {
        store.put(returns.getReturnId(), returns);
        return returns;
    }

    @Override
    public Optional<Returns> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Returns> findByCriteria(Map<String, Object> criteria) {
        return store.values().stream()
                .filter(returns -> matchesCriteria(returns, criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<Returns> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Boolean update(Map<String, Object> criteria, Map<String, Object> changeValues) {
        List<Returns> objectsToChange = findByCriteria(criteria);
        for (Returns object : objectsToChange) {
            for (Map.Entry<String, Object> entry : changeValues.entrySet()) {
                String fieldName = entry.getKey();
                Object newValue = entry.getValue();

                try {
                    // 필드 접근 및 값 설정
                    Field field = Returns.class.getDeclaredField(fieldName);
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
