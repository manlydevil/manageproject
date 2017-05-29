/**Copyright(C) 2017  
 *LoginController.java, Mar 11, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import manageproject.entities.formbean.AccountInfor;
import manageproject.utils.Common;
import manageproject.utils.Constants;
import manageproject.validate.LoginValidate;

/**
 * @author DELL
 *
 */
@Controller
@SessionAttributes(names="accountSess")
public class LoginController {
	
	@Autowired
	private LoginValidate loginValidator;
	
	@RequestMapping(value={"/login","/login/{actor}"},method=RequestMethod.GET)
	public String login(ModelMap modelMap, @PathVariable Optional<String> actor) {
		modelMap.addAttribute("accountInfor", new AccountInfor());
		return "formLogin";
	}
	
	@RequestMapping(value={"/login","/login/{actor}"},method=RequestMethod.POST)
	public String login(ModelMap modelMap,@Valid @ModelAttribute("accountInfor") AccountInfor accountInfor, BindingResult result, @PathVariable("actor") Optional<String> actor, HttpSession session) {
		try {
				String priority = "";
				if(actor.isPresent()) {
					priority = actor.get().toLowerCase();
				}
				accountInfor.setPriority(priority);
				// validate account
				loginValidator.validate(accountInfor, result);
				// nếu có lỗi
				if (result.hasErrors()) {
					modelMap.addAttribute("accountInfor", accountInfor);
					// trả về MH01 với thông tin vừa nhập vào
					return Constants.FORMLOGIN;
				}
				// nếu không có lỗi
				accountInfor.setPassword(Common.encryptMD5(accountInfor.getPassword()));
				// đưa account lên session 
				modelMap.addAttribute("accountSess", accountInfor);
				if(priority.equals(Constants.ADMIN)) {
					session.setAttribute("adminSession", accountInfor);
					return Constants.RE_FORM_LIST;
				} else if(priority.equals(Constants.STUDENT)) {
					session.setAttribute("studentSession", accountInfor);
					return Constants.RE_FORM_UPDATE;
				} else {
					session.setAttribute("teacherSession", accountInfor);
					return Constants.RE_FORM_LISTASSIGN;
				}
		} catch (Exception ex) {
			ex.printStackTrace();
			return Constants.FORMLOGIN;
		}
	}
}
