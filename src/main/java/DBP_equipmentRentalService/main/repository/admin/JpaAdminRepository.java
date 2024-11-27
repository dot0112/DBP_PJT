package DBP_equipmentRentalService.main.repository.admin;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.repository.JpaGenericRepository;
import DBP_equipmentRentalService.main.repository.util.ReflectionUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JpaAdminRepository extends JpaGenericRepository<Admin, String> implements AdminRepository  {
    @Autowired
    public JpaAdminRepository(EntityManager em){
        super(em, Admin.class);
    }
}
