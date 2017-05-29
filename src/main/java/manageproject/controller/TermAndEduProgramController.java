/**Copyright(C) 2017
 *TermAndEduProgramController.java, May 14, 2017 [Nguyễn Hưng Thuận]
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

import manageproject.entities.ClassBean;
import manageproject.entities.EduProgramBean;
import manageproject.entities.SubjectBean;
import manageproject.entities.TermBean;
import manageproject.logic.EduProgramLogic;
import manageproject.logic.TermLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Controller
@PropertySource(value="classpath:msg_error.properties", encoding="UTF-8")
public class TermAndEduProgramController {
	@Autowired
	private TermLogic termLogic;
	@Autowired
	private EduProgramLogic eduProgramLogic;
	@Autowired
	private Environment env;
	
	@GetMapping(value="TermAndEduProgram.do")
	public String show(ModelMap modelMap) {
		try {
			List<TermBean> listTerm = termLogic.getListTerm();
			List<EduProgramBean> listEduPr = eduProgramLogic.getListEduProgram();
			modelMap.addAttribute("listTerm", listTerm);
			modelMap.addAttribute("listEduPr", listEduPr);
			modelMap.addAttribute("termInfor", new TermBean());
			modelMap.addAttribute("eduProgramInfor", new EduProgramBean());
			return "formTermAndEduProgram";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@RequestMapping(value={"Term.do", "Term/{page}"}, method={RequestMethod.GET, RequestMethod.POST})
	public String termController (ModelMap modelMap, @PathVariable("page") Optional<String> page, @Valid @ModelAttribute("termInfor") TermBean termBean, BindingResult result) {
		try {
			if(!result.hasErrors()) {
					termLogic.insertOrUpdateTerm(termBean);
			}
			List<TermBean> listTerm = termLogic.getListTerm();
			List<EduProgramBean> listEduPr = eduProgramLogic.getListEduProgram();
			modelMap.addAttribute("listTerm", listTerm);
			modelMap.addAttribute("listEduPr", listEduPr);
			modelMap.addAttribute("termInfor", new TermBean());
			modelMap.addAttribute("eduProgramInfor", new EduProgramBean());
			return "formTermAndEduProgram";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}

	@RequestMapping(value={"EduProgram.do", "EduProgram/{page}"}, method={RequestMethod.GET, RequestMethod.POST})
	public String eduProgramController (ModelMap modelMap, @PathVariable("page") Optional<String> page, @Valid @ModelAttribute("eduProgramInfor") EduProgramBean eduProgramBean, BindingResult result) {
		try {
			if(!result.hasErrors()) {
					eduProgramLogic.insertOrUpdateEdu(eduProgramBean);
			}
			List<TermBean> listTerm = termLogic.getListTerm();
			List<EduProgramBean> listEduPr = eduProgramLogic.getListEduProgram();
			modelMap.addAttribute("listTerm", listTerm);
			modelMap.addAttribute("listEduPr", listEduPr);
			modelMap.addAttribute("termInfor", new TermBean());
			modelMap.addAttribute("eduProgramInfor", new EduProgramBean());
			return "formTermAndEduProgram";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@PostMapping(value="/DeleteTerm.do")
	public String deleteTerm(ModelMap modelMap, HttpServletRequest request, RedirectAttributes attribute) {
		try {
			int termID = Integer.parseInt(request.getParameter("termID"));
			if(!termLogic.deleteTerm(termID)) {
				//attribute.addFlashAttribute("class_errmsg", env.getProperty("Cannot_del_class"));
			}
			return "redirect:/TermAndEduProgram.do";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@PostMapping(value="/DeleteEduProgram.do")
	public String deleteEduProgram(ModelMap modelMap, HttpServletRequest request, RedirectAttributes attributes) {
		try {
			int eduID = Integer.parseInt(request.getParameter("eduProgramID"));
			if(!eduProgramLogic.deleteEduPr(eduID)) {
				//attributes.addFlashAttribute("sub_errmsg", env.getProperty("Cannot_del_subject"));
			}
			return "redirect:/TermAndEduProgram.do";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}

	@RequestMapping(value="setTermTB.do", method=RequestMethod.GET)
	public @ResponseBody TermBean setTermTB(HttpServletRequest request){
		int id = request.getParameter("id") == null ? 1 : Integer.parseInt(request.getParameter("id"));
		return termLogic.getTermByID(id);
	}
	
	@RequestMapping(value="setEduProgramTB.do", method=RequestMethod.GET)
	@ResponseBody 
	public EduProgramBean setEduProgramTB(HttpServletRequest request){
		int id = request.getParameter("id") == null ? 1 : Integer.parseInt(request.getParameter("id"));
		return eduProgramLogic.getEduProgramByID(id);
	}
}
