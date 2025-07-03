package Students.Internship.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Students.Internship.model.AdminUser;
import Students.Internship.model.internApply;
import Students.Internship.repo.AdminRepo;
import Students.Internship.repo.internApplyRepo;
import Students.Internship.repo.studentInternRepo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class adminsetupController {

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private internApplyRepo applyRepo;
    @Autowired
    private studentInternRepo repo;
    @Autowired
    private internApplyRepo internApplyRepo;

    @GetMapping("/admin-login")
    public String showLoginForm() {
        return "admin_login";
    }

    @PostMapping("/admin-login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        AdminUser admin = adminRepo.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            session.setAttribute("isAdmin", true);
            return "redirect:/inter_list";
        } else {
            model.addAttribute("error", "Invalid Username or Password");
            return "admin_login";
        }
    }

    @GetMapping("/inter_list")
    public String showAllInterns(Model model, HttpSession session) {
        if (session.getAttribute("isAdmin") == null) {
            return "redirect:/admin-login";
        }

        List<internApply> interns = applyRepo.findAll();
        model.addAttribute("interns", interns);
        return "inter_list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin-login";
    }

    @GetMapping("/admin/downloadInterns")
    public void downloadInterns(HttpServletResponse response) throws IOException {
        List<internApply> interns = applyRepo.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Interns");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Email");
        header.createCell(3).setCellValue("Course ID");
        header.createCell(4).setCellValue("Applied Course");
        header.createCell(5).setCellValue("Resume Link");
        header.createCell(6).setCellValue("Status");

        int rowNum = 1;
        for (internApply intern : interns) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(intern.getId());
            row.createCell(1).setCellValue(intern.getStudentName());
            row.createCell(2).setCellValue(intern.getEmail());
            row.createCell(3).setCellValue(intern.getCourseId());
            row.createCell(4).setCellValue(intern.getAppliedCourse());
            row.createCell(5).setCellValue(intern.getResumeLink());
            row.createCell(6).setCellValue(intern.getStatus());
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=interns.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @PostMapping("/admin/uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile file, HttpSession session, Model model) {
        Object admin = session.getAttribute("isAdmin");
        if (admin == null) {
            return "redirect:/admin-login";
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String studentName = row.getCell(0).getStringCellValue();
                String email = row.getCell(1).getStringCellValue();
                String appliedCourse = row.getCell(2).getStringCellValue();
                String resumeLink = row.getCell(3).getStringCellValue();
                String courseId = row.getCell(4).getStringCellValue();
                String status = row.getCell(5).getStringCellValue();

                List<internApply> existingInterns = internApplyRepo.findByEmail(email);
                if (!existingInterns.isEmpty()) {
                    // Update all existing ones
                    for (internApply intern : existingInterns) {
                        intern.setStudentName(studentName);
                        intern.setAppliedCourse(appliedCourse);
                        intern.setResumeLink(resumeLink);
                        intern.setCourseId(courseId);
                        intern.setStatus(status);
                        internApplyRepo.save(intern);
                    }
                } else {
                    // New insert
                    internApply intern = new internApply();
                    intern.setStudentName(studentName);
                    intern.setEmail(email);
                    intern.setAppliedCourse(appliedCourse);
                    intern.setResumeLink(resumeLink);
                    intern.setCourseId(courseId);
                    intern.setStatus(status);
                    internApplyRepo.save(intern);
                }

            }

            model.addAttribute("message", "Excel uploaded and data saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Error uploading file: " + e.getMessage());
        }

        return "inter_list";
    }

    @PostMapping("/admin/update-status")
    public String updateStatus(@RequestParam String internId, @RequestParam String status, RedirectAttributes redirectAttributes) {
        Optional<internApply> optionalIntern = applyRepo.findById(internId);
        if (optionalIntern.isPresent()) {
            internApply intern = optionalIntern.get();
            String currentStatus = intern.getStatus();

            if ("Confirmed".equalsIgnoreCase(currentStatus)) {
                redirectAttributes.addFlashAttribute("error", "Status already confirmed. You can't change it.");
                return "redirect:/inter_list";
            }

            if (status.equals("Applied") || status.equals("Not Confirmed") || status.equals("Confirmed")) {
                intern.setStatus(status);
                applyRepo.save(intern);
            } else {
                redirectAttributes.addFlashAttribute("error", "Invalid status update!");
            }
        }
        return "redirect:/inter_list";
    }
} 
