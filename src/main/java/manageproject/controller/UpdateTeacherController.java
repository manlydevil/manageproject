/**Copyright(C) 2017
 *UpdateTeacherController.java, May 5, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import manageproject.entities.DegreeBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.TeacherFormBean;
import manageproject.logic.TeacherLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Controller
@PropertySource(value="classpath:msg_error.properties")
public class UpdateTeacherController {
	@Autowired
	private TeacherLogic teacherLogic;
	@Autowired
	private Environment env;
	
	/**
	 * Phương thức xóa một giảng viên
	 * @param modelMap ModelMap
	 * @param request HttpServletRequest
	 * @param attribute RedirectAttributes
	 * @return String
	 */
	@PostMapping(value="/DeleteTeacher")
	public String deleteTeacher(ModelMap modelMap, HttpServletRequest request, RedirectAttributes attribute) {
		try {
			int teacherID = Integer.parseInt(request.getParameter("teacherID"));
			if(teacherLogic.deleteTeacher(teacherID)) {
				return "redirect:/ListTeacher.do";
			}
			attribute.addFlashAttribute("error", env.getProperty("CANNOT_DEL"));
			return "redirect:/ListTeacher.do";
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
	}
	
	/**
	 * Controller được gọi khi vào form thêm mới giảng viên
	 * @param modelMap ModelMap
	 * @param request HttpServletRequest
	 * @param session HttpSession 
	 * @return String
	 */
	@GetMapping(value="/AddTeacher.do")
	public String addTeacher(ModelMap modelMap, HttpServletRequest request, HttpSession session) {
		try {
			String ssKey = request.getParameter("ssKey");
			// tạo mới session hoặc lấy từ request
			String key = ssKey == null ? Common.getKey() : ssKey;
			// đưa đối tượng lên session
			session.setAttribute(key, new TeacherFormBean());
			// đưa lên request
			modelMap.addAttribute("ssKey", key);
			modelMap.addAttribute("teacherInfor", new TeacherFormBean());
			return "formUpdateTeacher";
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
	}
	
	/**
	 * Controller được gọi khi ấn sửa thông tin giảng viên
	 * @param modelMap ModelMap
	 * @param request HttpServletRequest
	 * @param ssKey String
	 * @return String
	 */
	@GetMapping(value="/UpdateTeacher/{ssKey}")
	public String showDetails(ModelMap modelMap, HttpServletRequest request, @PathVariable String ssKey) {
		try {
			int teacherID = Integer.parseInt(request.getParameter("id"));
			TeacherFormBean formBean = teacherLogic.getTeacherByID(teacherID);
			modelMap.addAttribute("teacherInfor", formBean);
			modelMap.addAttribute("ssKey", ssKey);
			return "formUpdateTeacher";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	/**
	 * Controller được gọi khi người dùng xác nhận thay đổi thông tin 
	 * @param modelMap ModelMap
	 * @param formBean TeacherFormBean
	 * @param results BindingResult
	 * @return String
	 */
	@PostMapping(value="/UpdateTeacher.do")
	public String updateTeacher(ModelMap modelMap,@Valid @ModelAttribute("teacherInfor") TeacherFormBean formBean, BindingResult results) {
		try {
			if(!results.hasErrors()) {
				if(teacherLogic.insertOrUpdate(formBean)) {
					results.rejectValue("teacherName", "UpdateCompleted");
				}
			}
			return "formUpdateTeacher";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	/**
	 * Lấy ra danh sách giảng viên
	 * @return List<DegreeBean>
	 */
	@ModelAttribute("listDegree")
	public List<DegreeBean> getListDegree() {
		return teacherLogic.getListDegree();
	}
}
