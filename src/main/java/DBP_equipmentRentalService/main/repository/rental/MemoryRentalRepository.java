package DBP_equipmentRentalService.main.repository.rental;

import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryRentalRepository extends MemoryGenericRepository<Rental> implements RentalRepository {
    private static final Map<String, Rental> store = new HashMap<>();


    @Override
    public Rental save(Rental rental) {
        store.put(rental.getRentalId(), rental);
        return rental;
    }

    @Override
    public Optional<Rental> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Rental> findByCriteria(Map<String, Object> criteria) {
        return store.values().stream()
                .filter(rental -> matchesCriteria(rental, criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Boolean update(Map<String, Object> criteria, Map<String, Object> changeValues) {
        List<Rental> objectsToChange = findByCriteria(criteria);
        for (Rental object : objectsToChange) {
            for (Map.Entry<String, Object> entry : changeValues.entrySet()) {
                String fieldName = entry.getKey();
                Object newValue = entry.getValue();

                try {
                    // 필드 접근 및 값 설정
                    Field field = Rental.class.getDeclaredField(fieldName);
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
