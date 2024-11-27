package DBP_equipmentRentalService.main.repository.repairRequest;

import DBP_equipmentRentalService.main.domain.RepairRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RepairRequestRepository {
    RepairRequest save(RepairRequest repairRequest);
    Optional<RepairRequest> findById(String id);
    List<RepairRequest> findByCriteria(Map<String, Object> criteria);
    List<RepairRequest> findAll();
}
