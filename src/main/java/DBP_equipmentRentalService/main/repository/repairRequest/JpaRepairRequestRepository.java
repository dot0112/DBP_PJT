package DBP_equipmentRentalService.main.repository.repairRequest;

import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaRepairRequestRepository extends JpaGenericRepository<RepairRequest> implements RepairRequestRepository {
    @Autowired
    public JpaRepairRequestRepository(EntityManager em){
        super(em, RepairRequest.class);
    }

    @Override
    public Optional<RepairRequest> findById(String id) {
        RepairRequest repairRequest = em.find(RepairRequest.class, id);
        return Optional.ofNullable(repairRequest);
    }
}
