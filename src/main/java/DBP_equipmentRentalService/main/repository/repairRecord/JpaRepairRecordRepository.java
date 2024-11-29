package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class JpaRepairRecordRepository extends JpaGenericRepository<RepairRecord> implements RepairRecordRepository {
    @Autowired
    public JpaRepairRecordRepository(EntityManager em){
        super(em, RepairRecord.class);
    }

    @Override
    public Optional<RepairRecord> findByKey(String itemId, LocalDate repairDate) {
        String jpql = "SELECT e FROM REPAIRRECORD e WHERE e.ITEMID = :ITEMID AND e.REPAIRDATE = :REPAIRDATE";
        TypedQuery<RepairRecord> query = em.createQuery(jpql, RepairRecord.class);

        query.setParameter("ITEMID", itemId);
        query.setParameter("REPAIRDATE", repairDate);

        RepairRecord result = query.getSingleResult();
        return Optional.ofNullable(result);
    }
}
