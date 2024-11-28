package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    // for test
    @GetMapping("/")
    @ResponseBody
    public String home(){
        List<Admin> adminList = adminService.findAll();

        for (Admin admin : adminList) {
            System.out.println(admin.getAdminId());
        }

        System.out.println();
        return "home";
    }
}
