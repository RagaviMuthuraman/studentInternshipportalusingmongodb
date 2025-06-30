package Students.Internship.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "course_applications")

public class internApply {
	
	    private String studentName;
	    private String email;
	    private String appliedCourse;
	    private String resumeLink;
	    // getters, setters, etc.
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
	}


