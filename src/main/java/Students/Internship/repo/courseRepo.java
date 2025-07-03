package Students.Internship.repo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import Students.Internship.model.courseDetails;

	


	@Repository
	public interface courseRepo extends MongoRepository<courseDetails, String> {

	    // You can add custom query methods if needed
	    courseDetails findByCourseName(String courseName);
	    
	    courseDetails findByCourseId(String courseId);  // optional if needed
	

}
