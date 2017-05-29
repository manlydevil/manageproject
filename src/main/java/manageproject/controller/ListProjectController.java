/**Copyright(C) 2017
 *ListProjectController.java, Apr 1, 2017 [Nguyễn Hưng Thuận]
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manageproject.entities.EduProgramBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.logic.EduProgramLogic;
import manageproject.logic.ProjectLogic;
import manageproject.logic.TermLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Controller
public class ListProjectController {
	
	@Autowired
	private ProjectLogic projectLogic;
	@Autowired
	private TermLogic termLogic;
	@Autowired
	private EduProgramLogic eduProgramLogic;
	
	@RequestMapping(value={"/ListProject.do", "/ListProject/{action}/{page}", "/ListProject/{action}.do"}, method={RequestMethod.GET,RequestMethod.POST})
	public String listProject(ModelMap modelMap, @ModelAttribute("inforSearch") InforSearchFormBean formBean, HttpServletRequest request,@PathVariable Optional<String> action, @PathVariable Optional<String> page) {
		try{
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
			int totalRecords = projectLogic.getTotalRecords(formBean);
			int totalPages = Common.getTotalPage(totalRecords, limit);
			List<Integer> listPaging = Common.getListPaging(totalRecords, limit, currentPage);
			List<ProjectInforBean> listProject = projectLogic.getListProject(formBean, offset, limit);
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
			modelMap.addAttribute("listProject", listProject);
			return "formListProject";
		} catch(Exception ex){
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
	
	@PostMapping(value="DeleteProject.do")
	public String deleteProject(ModelMap modelMap, HttpServletRequest request) {
		try {
			int projectID = Integer.parseInt(request.getParameter("projectID"));
			if (projectLogic.deleteProject(projectID)) {
				return "redirect:/ListProject.do";
			}
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
}
