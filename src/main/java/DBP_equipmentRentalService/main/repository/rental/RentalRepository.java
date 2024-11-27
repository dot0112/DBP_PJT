package DBP_equipmentRentalService.main.repository.rental;

import DBP_equipmentRentalService.main.domain.Rental;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RentalRepository {
    Rental save(Rental rental);
    Optional<Rental> findById(String id);
    List<Rental> findByCriteria(Map<String, Object> criteria);
    List<Rental> findAll();
}
