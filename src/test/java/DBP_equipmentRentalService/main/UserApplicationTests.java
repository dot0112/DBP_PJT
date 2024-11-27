package DBP_equipmentRentalService.main;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.service.AdminService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserApplicationTests {

	@Autowired
	private AdminService adminService; // 서비스 클래스 주입

	@Test
	void contextLoads() {
		System.out.println(1);
	}

}
