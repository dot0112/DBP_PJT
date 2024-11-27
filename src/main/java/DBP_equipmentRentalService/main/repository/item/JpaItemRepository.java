package DBP_equipmentRentalService.main.repository.item;

import DBP_equipmentRentalService.main.domain.Item;

import DBP_equipmentRentalService.main.repository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaItemRepository extends JpaGenericRepository<Item, String> implements ItemRepository {
    @Autowired
    public JpaItemRepository(EntityManager em){
        super(em, Item.class);
    }
}
