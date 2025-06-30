package Students.Internship.repo;

import Students.Internship.model.internshipCourse;
import org.springframework.data.mongodb.repository.MongoRepository;

	public interface internshipCourseRepo extends MongoRepository<internshipCourse, String> {
	}


