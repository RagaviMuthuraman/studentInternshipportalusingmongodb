package Students.Internship.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "internship_courses")
public class internshipCourse {



	    @Id
	    private String id;
	    private String title;
	    private String description;

	    // constructors
	    public internshipCourse() {}

	    public internshipCourse(String title, String description) {
	        this.title = title;
	        this.description = description;
	    }

	    // getters & setters
	    public String getId() { return id; }
	    public void setId(String id) { this.id = id; }

	    public String getTitle() { return title; }
	    public void setTitle(String title) { this.title = title; }

	    public String getDescription() { return description; }
	    public void setDescription(String description) { this.description = description; }
	}


