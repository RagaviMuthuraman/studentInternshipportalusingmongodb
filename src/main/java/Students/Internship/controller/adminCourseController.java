package Students.Internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Students.Internship.model.courseDetails;
import Students.Internship.repo.courseRepo;
@Controller
public class adminCourseController {
	


	

	    @Autowired
	    private courseRepo courseRepo;

	    // Show the add course form
	    @GetMapping("/admin/add-course")
	    public String showAddCourseForm() {
	        return "course_details";  // 🔁 Make sure you have course_register.html
	    }

	    @PostMapping("/admin/add-course")
	    public String addCourse(@RequestParam String courseId,
	                            @RequestParam String courseName,
	                            @RequestParam String description,
	                            Model model) {

	        courseDetails course = new courseDetails(); // ✅ create course object
	        course.setCourseId(courseId);               // ✅ set values from form
	        course.setCourseName(courseName);
	        course.setDescription(description);

	        courseRepo.save(course);                    // ✅ save to MongoDB

	        model.addAttribute("message", "Course added successfully!");
	        return "course_details";
	    }

	}


