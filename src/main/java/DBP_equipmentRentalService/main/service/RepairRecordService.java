package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.repository.repairRecord.RepairRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RepairRecordService {
    private final RepairRecordRepository repairRecordRepository;

    @Autowired
    public RepairRecordService(RepairRecordRepository repairRecordRepository){
        this.repairRecordRepository = repairRecordRepository;
    }

    public void join(RepairRecord repairRecord){repairRecordRepository.save(repairRecord);}
}
