/**Copyright(C) 2017
 *ImportFileController.java, May 9, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.cellprocessor.ConvertNullTo;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.mysql.cj.fabric.xmlrpc.base.Array;

import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.StudentFormBean;
import manageproject.logic.ClassLogic;
import manageproject.logic.EduProgramLogic;
import manageproject.logic.ProjectLogic;
import manageproject.logic.StudentLogic;
import manageproject.logic.SubjectLogic;
import manageproject.logic.TeacherLogic;
import manageproject.logic.TermLogic;
import manageproject.utils.Constants;
import manageproject.utils.CustomException;
import manageproject.validate.ImportValidate;
import manageproject.validate.UpdateInforValidate;

/**
 * @author DELL
 *
 */
@Controller
@PropertySource(value="classpath:msg_error.properties", encoding="UTF-8")
public class ImportFileController {
	@Autowired
	private StudentLogic studentLogic;
	@Autowired
	private ProjectLogic projectLogic;
	@Autowired
	private TermLogic termLogic;
	@Autowired
	private EduProgramLogic eduProgramLogic;
	@Autowired
	private TeacherLogic teacherLogic;
	@Autowired
	private ClassLogic classLogic;
	@Autowired
	private SubjectLogic subjectLogic;
	@Autowired
	private Environment env;
	@Autowired
	private ImportValidate validate;
	
	private static CellProcessor[] studentProcessor() {
		final CellProcessor[] processor = new CellProcessor[] {
				new NotNull(), // mã sinh viên
				new NotNull(), // tên sv
				new NotNull(), // email
				new ConvertNullTo(""), // sđt
				new ConvertNullTo(""), // nơi công tác
				new ConvertNullTo(""), // chức vụ
				new NotNull(), // lớp
				new NotNull(), // môn học
				new NotNull(), // học kì
				new NotNull(), // CTDT
		};
		return processor;
	}
	
	private static CellProcessor[] assignInstructionProcessor() {
		final CellProcessor[] processor = new CellProcessor[] {
				new NotNull(), // mã sinh viên
				new NotNull(), // tên sv
				new NotNull(), // email
				new ConvertNullTo(""), // sđt
				new ConvertNullTo(""), // nơi công tác
				new ConvertNullTo(""), // chức vụ
				new NotNull(), // lớp
				new NotNull(), // môn học
				new NotNull(), // học kì
				new NotNull(), // CTDT
				new NotNull(), // gv hướng dẫn
		};
		return processor;
	}
	
	private static CellProcessor[] assignReviewProcessor() {
		final CellProcessor[] processor = new CellProcessor[] {
				new NotNull(), // mã sinh viên
				new NotNull(), // tên sv
				new NotNull(), // email
				new ConvertNullTo(""), // sđt
				new ConvertNullTo(""), // nơi công tác
				new ConvertNullTo(""), // chức vụ
				new NotNull(), // lớp
				new NotNull(), // môn học
				new NotNull(), // học kì
				new NotNull(), // CTDT
				new NotNull(), // gv hướng dẫn
				new NotNull(), // gv phản biện
		};
		return processor;
	}
	
	private static CellProcessor[] projectProcessor() {
		final CellProcessor[] processors = new CellProcessor[] {
				new NotNull(), // tên đồ án
				new ConvertNullTo(""), // ngày nộp
				new NotNull(), // mã sinh viên
				new NotNull(), // từ khóa
				new NotNull(), // mô tả
				new NotNull(), // ghi chú
				new NotNull(), // sv public
				new NotNull(), // gv public
			};
		return processors;
	}
	
	@GetMapping(value="ImportStudent.do")
	public String form() {
		return "formImportStudent";
	}
	
	@PostMapping(value="ImportST.do")
	public String readStudentCsv(@RequestParam("file") MultipartFile uploadedFile, RedirectAttributes attribute, HttpServletRequest request) throws Exception {
		ICsvBeanReader beanReader = null;
		try {
			if(uploadedFile.getContentType().equals("application/vnd.ms-excel")) {
				String type = request.getParameter("fileType");
				beanReader = new CsvBeanReader(new InputStreamReader(uploadedFile.getInputStream()), CsvPreference.STANDARD_PREFERENCE);
				final String[] header = beanReader.getHeader(true);
	            CellProcessor[] processors = null;
	            if(type.equals("0")) {
	            	processors = studentProcessor();
	            } else if(type.equals("1")) {
	            	processors = assignInstructionProcessor();
	            } else if(type.equals("2")) {
	            	processors = assignReviewProcessor();
	            }
	            List<StudentFormBean> listStudent = new ArrayList<StudentFormBean>();
	            StudentFormBean studentFormBean;
	            Map<String, String> mapErrors = new HashMap<String,String>();
	            while((studentFormBean = beanReader.read(StudentFormBean.class, header, processors)) != null) {
	            	studentFormBean = standardizedStudent(studentFormBean);
	            	mapErrors = validate.validate(studentFormBean);
	            	if(mapErrors.size() > 0) {
	            		List<String> message = new ArrayList<String>(mapErrors.values());
	            		attribute.addFlashAttribute("message", message);
	            		return "redirect:/ImportStudent.do";
	            	}
	            	listStudent.add(studentFormBean);
	            }
		        if(!(studentLogic.insertListStudent(listStudent))) {
		          	attribute.addFlashAttribute("message", env.getProperty("ImportFailed"));
		        }
			} else {
				attribute.addFlashAttribute("message", env.getProperty("Invalid.FileImport"));
			}
            return "redirect:/ListStudent.do";
		} catch(CustomException ex) {
			attribute.addFlashAttribute("message", uploadedFile.getOriginalFilename()+" "+ex.getMessage());
			return "redirect:/ImportStudent.do";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:/error.do?error="+Constants.SYSTEM_ERROR;
		} finally {
            if( beanReader != null ) {
                beanReader.close();
        }
}
	}
	
	@GetMapping(value="ImportProject.do")
	public String formImport() {
		return "formImportProject";
	}
	
	@PostMapping(value="ImportPJ.do")
	public String readProjectCsv(@RequestParam("file") MultipartFile uploadedFile, RedirectAttributes attribute) throws Exception {
		ICsvBeanReader beanReader = null;
		try {
			if(uploadedFile.getContentType().equals("application/vnd.ms-excel")) {
				beanReader = new CsvBeanReader(new InputStreamReader(uploadedFile.getInputStream()), CsvPreference.STANDARD_PREFERENCE);
				final String[] header = beanReader.getHeader(true);
	            final CellProcessor[] processors = projectProcessor();
	            List<ProjectInforBean> listProject = new ArrayList<ProjectInforBean>();
	            ProjectInforBean projectInforBean;
	            Map<String, String> mapErrors = new HashMap<String,String>();
	            while((projectInforBean = beanReader.read(ProjectInforBean.class, header, processors)) != null) {
	            	mapErrors = validate.validateProject(projectInforBean);
	            	if(mapErrors.size() > 0) {
	            		List<String> message = new ArrayList<String>(mapErrors.values());
	            		attribute.addFlashAttribute("message", message);
	            		return "redirect:/ImportProject.do";
	            	}
	            	listProject.add(projectInforBean);
	            }
	            if(!projectLogic.insertListProject(listProject)) {
	            	attribute.addFlashAttribute("message", env.getProperty("ImportFailed"));
	            } 
			} else {
				attribute.addFlashAttribute("message", env.getProperty("Invalid.FileImport"));
			}
            return "redirect:/ListProject.do";
		} catch(CustomException ce) {
			attribute.addFlashAttribute("message", env.getProperty("Assigned.Student"));
			return "redirect:/ListProject.do";			
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:/error.do?error="+Constants.SYSTEM_ERROR;
		} finally {
            if( beanReader != null ) {
                beanReader.close();
        }
		}
	}
	
	private StudentFormBean standardizedStudent(StudentFormBean studentFormBean) throws CustomException {
		if(!"".equals(classLogic.getClassID(studentFormBean.getStudentClass()))) {
			studentFormBean.setClassID(classLogic.getClassID(studentFormBean.getStudentClass()));
		} else {
			throw new CustomException("Student: "+studentFormBean.getStudentNumber()+", error: "+studentFormBean.getStudentClass());
		}
		
		if(!"".equals(subjectLogic.getSubjectIDBySubjectCode(studentFormBean.getSubject()))) {
			studentFormBean.setSubjectID(subjectLogic.getSubjectIDBySubjectCode(studentFormBean.getSubject()));
		} else {
			throw new CustomException("Student: "+studentFormBean.getStudentNumber()+", error: "+studentFormBean.getSubject());
		}
		
		if(!"".equals(eduProgramLogic.getEduProgramID(studentFormBean.getEduProgram()))) {
			studentFormBean.setEduProgramID(eduProgramLogic.getEduProgramID(studentFormBean.getEduProgram()));
		} else {
			throw new CustomException("Student: "+studentFormBean.getStudentNumber()+", error: "+studentFormBean.getEduProgram());
		}
		
		if(!"".equals(termLogic.getTermIDByTerm(studentFormBean.getTerm()))) {
			studentFormBean.setTermID(termLogic.getTermIDByTerm(studentFormBean.getTerm()));
		} else {
			throw new CustomException("Student: "+studentFormBean.getStudentNumber()+", error: "+studentFormBean.getTerm());
		}
		
		if(!"".equals(teacherLogic.getTeacherIDByName(studentFormBean.getInstructorTeacher()))) {
			studentFormBean.setInstructorTeacherID(teacherLogic.getTeacherIDByName(studentFormBean.getInstructorTeacher()));
		} else if(studentFormBean.getInstructorTeacher() != null) {
			throw new CustomException("Student: "+studentFormBean.getStudentNumber()+", error: "+studentFormBean.getInstructorTeacher());
		}
		
		if(!"".equals(teacherLogic.getTeacherIDByName(studentFormBean.getReviewTeacher()))) {
			studentFormBean.setReviewTeacherID(teacherLogic.getTeacherIDByName(studentFormBean.getReviewTeacher()));
		} else if(studentFormBean.getReviewTeacher() != null) {
			throw new CustomException("Student: "+studentFormBean.getStudentNumber()+", error: "+studentFormBean.getReviewTeacher());
		}
		return studentFormBean;
	}

}
