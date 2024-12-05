package DBP_equipmentRentalService.main.repository.repairRequest;

import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryRepairRequestRepository extends MemoryGenericRepository<RepairRequest> implements RepairRequestRepository {
    private static final Map<String, RepairRequest> store = new HashMap<>();


    @Override
    public RepairRequest save(RepairRequest repairRequest) {
        store.put(repairRequest.getRepairRequestId(), repairRequest);
        return repairRequest;
    }

    @Override
    public Optional<RepairRequest> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<RepairRequest> findByCriteria(Map<String, Object> criteria) {
        return store.values().stream()
                .filter(repairRequest -> matchesCriteria(repairRequest, criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<RepairRequest> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Boolean update(Map<String, Object> criteria, Map<String, Object> changeValues) {
        List<RepairRequest> objectsToChange = findByCriteria(criteria);
        for (RepairRequest object : objectsToChange) {
            for (Map.Entry<String, Object> entry : changeValues.entrySet()) {
                String fieldName = entry.getKey();
                Object newValue = entry.getValue();

                try {
                    // 필드 접근 및 값 설정
                    Field field = RepairRequest.class.getDeclaredField(fieldName);
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
