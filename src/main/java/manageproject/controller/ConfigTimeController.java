/**Copyright(C) 2017
 *ConfigTimeController.java, Mar 17, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manageproject.entities.EduProgramBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.ConfigTimeFormBean;
import manageproject.logic.ConfigTimeLogic;
import manageproject.logic.EduProgramLogic;
import manageproject.logic.TermLogic;
import manageproject.utils.Constants;
import manageproject.validate.ConfigTimeValidate;

/**
 * @author DELL
 *
 */
@Controller
public class ConfigTimeController {
	@Autowired
	private TermLogic termLogic;
	@Autowired
	private EduProgramLogic eduProgramLogic;
	@Autowired
	private ConfigTimeLogic configTimeLogic;
	@Autowired
	private ConfigTimeValidate validator;
	
	@RequestMapping(value="NewConfig.do", method=RequestMethod.GET)
	public String createConfigTime(ModelMap modelMap) {
		modelMap.addAttribute("configTime", new ConfigTimeFormBean());
		return "formConfigTime";
	}
	
	@RequestMapping(value="UpdateConfig.do", method=RequestMethod.POST) 
	public String updateConfig(ModelMap modelMap, @Valid @ModelAttribute("configTime") ConfigTimeFormBean configTime, BindingResult results) {
		try {
			validator.validate(configTime, results);
			if(results.hasErrors()) {
				modelMap.addAttribute("configTime", configTime);
				return "formConfigTime";
			}
			configTimeLogic.insertOrUpdate(configTime);
			return "redirect:ListConfigTime.do";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@RequestMapping(value="UpdateConfig.do", method=RequestMethod.GET)
	public String showConfig(ModelMap modelMap, HttpServletRequest request) {
		try {
			int configTimeID = Integer.parseInt(request.getParameter("id"));
			ConfigTimeFormBean formBean = configTimeLogic.getConfigByID(configTimeID);
			modelMap.addAttribute("configTime", formBean);
			return "formConfigTime";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@ModelAttribute("listTerm")
	public List<TermBean> getAllTerm() {
		return termLogic.getAllTerm();
	}
	
	@ModelAttribute("listEduProgram")
	public List<EduProgramBean> getAllEduProgram() {
		return eduProgramLogic.getAllEduProgram();
	}

}
