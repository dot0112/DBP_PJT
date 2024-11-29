package DBP_equipmentRentalService.main.repository.rental;

import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRentalRepository extends JpaGenericRepository<Rental, String> implements RentalRepository {
   @Autowired
    public JpaRentalRepository(EntityManager em){
       super(em, Rental.class);
   }
}
