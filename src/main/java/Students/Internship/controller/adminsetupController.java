package Students.Internship.controller;

import Students.Internship.model.AdminUser;
import Students.Internship.repo.AdminRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class adminsetupController {

    @Autowired
    private AdminRepo adminRepo;

    // === SHOW ADMIN LOGIN PAGE ===
    @GetMapping("/admin-login")
    public String showLoginForm() {
        return "admin_login"; // loads admin_login.html
    }

    // === PROCESS ADMIN LOGIN ===
    @PostMapping("/admin-login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        AdminUser admin = adminRepo.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            session.setAttribute("isAdmin", true);
            return "redirect:/inter_list"; // redirect to intern list
        } else {
            model.addAttribute("error", "Invalid Username or Password");
            return "admin_login";
        }
    }

    // === ADMIN LOGOUT ===
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin-login";
    }

    // === INITIAL ADMIN ACCOUNT CREATION ===
    @GetMapping("/insert-admin")
    @ResponseBody
    public String insertAdmin() {
        if (adminRepo.findByUsername("admin") == null) {
            AdminUser admin = new AdminUser();
            admin.setUsername("admin");
            admin.setPassword("admin123"); // plain text for now; secure later
            adminRepo.save(admin);
            return "✅ Admin inserted successfully!";
        } else {
            return "⚠️ Admin already exists.";
        }
    }
}
