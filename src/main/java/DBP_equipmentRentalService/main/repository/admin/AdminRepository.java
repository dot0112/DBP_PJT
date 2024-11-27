package DBP_equipmentRentalService.main.repository.admin;

import DBP_equipmentRentalService.main.domain.Admin;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AdminRepository {
    Admin save(Admin admin);
    Optional<Admin> findById(String id);
    List<Admin> findByCriteria(Map<String, Object> criteria);
    List<Admin> findAll();
}
