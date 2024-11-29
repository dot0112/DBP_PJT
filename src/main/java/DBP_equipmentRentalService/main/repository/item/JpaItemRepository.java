package DBP_equipmentRentalService.main.repository.item;

import DBP_equipmentRentalService.main.domain.Item;

import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaItemRepository extends JpaGenericRepository<Item> implements ItemRepository {
    @Autowired
    public JpaItemRepository(EntityManager em){
        super(em, Item.class);
    }

    @Override
    public Optional<Item> findById(String id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }
}
