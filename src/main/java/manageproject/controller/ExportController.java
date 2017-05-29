/**Copyright(C) 2017
 *ExportController.java, Apr 6, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.logic.ProjectLogic;
import manageproject.logic.StudentLogic;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Controller
@PropertySource(value="classpath:label_text.properties", encoding="UTF-8")
public class ExportController {
	@Autowired
	private StudentLogic studentLogic;
	@Autowired
	private Environment env;
	@Autowired
	private ProjectLogic projectLogic;
	
	@RequestMapping(value="DownloadListStudent.do", method=RequestMethod.POST)
	public String downloadListStudent(HttpServletResponse response, @ModelAttribute("inforSearchFormBean") InforSearchFormBean  inforSearchFormBean, HttpServletRequest request) {
		ICsvBeanWriter csvBeanWriter = null;
		try {
			String fileName = Constants.LIST_STU_FILENAME;
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName);
			// Cấu hình header cho file export
			response.setHeader(headerKey, headerValue);
			response.setContentType("text/csv; character:UTF-8");
			// Thêm BOM(Byte of mark) vào đầu file để có thể hiển thị tiếng Việt
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(0xEF);   
			outputStream.write(0xBB);
			outputStream.write(0xBF);  
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			List<StudentInforBean> listStudent = studentLogic.getListDataExport(inforSearchFormBean);
			// Tạo đối tượng csvbeanwriter với writer vừa thêm BOM
			csvBeanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			// Set header cho dữ liệu trong bảng
			String[] headerUser = { Constants.ID, Constants.FULLNAME, Constants.EMAIL, Constants.PHONE, Constants.TERM, Constants.EDU_PROGRAM, Constants.WORK_PLACE, Constants.OFFICE};
			// Tiêu đề các cột khi export
			String[] headerShow = {env.getProperty("STUDENT_ID"), env.getProperty("STUDENT_NAME"), env.getProperty("EMAIL"), env.getProperty("PHONENUMBER"), env.getProperty("TERM"), env.getProperty("EDU_PROGRAM"), env.getProperty("WORKPLACE"), env.getProperty("OFFICE")};
			csvBeanWriter.writeHeader(headerShow);
			for(StudentInforBean student : listStudent){
				csvBeanWriter.write(student, headerUser);
			}
			return "redirect:/ListStudent.do";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		} finally {
			if(csvBeanWriter != null) {
				try {
					csvBeanWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(value="DownloadListProject.do", method=RequestMethod.POST)
	public String downloadListProject(HttpServletResponse response, @ModelAttribute("inforSearchFormBean") InforSearchFormBean  inforSearchFormBean, HttpServletRequest request) {
		try {
			String fileName = Constants.LIST_PRO_FILENAME;
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", fileName);
			// Cấu hình header cho file export
			response.setHeader(headerKey, headerValue);
			response.setContentType("text/csv; character:UTF-8");
			// Thêm BOM(Byte of mark) vào đầu file để có thể hiển thị tiếng Việt
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(0xEF);   
			outputStream.write(0xBB);
			outputStream.write(0xBF);  
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			List<ProjectInforBean> listProject = projectLogic.getListDataExport(inforSearchFormBean);
			// Tạo đối tượng csvbeanwriter với writer vừa thêm BOM
			ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
			// Set header cho dữ liệu trong bảng
			String[] headerUser = { Constants.PROJECT_ID, Constants.PROJECT_NAME, Constants.SUBMIT_DATE, Constants.STUDENT_NAME, Constants.INSTRUCTOR_TEACHER, Constants.REVIEW_TEACHER, Constants.EDU_PROGRAM, Constants.TERM};
			// Tiêu đề các cột khi export
			String[] headerShow = {env.getProperty("ID"), env.getProperty("PROJECT_NAME"), env.getProperty("SUBMIT_DATE"), env.getProperty("STUDENT_NAME"), env.getProperty("INSTRUCTOR_TEACHER"), env.getProperty("REVIEW_TEACHER"), env.getProperty("EDU_PROGRAM"), env.getProperty("TERM")};
			csvBeanWriter.writeHeader(headerShow);
			for(ProjectInforBean project : listProject){
				csvBeanWriter.write(project, headerUser);
			}
			csvBeanWriter.close();
			return "redirect:/ListProject.do";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
}}
