package DBP_equipmentRentalService.main.repository.users;

import DBP_equipmentRentalService.main.domain.Users;
import DBP_equipmentRentalService.main.repository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUsersRepository extends JpaGenericRepository<Users, String> implements UsersRepository {
    @Autowired
    public JpaUsersRepository(EntityManager em){
        super(em, Users.class);
    }
}
