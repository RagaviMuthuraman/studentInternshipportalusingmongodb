package Students.Internship.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "internship_courses")
public class internshipCourse {

    @Id
    private String id;  // ✅ MongoDB document ID (do not use courseId as @Id)


    @Field("courseId") // ✅ Important: make sure this is here
    private String courseId;

    private String title;
    private String description;

    // Constructors
    public internshipCourse() {}

    public internshipCourse(String courseId, String title, String description) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
