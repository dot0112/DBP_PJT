package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.Users;
import DBP_equipmentRentalService.main.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
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

    private void validateDuplicateMember(Users users){
        usersRepository.findById(users.getUserId()).ifPresent(m -> {throw new IllegalStateException("존재하는 ID입니다.");});
    }
}
