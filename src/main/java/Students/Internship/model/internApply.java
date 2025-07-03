// 1. internApply.java (Optimized for Excel)
package Students.Internship.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "course_applications")
public class internApply {

    @Id
    private String id; // Unique ID for each application
    private String studentName;
    private String email;
    private String appliedCourse;
    private String resumeLink;
    private String courseId;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppliedCourse() {
        return appliedCourse;
    }

    public void setAppliedCourse(String appliedCourse) {
        this.appliedCourse = appliedCourse;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}