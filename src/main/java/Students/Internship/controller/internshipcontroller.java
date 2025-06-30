package Students.Internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import Students.Internship.model.internshipCourse;
import Students.Internship.repo.internshipCourseRepo;
@Controller
public class internshipcontroller {
	
	

	    @Autowired
	    private internshipCourseRepo repo;

	    @GetMapping("/insert-courses")
	    @ResponseBody
	    public String insertCourses() {
	        if (repo.findAll().isEmpty()) {
	            repo.save(new internshipCourse("Artificial Intelligence", "Learn core AI concepts and ML."));
	            repo.save(new internshipCourse("Java Development", "Spring Boot, REST APIs, and backend."));
	            repo.save(new internshipCourse("Python Programming", "Python basics, Flask, and data tools."));
	            repo.save(new internshipCourse("PHP Full Stack", "Laravel, web dev, and MySQL."));
	            repo.save(new internshipCourse("UI/UX Designing", "Figma, Adobe XD, real-time design projects."));
	            return "Internship courses inserted!";
	        } else {
	            return "Courses already exist.";
	        }
	    }
	}


