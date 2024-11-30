package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

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

    public void clearStore() {
        store.clear();
    }
}
