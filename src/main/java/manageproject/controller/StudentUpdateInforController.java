/**Copyright(C) 2017
 *StudentUpdateInforController.java, May 21, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manageproject.entities.ClassBean;
import manageproject.entities.EduProgramBean;
import manageproject.entities.SubjectBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.AccountInfor;
import manageproject.entities.formbean.BasicInforFormBean;
import manageproject.entities.formbean.TeacherFormBean;
import manageproject.logic.ClassLogic;
import manageproject.logic.EduProgramLogic;
import manageproject.logic.StudentLogic;
import manageproject.logic.SubjectLogic;
import manageproject.logic.TeacherLogic;
import manageproject.logic.TermLogic;
import manageproject.utils.Constants;
import manageproject.validate.UpdateInforValidate;

/**
 * @author DELL
 *
 */
@Controller
public class StudentUpdateInforController {
	@Autowired
	private StudentLogic studentLogic;
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
	
	@RequestMapping(value="UpdateInformation.st", method = RequestMethod.GET)
	public String showInfor(ModelMap modelMap, HttpSession session) {
		try {
			AccountInfor account = (AccountInfor) session.getAttribute("accountSess");
			String userName = account.getUserName();
			BasicInforFormBean formBean = studentLogic.getStuInforByID(userName);
			modelMap.addAttribute("basicInfor", formBean);
			return "formStudentUpdate";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@RequestMapping(value="/Update.st", method = RequestMethod.POST)
	public String studentUpdate(ModelMap modelMap, HttpSession session, @Valid @ModelAttribute("basicInfor") BasicInforFormBean formBean, BindingResult result) {
		try {
			if(!result.hasErrors()) {
				AccountInfor account = (AccountInfor) session.getAttribute("accountSess");
				String userName = account.getUserName();
				if(studentLogic.updateBasicInfor(formBean, userName)) {		
					result.rejectValue("name", "UpdateCompleted");
				}
			}
			modelMap.addAttribute("studentInfor", formBean);
			return "formStudentUpdate";
		} catch(Exception ex) {
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
}
