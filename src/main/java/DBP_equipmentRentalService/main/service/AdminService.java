package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.repository.admin.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /*
     * 관리자 로그인 기능
     * 반환값: boolean
     * 설명: adminId와 password를 이용하여 Database에 해당 정보를 가진 Admin이 있는지 확인
     *       각 adminId와 password는 Admin 객체를 이용하여 전달
     * */
    public Boolean login(Admin admin) {
        if (admin == null) return false;
        Admin foundAdmin = adminRepository.findById(admin.getAdminId()).orElse(null);
        return (foundAdmin != null) && (foundAdmin.getPassword().equals(admin.getPassword()));
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
}
