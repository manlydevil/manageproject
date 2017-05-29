/**Copyright(C) 2017
 *ListConfigTimeController.java, Mar 25, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manageproject.entities.formbean.ConfigTimeFormBean;
import manageproject.logic.ConfigTimeLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Controller
public class ListConfigTimeController {
	@Autowired
	private ConfigTimeLogic configTimeLogic;
	@RequestMapping(value="ListConfigTime.do", method={RequestMethod.POST, RequestMethod.GET})
	public String showList(ModelMap modelMap, HttpServletRequest request) {
		try {
			String sortType = Constants.ASC;
			String sortBy = "c.configTimeID";
			List<ConfigTimeFormBean> listConfigTime = configTimeLogic.getListConfigTime(sortBy, sortType);
			modelMap.addAttribute("listConfigTime", listConfigTime);
			return "formListConfigTime";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@RequestMapping(value="DeleteConfig.do", method={RequestMethod.POST})
	public String deleteConfig(ModelMap modelMap, HttpServletRequest request) {
		try {
			int configID = Integer.parseInt(request.getParameter("ID"));
			boolean rs = configTimeLogic.deleteConfig(configID);
			if(rs) {
				return "redirect:ListConfigTime.do";
			} 
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
}
