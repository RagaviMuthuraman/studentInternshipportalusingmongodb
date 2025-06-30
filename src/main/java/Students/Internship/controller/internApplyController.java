package Students.Internship.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Students.Internship.model.internApply;
import Students.Internship.repo.internApplyRepo;

@Controller
public class internApplyController {
	

    @Autowired
    private internApplyRepo appRepo;
    @GetMapping("/apply")
    public String fallbackApplyPage() {
        return "submit"; // or wherever you want
    }

	@PostMapping("/apply")
	public String applyToCourse(@RequestParam String course, 
	                            @RequestParam String name,
	                            @RequestParam String email,
	                            @RequestParam String resumeLink,Model model) {
	    internApply app = new internApply();
	    app.setStudentName(name);
	    app.setEmail(email);
	    app.setAppliedCourse(course);
	    app.setResumeLink(resumeLink);
	    appRepo.save(app);
	    model.addAttribute("successMessage", "✅ Successfully applied for " + course + " internship!");

	    return "home";
	}

}
