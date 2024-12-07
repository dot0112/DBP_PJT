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

    public List<RepairRequest> findRequest(String userId) {
        return repairRequestRepository.findByCriteria(Map.of("userId", userId, "isRepaired", 0));
    }

    public List<RepairRequest> findAll(){
        return repairRequestRepository.findAll();
    }

    public List<RepairRequest> findRequestByRepairRequest(String repairRequestId){
        return repairRequestRepository.findByCriteria(Map.of("repairRequestId", repairRequestId));
    }

    public List<RepairRequest> findByItemId(String itemId){
        return repairRequestRepository.findByCriteria(Map.of("itemID", itemId));
    }
}
