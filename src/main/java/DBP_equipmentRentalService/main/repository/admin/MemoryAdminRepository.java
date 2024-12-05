package DBP_equipmentRentalService.main.repository.admin;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryAdminRepository extends MemoryGenericRepository<Admin> implements AdminRepository {
    private static final Map<String, Admin> store = new HashMap<>();

    @Override
    public Admin save(Admin admin) {
        store.put(admin.getAdminId(), admin);
        return admin;
    }

    @Override
    public Optional<Admin> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Admin> findByCriteria(Map<String, Object> criteria) {
        return store.values().stream()
                .filter(admin -> matchesCriteria(admin, criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<Admin> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Boolean update(Map<String, Object> criteria, Map<String, Object> changeValues) {
        List<Admin> objectsToChange = findByCriteria(criteria);
        for (Admin object : objectsToChange) {
            for (Map.Entry<String, Object> entry : changeValues.entrySet()) {
                String fieldName = entry.getKey();
                Object newValue = entry.getValue();

                try {
                    // 필드 접근 및 값 설정
                    Field field = Admin.class.getDeclaredField(fieldName);
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
