package DBP_equipmentRentalService.main.repository.rental;

import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryRentalRepository extends MemoryGenericRepository<Rental> implements RentalRepository {
    private static final Map<String, Rental> store = new HashMap<>();


    @Override
    public Rental save(Rental rental) {
        store.put(rental.getRentalId(), rental);
        return rental;
    }

    @Override
    public Optional<Rental> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Rental> findByCriteria(Map<String, Object> criteria) {
        return store.values().stream()
                .filter(rental -> matchesCriteria(rental, criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
