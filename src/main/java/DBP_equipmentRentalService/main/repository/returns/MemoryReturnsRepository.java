package DBP_equipmentRentalService.main.repository.returns;

import DBP_equipmentRentalService.main.domain.Returns;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryReturnsRepository extends MemoryGenericRepository<Returns> implements ReturnsRepository {
    private static final Map<String, Returns> store = new HashMap<>();


    @Override
    public Returns save(Returns returns) {
        store.put(returns.getReturnId(), returns);
        return returns;
    }

    @Override
    public Optional<Returns> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Returns> findByCriteria(Map<String, Object> criteria) {
        return store.values().stream()
                .filter(returns -> matchesCriteria(returns, criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<Returns> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
