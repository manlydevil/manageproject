/**Copyright(C) 2017
 *ListReferenceProjectController.java, Apr 23, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import manageproject.entities.EduProgramBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.ProjectInforSearch;
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
public class ListReferenceProjectController {
	@Autowired
	private ProjectLogic projectLogic;
	@Autowired
	private TermLogic termLogic;
	@Autowired
	private EduProgramLogic eduProgramLogic;

	@RequestMapping(value={"ListReferenceProject","ListReferenceProject/Search","ListReferenceProject/page/{page}"}, method={RequestMethod.GET, RequestMethod.POST})
	public String showListReferProject(ModelMap modelMap, @ModelAttribute("projectInfor") ProjectInforSearch inforSearch, @PathVariable("page") Optional<String> page) {
		try {
			int currentPage = 1;
			if(page.isPresent()) {
				currentPage = Integer.parseInt(page.get());
			}
			int limit = Common.getLimit();
			int offset = Common.getOffset(currentPage, limit);
			int totalRecords = projectLogic.getTotalRecordReferPro(inforSearch);
			int totalPages = Common.getTotalPage(totalRecords, limit);
			List<Integer> listPaging = Common.getListPaging(totalRecords, limit, currentPage);
			List<ProjectInforBean> listProject = projectLogic.getReferProject(inforSearch, limit, offset);
			modelMap.addAttribute("listPaging", listPaging);
			modelMap.addAttribute("totalPages", totalPages);
			modelMap.addAttribute("currentPage", currentPage);
			modelMap.addAttribute("listProject", listProject);
			return "formReferenceProject";
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
	
	@GetMapping(value="/ProjectDetails")
	public String showDetailsInfor(ModelMap modelMap, HttpServletRequest request) {
		try {
			int projectID = Integer.parseInt(request.getParameter("projectID"));
			ProjectInforBean inforBean = projectLogic.getProjectInforByID(projectID);
			modelMap.addAttribute("projectInforBean", inforBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
		return "formDetailsProject";
	}
}
