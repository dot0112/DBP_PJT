package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.Users;
import DBP_equipmentRentalService.main.repository.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public boolean signIn(String username, String password){
        Optional<Users> optionalUsers = usersRepository.findById(username);

        if(optionalUsers.isPresent()){
            Users users = optionalUsers.get();

            return Objects.equals(password, users.getPassword());
        }

        return false;
    }

    public String signUp(Users users){
        usersRepository.save(users);
        return users.getUserId();
    }

    public boolean isDuplicateMember(String username){
        return usersRepository.findById(username).isPresent();
    }
}
