package DBP_equipmentRentalService.main.repository.repairRequest;

import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.repository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRepairRequestRepository extends JpaGenericRepository<RepairRequest, String> implements RepairRequestRepository {
    @Autowired
    public JpaRepairRequestRepository(EntityManager em){
        super(em, RepairRequest.class);
    }

}
