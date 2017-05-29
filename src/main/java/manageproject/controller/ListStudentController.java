	/**Copyright(C) 2017
 *ListStudentController.java, Mar 14, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import manageproject.entities.EduProgramBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.StudentFormBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.logic.EduProgramLogic;
import manageproject.logic.StudentLogic;
import manageproject.logic.TermLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Controller
public class ListStudentController {
	
	@Autowired
	private StudentLogic studentLogic;
	@Autowired
	private TermLogic termLogic;
	@Autowired
	private EduProgramLogic eduProgramLogic;
	@Autowired
	private Environment env;
	
	@RequestMapping(value={"/ListStudent.do", "/ListStudent/{action}/{page}", "/ListStudent/{action}.do"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String showListStudent (ModelMap modelMap, @ModelAttribute("inforSearch") InforSearchFormBean formBean,
					@PathVariable Optional<String> action, @PathVariable Optional<String> page, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			int currentPage = 1;			
			if(action.isPresent()) {
				if(action.get().equals(Constants.ACTION_PAGING)) {
					if(page.isPresent()) {
						formBean.setCurrentPage(page.get());
					}
				}
			}		
			currentPage = Integer.parseInt(formBean.getCurrentPage());
			int limit = Common.getLimit();
			int offset = Common.getOffset(currentPage, limit);
			int totalRecords = studentLogic.getTotalRecords(formBean);
			int totalPages = Common.getTotalPage(totalRecords, limit);
			List<Integer> listPaging = Common.getListPaging(totalRecords, limit, currentPage);
			List<StudentInforBean> lstStudent = studentLogic.getListStudent(formBean, offset, limit);
			String ssKey = request.getParameter("ssKey");
			// tạo mới session hoặc lấy từ request
			String key = ssKey == null ? Common.getKey() : ssKey;
			// đưa đối tượng lên session
			session.setAttribute(key, formBean);
			// đưa lên request
			modelMap.addAttribute("ssKey", key);
			modelMap.addAttribute("inforSeacrh", formBean);
			modelMap.addAttribute("listPaging", listPaging);
			modelMap.addAttribute("totalPages", totalPages);
			modelMap.addAttribute("lstStudent", lstStudent);
			return "formListStudent";
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
	
	@RequestMapping(value="DeleteStudent.do", method=RequestMethod.POST)
	public String deleteStudent(ModelMap modelMap, HttpServletRequest request, RedirectAttributes attributes) {
		try {
			int studentNumber = Integer.parseInt(request.getParameter("studentNumber"));
			boolean rs = studentLogic.deleteStudent(studentNumber);
			if(!rs) {
				attributes.addFlashAttribute("message", env.getProperty("Cannot_del_student"));
			}
			return "redirect:ListStudent.do";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
}
