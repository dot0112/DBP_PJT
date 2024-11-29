package DBP_equipmentRentalService.main.repository.rental;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaRentalRepository extends JpaGenericRepository<Rental> implements RentalRepository {
   @Autowired
    public JpaRentalRepository(EntityManager em){
       super(em, Rental.class);
   }

    @Override
    public Optional<Rental> findById(String id) {
        Rental rental = em.find(Rental.class, id);
        return Optional.ofNullable(rental);
    }
}
