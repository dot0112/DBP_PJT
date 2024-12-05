package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.repository.repairRecord.RepairRecordRepository;
import DBP_equipmentRentalService.main.repository.repairRequest.RepairRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class RepairRequestService {
    private RepairRequestRepository repairRequestRepository;
    private RepairRecordRepository repairRecordRepository;

    @Autowired
    public RepairRequestService(RepairRequestRepository repairRequestRepository, RepairRecordRepository repairRecordRepository) {
        this.repairRequestRepository = repairRequestRepository;
        this.repairRecordRepository = repairRecordRepository;
    }

    public void join(RepairRequest repairRequest) {
        repairRequestRepository.save(repairRequest);
    }

    public List<RepairRequest> findRequest(String userId) {
        Map<String, LocalDate> repairDateMem = new HashMap<>();
        List<RepairRequest> result = new ArrayList<>();
        List<RepairRequest> listOfRequest = repairRequestRepository.findByCriteria(Map.of("userId", userId));
        for (RepairRequest request : listOfRequest) {
            String itemId = request.getItemId();
            LocalDate recentRepairDate = repairDateMem.get(itemId);
            if (recentRepairDate == null) {
                List<RepairRecord> listOfRepairRecord = repairRecordRepository.findByCriteria(Map.of("itemId", itemId));
                RepairRecord recentRecord = listOfRepairRecord.stream().max(Comparator.comparing(RepairRecord::getRepairDate)).orElse(null);
                recentRepairDate = (recentRecord == null) ? LocalDate.MIN : recentRecord.getRepairDate();
                repairDateMem.put(itemId, recentRepairDate);
            }
            if(recentRepairDate < request.get)
        }
    }
}
