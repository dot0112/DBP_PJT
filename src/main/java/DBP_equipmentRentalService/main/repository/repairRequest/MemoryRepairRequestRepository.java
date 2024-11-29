package DBP_equipmentRentalService.main.repository.repairRequest;

import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

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

    public void clearStore() {
        store.clear();
    }
}
