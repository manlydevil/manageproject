/**Copyright(C) 2017
 *ClassAndSubjectController.java, Apr 7, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import manageproject.entities.ClassBean;
import manageproject.entities.SubjectBean;
import manageproject.logic.ClassLogic;
import manageproject.logic.SubjectLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Controller
@PropertySource(value="classpath:msg_error.properties", encoding="UTF-8")
public class ClassAndSubjectController {
	@Autowired
	private SubjectLogic subjectLogic;
	@Autowired
	private ClassLogic classLogic;
	@Autowired
	private Environment env;
	
	@RequestMapping(value={"AddSubject.do"}, method={RequestMethod.GET, RequestMethod.POST})
	public String subjectController (ModelMap modelMap, @PathVariable("page") Optional<String> page, @Valid @ModelAttribute("subjectInfor") SubjectBean subjectBean, BindingResult result) {
		try {
			if(!result.hasErrors()) {
				subjectLogic.addSubject(subjectBean);
			}
			List<ClassBean> listClass = classLogic.getListClass(0, 0);
			List<SubjectBean> listSubject = subjectLogic.getListSubject(0, 0);
			modelMap.addAttribute("listClass", listClass);
			modelMap.addAttribute("listSubject", listSubject);
			modelMap.addAttribute("classInfor", new ClassBean());
			modelMap.addAttribute("subjectInfor", new SubjectBean());
			return "formClassAndSubject";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@RequestMapping(value={"AddClass.do"}, method={RequestMethod.GET, RequestMethod.POST})
	public String classController (ModelMap modelMap, @PathVariable("page") Optional<String> page, @Valid @ModelAttribute("classInfor") ClassBean classBean, BindingResult result) {
		try {
			if(!result.hasErrors()) {
				classLogic.addClass(classBean);
			}
			List<ClassBean> listClass = classLogic.getListClass(0, 0);
			List<SubjectBean> listSubject = subjectLogic.getListSubject(0, 0);
			modelMap.addAttribute("listClass", listClass);
			modelMap.addAttribute("listSubject", listSubject);
			modelMap.addAttribute("classInfor", new ClassBean());
			modelMap.addAttribute("subjectInfor", new SubjectBean());
			return "formClassAndSubject";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@GetMapping(value={"/ClassAndSubject.do"})
	public String show(ModelMap modelMap) {
		try {
			List<ClassBean> listClass = classLogic.getListClass(0, 0);
			List<SubjectBean> listSubject = subjectLogic.getListSubject(0, 0);
			modelMap.addAttribute("listClass", listClass);
			modelMap.addAttribute("listSubject", listSubject);
			modelMap.addAttribute("classInfor", new ClassBean());
			modelMap.addAttribute("subjectInfor", new SubjectBean());
			return "formClassAndSubject";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@PostMapping(value="/DeleteClass.do")
	public String deleteClass(ModelMap modelMap, HttpServletRequest request, RedirectAttributes attribute) {
		try {
			int classID = Integer.parseInt(request.getParameter("classID"));
			if(!classLogic.deleteClass(classID)) {
				attribute.addFlashAttribute("class_errmsg", env.getProperty("Cannot_del_class"));
			}
			return "redirect:/ClassAndSubject.do";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@PostMapping(value="/DeleteSubject.do")
	public String deleteSubject(ModelMap modelMap, HttpServletRequest request, RedirectAttributes attributes) {
		try {
			int subjectID = Integer.parseInt(request.getParameter("subjectID"));
			if(!subjectLogic.deleteSubject(subjectID)) {
				attributes.addFlashAttribute("sub_errmsg", env.getProperty("Cannot_del_subject"));
			}
			return "redirect:/ClassAndSubject.do";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}

	@RequestMapping(value="setClassTB.do", method=RequestMethod.GET)
	public @ResponseBody ClassBean setClassTB(HttpServletRequest request){
		int id = request.getParameter("id") == null ? 1 : Integer.parseInt(request.getParameter("id"));
		return classLogic.getClassByID(id);
	}
	
	@RequestMapping(value="setSubjectTB.do", method=RequestMethod.GET)
	@ResponseBody 
	public SubjectBean setSubjectTB(HttpServletRequest request){
		int id = request.getParameter("id") == null ? 1 : Integer.parseInt(request.getParameter("id"));
		return subjectLogic.getSubjectByID(id);
	}
}
