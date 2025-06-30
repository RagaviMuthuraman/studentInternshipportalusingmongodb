package Students.Internship.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Students.Internship.model.internshipCourse;
import Students.Internship.model.studentIntern;
import Students.Internship.repo.internshipCourseRepo;
import Students.Internship.repo.studentInternRepo;
import jakarta.servlet.http.HttpSession;

@Controller
public class internController {

    @Autowired
    private studentInternRepo repo;
    @Autowired
    private internshipCourseRepo courseRepo;


    // === HOME PAGE ===
    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    // === STUDENT REGISTRATION ===
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new studentIntern());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute studentIntern intern) {
        repo.save(intern);
        return "redirect:/login";
    }

    // === LOGIN PAGE ===
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    @PostMapping("/login")
    public String loginStudent(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {

        Optional<studentIntern> user = repo.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            session.setAttribute("student", user.get());

            // ✅ Fetch internship courses and send to UI
            List<internshipCourse> courses = courseRepo.findAll();
            model.addAttribute("courses", courses);

            return "intern_register"; // ✅ Go to course detail page
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login"; // ❌ Don’t go to intern_register on failure
        }
    }



    // === INTERNSHIP COURSES LIST ===
    @GetMapping("/intern_register")
    public String showInternshipCourses() {
        return "intern_register";  // shows 5 course cards + apply button
    }

  


    // === INTERN LIST (ADMIN ONLY) ===
    @GetMapping("/inter_list")
    public String showAllInterns(Model model, HttpSession session) {
        if (session.getAttribute("isAdmin") == null) {
            return "redirect:/admin-login";
        }
        List<studentIntern> interns = repo.findAll();
        model.addAttribute("interns", interns);
        return "inter_list";
    }

    // === INTERN STATUS CHECK ===
    @GetMapping("/intern_status")
    public String showStatusForm() {
        return "intern_status";
    }

    @GetMapping("/check_status")
    public String checkStatus(@RequestParam String email, Model model) {
        Optional<studentIntern> intern = repo.findByEmail(email);
        if (intern.isPresent()) {
            model.addAttribute("statusMessage", "Your current status: " + intern.get().getStatus());
        } else {
            model.addAttribute("statusMessage", "No record found for this email.");
        }
        return "intern_status";
    }

    // === ADMIN UPDATE STATUS ===
    @PostMapping("/update-status")
    public String updateStatus(@RequestParam String id, @RequestParam String status) {
        Optional<studentIntern> internOpt = repo.findById(id);
        internOpt.ifPresent(intern -> {
            intern.setStatus(status);
            repo.save(intern);
        });
        return "redirect:/inter_list";
    }

    // === CONTACT FORM ===
   // @GetMapping("/contact")
 //   public String showContactForm() {
   //     return "contact";
    //}
//
  //  @PostMapping("/contact")
    //public String submitContact(@RequestParam String name,
      //                          @RequestParam String email,
        //                        @RequestParam String message) {
        //System.out.println("Contact: " + name + ", " + email + ", " + message);
        //return "redirect:/home";
   // }

    // === CERTIFICATE GENERATION ===
    //@GetMapping("/generate_certificate")
    //public String showCertificateForm() {
     //   return "generate_certificate";
    //}

    //@PostMapping("/generate_certificate")
    //public String processCertificate(@RequestParam String name, @RequestParam String course) {
      //  System.out.println("Generate certificate for: " + name + ", " + course);
        //return "redirect:/home";
    //}
 

}
