package DBP_equipmentRentalService.main.repository.users;

import DBP_equipmentRentalService.main.domain.Users;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUsersRepository extends JpaGenericRepository<Users> implements UsersRepository {
    @Autowired
    public JpaUsersRepository(EntityManager em){
        super(em, Users.class);
    }

    @Override
    public Optional<Users> findById(String id) {
        Users users = em.find(Users.class, id);
        return Optional.ofNullable(users);
    }
}
