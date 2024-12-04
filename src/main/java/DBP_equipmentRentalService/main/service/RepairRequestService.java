package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.repository.repairRequest.RepairRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RepairRequestService {
    private RepairRequestRepository repairRequestRepository;

    @Autowired
    public RepairRequestService(RepairRequestRepository repairRequestRepository){
        this.repairRequestRepository = repairRequestRepository;
    }

    public void join(RepairRequest repairRequest){
        repairRequestRepository.save(repairRequest);
    }
}
