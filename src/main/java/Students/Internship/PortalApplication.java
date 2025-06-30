package Students.Internship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import Students.Internship.model.AdminUser;
import Students.Internship.repo.AdminRepo;

@SpringBootApplication
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadDefaultAdmin(AdminRepo adminUserRepo) {
		return args -> {
			if (adminUserRepo.findByUsername("admin") == null) {
				AdminUser admin = new AdminUser();
				admin.setUsername("admin");
				admin.setPassword("admin123");
				adminUserRepo.save(admin);
				System.out.println("✅ Default admin created");
			} else {
				System.out.println("✅ Admin already exists");
			}
		};
	}
}

