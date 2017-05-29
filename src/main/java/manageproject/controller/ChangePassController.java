/**Copyright(C) 2017
 *ChangePassController.java, Mar 31, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manageproject.entities.formbean.AccountInfor;
import manageproject.entities.formbean.BasicInforFormBean;
import manageproject.entities.formbean.BasicInforFormBean.changePass;
import manageproject.logic.StudentLogic;
import manageproject.utils.Constants;
import manageproject.validate.ChangePassValidate;

/**
 * @author DELL
 *
 */
@Controller
public class ChangePassController {
	@Autowired
	private ChangePassValidate validator;
	@Autowired
	private StudentLogic studentLogic;
	
	@RequestMapping(value="ChangePass.st", method=RequestMethod.GET)
	public String formChangePass(ModelMap modelMap) {
		modelMap.addAttribute("basicInfor", new BasicInforFormBean());
		return "formChangePass";
	}
	
	@RequestMapping(value="ChangePass.st", method=RequestMethod.POST)
	public String changePass(ModelMap modelMap, HttpSession session, @Validated(value=changePass.class) @ModelAttribute("basicInfor") BasicInforFormBean formBean, BindingResult result) {
		try {
			validator.validate(formBean, result);
			if(!result.hasErrors()) {
				AccountInfor account = (AccountInfor) session.getAttribute("accountSess");
				String userName = account.getUserName();
				if(studentLogic.changePass(formBean.getNewPass(), userName)) {
					result.rejectValue("newPass", "CHANGEPASS_SUCCESS");
				}
			}
			return "formChangePass";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
}
