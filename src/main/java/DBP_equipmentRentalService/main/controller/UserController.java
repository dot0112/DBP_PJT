package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model){
        if (error != null) {
            model.addAttribute("errorMessage", "로그인에 실패했습니다. 다시 시도하세요.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session){
        boolean isLoggedIn = userService.signIn(username, password);

        if(isLoggedIn){
            session.setAttribute("isLoggedIn", true);

            return "redirect:/";
        }
        else{
            session.setAttribute("isLoggedIn", false);

            return "redirect:/login?error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }
}
