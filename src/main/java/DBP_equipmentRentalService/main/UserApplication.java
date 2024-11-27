package DBP_equipmentRentalService.main;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UserApplication {

	@Autowired
	private static AdminService adminService; // 서비스 클래스 주입

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
