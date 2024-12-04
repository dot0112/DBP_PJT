package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.repository.admin.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean signIn(String username, String password){
        Optional<Admin> optionalAdmin = adminRepository.findById(username);

        if(optionalAdmin.isPresent()){
            Admin admin = optionalAdmin.get();

            return Objects.equals(password, admin.getPassword());
        }
        return false;
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
}
