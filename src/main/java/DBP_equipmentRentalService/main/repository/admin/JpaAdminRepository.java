package DBP_equipmentRentalService.main.repository.admin;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaAdminRepository extends JpaGenericRepository<Admin> implements AdminRepository  {
    @Autowired
    public JpaAdminRepository(EntityManager em){
        super(em, Admin.class);
    }

    @Override
    public Optional<Admin> findById(String id) {
        Admin admin = em.find(Admin.class, id);
        return Optional.ofNullable(admin);
    }
}
