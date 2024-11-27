package DBP_equipmentRentalService.main.repository.returns;

import DBP_equipmentRentalService.main.domain.Returns;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReturnsRepository {
    Returns save(Returns returns);
    Optional<Returns> findById(String id);
    List<Returns> findByCriteria(Map<String, Object> criteria);
    List<Returns> findAll();
}
