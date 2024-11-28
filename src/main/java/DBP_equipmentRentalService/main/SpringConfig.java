package DBP_equipmentRentalService.main;

import DBP_equipmentRentalService.main.repository.admin.AdminRepository;
import DBP_equipmentRentalService.main.repository.admin.JpaAdminRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){
        this.em=em;
    }

    @Bean
    public AdminRepository adminRepository() {
        return new JpaAdminRepository(em);
    }


}
