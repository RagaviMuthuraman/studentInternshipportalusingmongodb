package Students.Internship.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "courseDetails")
public class courseDetails {
	


	

	    @Id
	    private String id;

	    @Field("courseId")
	    private String courseId;

	    @Field("courseName")
	    private String courseName;

	    @Field("description")
	    private String description;

	    // Getters and Setters
	    public String getId() {
	        return id;
	    }
	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getCourseId() {
	        return courseId;
	    }
	    public void setCourseId(String courseId) {
	        this.courseId = courseId;
	    }

	    public String getCourseName() {
	        return courseName;
	    }
	    public void setCourseName(String courseName) {
	        this.courseName = courseName;
	    }

	    public String getDescription() {
	        return description;
	    }
	    public void setDescription(String description) {
	        this.description = description;
	    }
	}


