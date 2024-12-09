package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.domain.RepairRecordId;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaRepairRecordRepository extends JpaGenericRepository<RepairRecord> implements RepairRecordRepository {
    @Autowired
    public JpaRepairRecordRepository(EntityManager em) {
        super(em, RepairRecord.class);
    }

    @Override
    public Optional<RepairRecord> findById(RepairRecordId repairRecordId) {
        String jpql = "SELECT e FROM RepairRecord e WHERE e.itemId = :itemId AND e.repairDate = :repairDate";
        TypedQuery<RepairRecord> query = em.createQuery(jpql, RepairRecord.class);

        query.setParameter("itemId", repairRecordId.getItemId());
        query.setParameter("repairDate", repairRecordId.getRepairDate());

        try {
            RepairRecord repairRecord = query.getSingleResult();
            return Optional.of(repairRecord);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
