/**Copyright(C) 2017
 *LogoutController.java, May 5, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manageproject.entities.formbean.AccountInfor;

/**
 * @author DELL
 *
 */
@Controller
public class LogoutController {
	
	/**
	 * Phương thức xử lý khi vào logout
	 * @param modelMap ModelMap
	 * @param session HttpSession
	 * @return MH01
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(ModelMap modelMap, HttpSession session) {
		// lấy tất cả tên các thuộc tính đưa lên session
		Enumeration<?> eAttr = session.getAttributeNames();
		while(eAttr.hasMoreElements()) {
			// duyệt lần lượt để xóa
			String ssName = (String) eAttr.nextElement();
			session.removeAttribute(ssName);
		}
		modelMap.addAttribute("account", new AccountInfor());
		return "welcome";
	}
}
