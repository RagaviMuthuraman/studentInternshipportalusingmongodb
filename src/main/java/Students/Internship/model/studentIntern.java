package Students.Internship.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "student_interns")
public class studentIntern {
	

	
	private String password;


	    @Id
	    private String id;
	    public String getId() {
			return id;
		}
	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCollege() {
			return college;
		}
		public void setCollege(String college) {
			this.college = college;
		}
		public String getDomain() {
			return domain;
		}
		public void setDomain(String domain) {
			this.domain = domain;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getResumeLink() {
			return resumeLink;
		}
		public void setResumeLink(String resumeLink) {
			this.resumeLink = resumeLink;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getAppliedDate() {
			return appliedDate;
		}
		public void setAppliedDate(String appliedDate) {
			this.appliedDate = appliedDate;
		}
		private String name;
	    private String college;
	    private String domain;
	    private String email;
	    private String phone;
	    private String resumeLink;
	    private String status; // e.g., Applied, Selected, Rejected
	    private String appliedDate;

	    // Getters & Setters
	}

