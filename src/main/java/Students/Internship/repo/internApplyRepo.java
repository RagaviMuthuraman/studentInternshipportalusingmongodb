package Students.Internship.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import Students.Internship.model.internApply;


	public interface internApplyRepo extends MongoRepository<internApply, String> {

		 List<internApply> findByEmail(String email);  // ✅ Optional query by email

		    Optional<internApply> findById(String id);         // ✅ Optional query by ID

		    List<internApply> findByCourseId(String courseId); // (optional) find all by courseId
		}

