package DBP_equipmentRentalService.main.repository.users;

import DBP_equipmentRentalService.main.domain.Users;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UsersRepository {
    Users save(Users users);
    Optional<Users> findById(String id);
    List<Users> findByCriteria(Map<String, Object> criteria);
    List<Users> findAll();
}
