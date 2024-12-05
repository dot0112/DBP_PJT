package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryRepairRecordRepository extends MemoryGenericRepository<RepairRecord> implements RepairRecordRepository {
    private static final Map<List<Object>, RepairRecord> store = new HashMap<>();


    @Override
    public RepairRecord save(RepairRecord repairRecord) {
        store.put(Arrays.asList(repairRecord.getItemId(), repairRecord.getRepairDate()), repairRecord);
        return repairRecord;
    }

    @Override
    public Optional<RepairRecord> findById(String itemId, LocalDate repairDate) {
        return Optional.ofNullable(store.get(Arrays.asList(itemId, repairDate)));
    }

    @Override
    public List<RepairRecord> findByCriteria(Map<String, Object> criteria) {
        return store.values().stream()
                .filter(repairRecord -> matchesCriteria(repairRecord, criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairRecord> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Boolean update(Map<String, Object> criteria, Map<String, Object> changeValues) {
        List<RepairRecord> objectsToChange = findByCriteria(criteria);
        for (RepairRecord object : objectsToChange) {
            for (Map.Entry<String, Object> entry : changeValues.entrySet()) {
                String fieldName = entry.getKey();
                Object newValue = entry.getValue();

                try {
                    // 필드 접근 및 값 설정
                    Field field = RepairRecord.class.getDeclaredField(fieldName);
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
