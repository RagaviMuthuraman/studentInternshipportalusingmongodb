package Students.Internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Students.Internship.model.internApply;
import Students.Internship.model.internshipCourse;
import Students.Internship.repo.internApplyRepo;
import Students.Internship.repo.internshipCourseRepo;

import java.util.List;

@Controller
public class internApplyController {

    @Autowired
    private internApplyRepo appRepo;

    @Autowired
    private internshipCourseRepo courseRepo;

    // Show the form with all courses
    @GetMapping("/intern/register")
    public String showForm(@RequestParam("courseId") String courseId, Model model) {
        internshipCourse selectedCourse = courseRepo.findByCourseId(courseId);
        model.addAttribute("selectedCourse", selectedCourse);
        model.addAttribute("courses", courseRepo.findAll()); // 🔁 dropdown list
        return "submit";
    }

    // Save the application
    @PostMapping("/apply")
    public String applyToCourse(@RequestParam("courseId") String courseId,
                                @RequestParam String name,
                                @RequestParam String email,
                                @RequestParam String resumeLink,
                                Model model) {

        internshipCourse selectedCourse = courseRepo.findByCourseId(courseId); // ✅ fetch full course

        internApply app = new internApply();
        app.setStudentName(name);
        app.setEmail(email);
        app.setResumeLink(resumeLink);
        app.setCourseId(courseId); // save ID separately
        app.setAppliedCourse(selectedCourse.getTitle()); // save course name in appliedCourse
        app.setStatus("Applied");

        appRepo.save(app);

        model.addAttribute("message", "✅ Successfully applied for " + selectedCourse.getTitle());
        return "submit";
    }
}
