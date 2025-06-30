package Students.Internship.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import Students.Internship.model.studentIntern;

public interface studentInternRepo extends MongoRepository<studentIntern, String> {
    // You can add custom query methods later like:
    // List<StudentIntern> findByCollege(String college);
	Optional<studentIntern> findByEmail(String email);
}
