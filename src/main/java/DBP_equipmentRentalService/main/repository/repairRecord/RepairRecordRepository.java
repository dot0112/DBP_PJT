package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.RepairRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RepairRecordRepository {
    RepairRecord save(RepairRecord repairRecord);

    Optional<RepairRecord> findById(String itemId, LocalDate repairDate);

    List<RepairRecord> findByCriteria(Map<String, Object> criteria);

    List<RepairRecord> findAll();
}
