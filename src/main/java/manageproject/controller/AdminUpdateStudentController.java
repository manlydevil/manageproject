/**Copyright(C) 2017
 *AdminUpdateStudentController.java, May 21, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manageproject.entities.ClassBean;
import manageproject.entities.EduProgramBean;
import manageproject.entities.SubjectBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.StudentFormBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.entities.formbean.TeacherFormBean;
import manageproject.logic.ClassLogic;
import manageproject.logic.EduProgramLogic;
import manageproject.logic.StudentLogic;
import manageproject.logic.SubjectLogic;
import manageproject.logic.TeacherLogic;
import manageproject.logic.TermLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;
import manageproject.validate.UpdateInforValidate;

/**
 * @author DELL
 *
 */
@Controller
public class AdminUpdateStudentController {
	
	@Autowired
	private StudentLogic studentLogic;
	@Autowired
	private TermLogic termLogic;
	@Autowired
	private EduProgramLogic eduProgramLogic;
	@Autowired
	private TeacherLogic teacherLogic;
	@Autowired
	private UpdateInforValidate validator;
	@Autowired
	private ClassLogic classLogic;
	@Autowired
	private SubjectLogic subjectLogic;
	
	@RequestMapping(value="UpdateInfor.do", method = RequestMethod.POST)
	public String updateInfor(ModelMap modelMap,HttpServletRequest request, @Valid @ModelAttribute("studentInfor") StudentFormBean studentInfor, BindingResult result) {
		try {
			String ssKey = request.getParameter("ssKey");
			validator.validate(studentInfor, result);
			if(!result.hasErrors()) {
				if(studentLogic.saveOrUpdateInfor(studentInfor)){		
					result.rejectValue("studentNumber", "UpdateCompleted");
				}
			}
			modelMap.addAttribute("studentInfor", studentInfor);
			modelMap.addAttribute("ssKey", ssKey);
			return "formAdminUpdate";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@RequestMapping(value="/Update/{ssKey}", method = RequestMethod.GET)
	public String showInfor(ModelMap modelMap, HttpServletRequest request, @PathVariable Optional<String> ssKey) {
		try {
			int studentNumber = Integer.parseInt(request.getParameter("id"));
			StudentInforBean studentInforBean = studentLogic.getFullInfor(studentNumber);
			if(ssKey.isPresent()) {
				modelMap.addAttribute("ssKey", ssKey.get());
			}
			modelMap.addAttribute("studentInfor", studentInforBean);
			return "formAdminUpdate";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	
	
	@ModelAttribute("lstTerm")
	public List<TermBean> getAllTerm() {
		return termLogic.getAllTerm();
	}
	
	@ModelAttribute("lstEduProgram")
	public List<EduProgramBean> getAllEduProgram() {
		return eduProgramLogic.getAllEduProgram();
	}
	
	@ModelAttribute("lstTeacher") 
	public List<TeacherFormBean> getAllTeacher() {
		return teacherLogic.getAllTeacher();
	}
	
	@ModelAttribute("lstClass")
	public List<ClassBean> getAllClass() {
		return classLogic.getSelectBoxClass();
	}
	
	@ModelAttribute("lstSubject")
	public List<SubjectBean> getAllSubject() {
		return subjectLogic.getSelectBoxSubject();
	}
	
	@RequestMapping(value="/InsertStudent.do", method = RequestMethod.GET)
	public String newStudent (ModelMap modelMap, HttpServletRequest request, HttpSession session) {
		try {
			String ssKey = request.getParameter("ssKey");
			// tạo mới session hoặc lấy từ request
			String key = ssKey == null ? Common.getKey() : ssKey;
			// đưa đối tượng lên session
			session.setAttribute(key, new StudentInforBean());
			// đưa lên request
			modelMap.addAttribute("ssKey", key);
			modelMap.addAttribute("studentInfor", new StudentInforBean());
			return "formAdminUpdate";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
}
