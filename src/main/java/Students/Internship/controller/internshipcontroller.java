package Students.Internship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Students.Internship.model.internshipCourse;
import Students.Internship.repo.internshipCourseRepo;
@Controller
public class internshipcontroller {
	
	

	    @Autowired
	    private internshipCourseRepo repo;
	    
	    @GetMapping("/available-courses")
	    public String showCourses(Model model) {
	        List<internshipCourse> courses = repo.findAll(); // Fetch from DB
	        model.addAttribute("courses", courses);          // Pass to HTML
	        return "intern_register"; // Match the name of your HTML file in templates (without .html)
	    }


	    @GetMapping("/insert-courses")
	    @ResponseBody
	    public String insertCourses() {
	        if (repo.findAll().isEmpty()) {
	            repo.save(new internshipCourse("c001","Artificial Intelligence", "Learn core AI concepts and ML."));
	            repo.save(new internshipCourse("c002","Java Development", "Spring Boot, REST APIs, and backend."));
	            repo.save(new internshipCourse("c003","Python Programming", "Python basics, Flask, and data tools."));
	            repo.save(new internshipCourse("c004","PHP Full Stack", "Laravel, web dev, and MySQL."));
	            repo.save(new internshipCourse("c005","UI/UX Designing", "Figma, Adobe XD, real-time design projects."));
	            return "Internship courses inserted!";
	        } else {
	            return "Courses already exist.";
	        }
	    }
	    @GetMapping("/courses/new")
	    public String showCourseForm(Model model) {
	        model.addAttribute("course", new internshipCourse());
	        return "add_course"; // points to add_course.html
	    }

	    @PostMapping("/courses/add")
	    public String addCourse(@ModelAttribute internshipCourse course, Model model) {
	        repo.save(course);
	        model.addAttribute("message", "Course added successfully!");
	        return "redirect:/intern_register"; // or to a success page
	    }
	    

	}


