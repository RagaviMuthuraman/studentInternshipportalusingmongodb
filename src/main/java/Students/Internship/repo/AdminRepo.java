package Students.Internship.repo;
import Students.Internship.model.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;


	

	
	public interface AdminRepo extends MongoRepository<AdminUser, String> {
	    AdminUser findByUsername(String username);
	}


