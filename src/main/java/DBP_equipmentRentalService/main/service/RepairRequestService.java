package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.repository.repairRequest.RepairRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RepairRequestService {
    private RepairRequestRepository repairRequestRepository;

    @Autowired
    public RepairRequestService(RepairRequestRepository repairRequestRepository) {
        this.repairRequestRepository = repairRequestRepository;
    }

    public void join(RepairRequest repairRequest) {
        repairRequestRepository.save(repairRequest);
    }

    public List<RepairRequest> findRepairRequestByUserId(String userId) {
        return repairRequestRepository.findByCriteria(Map.of("userId", userId, "isRepaired", 0));
    }

    public List<RepairRequest> findRepairRequestAll() {
        return repairRequestRepository.findByCriteria(Map.of("isRepaired", 0));
    }
}
