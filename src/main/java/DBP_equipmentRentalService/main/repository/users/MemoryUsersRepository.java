package DBP_equipmentRentalService.main.repository.users;

import DBP_equipmentRentalService.main.domain.Users;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
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

    @Override
    public Boolean update(Map<String, Object> criteria, Map<String, Object> changeValues) {
        List<Users> objectsToChange = findByCriteria(criteria);
        for (Users object : objectsToChange) {
            for (Map.Entry<String, Object> entry : changeValues.entrySet()) {
                String fieldName = entry.getKey();
                Object newValue = entry.getValue();

                try {
                    // 필드 접근 및 값 설정
                    Field field = Users.class.getDeclaredField(fieldName);
                    field.setAccessible(true); // private 필드 접근 허용
                    field.set(object, newValue); // 새로운 값 설정
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException("Error updating field: " + fieldName, e);
                }
            }
        }
        return true;
    }

    public void clearStore() {
        store.clear();
    }
}
