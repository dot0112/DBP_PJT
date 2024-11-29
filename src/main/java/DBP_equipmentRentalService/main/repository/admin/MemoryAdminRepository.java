package DBP_equipmentRentalService.main.repository.admin;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

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

    public void clearStore() {
        store.clear();
    }
}
