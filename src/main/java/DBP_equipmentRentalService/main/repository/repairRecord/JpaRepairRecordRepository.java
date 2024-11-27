package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.repository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRepairRecordRepository extends JpaGenericRepository<RepairRecord, String> implements RepairRecordRepository {
    @Autowired
    public JpaRepairRecordRepository(EntityManager em){
        super(em, RepairRecord.class);
    }
}
