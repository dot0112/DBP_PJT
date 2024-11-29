package DBP_equipmentRentalService.main.repository.returns;

import DBP_equipmentRentalService.main.domain.Returns;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaReturnsRepository extends JpaGenericRepository<Returns> implements ReturnsRepository {
    @Autowired
    public JpaReturnsRepository(EntityManager em) {
        super(em, Returns.class);
    }

    @Override
    public Optional<Returns> findById(String id) {
        Returns returns = em.find(Returns.class, id);
        return Optional.ofNullable(returns);
    }
}
