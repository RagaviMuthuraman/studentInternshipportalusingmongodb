package Students.Internship.repo;
import Students.Internship.model.internApply;
import org.springframework.data.mongodb.repository.MongoRepository;


	public interface internApplyRepo extends MongoRepository<internApply, String> {

}
