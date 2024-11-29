package DBP_equipmentRentalService.main.repository.returns;

import DBP_equipmentRentalService.main.domain.Returns;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaReturnsRepository extends JpaGenericRepository<Returns, String> implements ReturnsRepository {
    @Autowired
    public JpaReturnsRepository(EntityManager em) {
        super(em, Returns.class);
    }
}
