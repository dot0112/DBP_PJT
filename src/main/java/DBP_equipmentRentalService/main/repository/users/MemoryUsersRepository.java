package DBP_equipmentRentalService.main.repository.users;

import DBP_equipmentRentalService.main.domain.Users;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryUsersRepository extends MemoryGenericRepository<Users> implements UsersRepository {
    private static final Map<String, Users> store = new HashMap<>();


    @Override
    public Users save(Users users) {
        store.put(users.getUserId(), users);
        return users;
    }

    @Override
    public Optional<Users> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Users> findByCriteria(Map<String, Object> criteria) {
        return store.values().stream()
                .filter(admin -> matchesCriteria(admin, criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<Users> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
