/**Copyright(C) 2017
 *ListTeacherController.java, May 5, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.InforSearchTeacherBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.entities.formbean.TeacherFormBean;
import manageproject.logic.TeacherLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Controller
public class ListTeacherController {
	@Autowired
	private TeacherLogic teacherLogic;
	
	@RequestMapping(value={"/ListTeacher.do", "/ListTeacher/{action}/{page}", "/ListTeacher/{action}.do"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String showListTeacher(ModelMap modelMap, @ModelAttribute("inforSearch") InforSearchTeacherBean formBean,
			@PathVariable Optional<String> action, @PathVariable Optional<String> page, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if(action.isPresent() && action.get().equals(Constants.ACTION_BACK)) {
					InforSearchTeacherBean inforSearch = (InforSearchTeacherBean) session.getAttribute(request.getParameter("ssKey"));
					formBean = inforSearch == null ? new InforSearchTeacherBean() : inforSearch;
			}
			List<TeacherFormBean> listTeacher = teacherLogic.getListTeacher(formBean, 0, 0);
			String ssKey = request.getParameter("ssKey");
			// tạo mới session hoặc lấy từ request
			String key = ssKey == null ? Common.getKey() : ssKey;
			// đưa đối tượng lên session
			session.setAttribute(key, formBean);
			// đưa lên request
			modelMap.addAttribute("ssKey", key);
			modelMap.addAttribute("inforSeacrh", formBean);
			modelMap.addAttribute("listTeacher", listTeacher);
			return "formListTeacher";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
}
