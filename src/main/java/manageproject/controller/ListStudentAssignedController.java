/**Copyright(C) 2017
 *ListStudentAssignedController.java, May 17, 2017 [Nguyễn Hưng Thuận]
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
import org.springframework.web.bind.annotation.ResponseBody;

import manageproject.entities.EduProgramBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.AccountInfor;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.logic.EduProgramLogic;
import manageproject.logic.ProjectLogic;
import manageproject.logic.StudentLogic;
import manageproject.logic.TeacherLogic;
import manageproject.logic.TermLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Controller
public class ListStudentAssignedController {
	
	@Autowired
	private TermLogic termLogic;
	@Autowired
	private EduProgramLogic eduProgramLogic;
	@Autowired
	private TeacherLogic teacherLogic;
	@Autowired
	private ProjectLogic projectLogic;
	
	@RequestMapping(value={"/ListStudentInstruction.tc", "/ListStudentInstruction/{action}.tc"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String showListStudent (ModelMap modelMap, @ModelAttribute("inforSearch") InforSearchFormBean formBean,
					@PathVariable Optional<String> action, @PathVariable Optional<String> page, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			AccountInfor account = (AccountInfor) session.getAttribute("teacherSession");  
			int teacherID = teacherLogic.getTeacherIDByUserName(account.getUserName());
			int currentPage = 1;
			if(action.isPresent()) {
				if(action.get().equals(Constants.ACTION_PAGING)) {
					if(page.isPresent()) {
						formBean.setCurrentPage(page.get());
					}
				} else if(action.get().equals(Constants.ACTION_BACK)) {
					InforSearchFormBean inforSearchFormBean = (InforSearchFormBean) session.getAttribute(request.getParameter("ssKey"));
					formBean = (inforSearchFormBean == null ? new InforSearchFormBean() : inforSearchFormBean);
				}
			}		
			currentPage = Integer.parseInt(formBean.getCurrentPage());
			int limit = Common.getLimit();
			int offset = Common.getOffset(currentPage, limit);
			int totalRecords = teacherLogic.getTotalStudent(teacherID, formBean);
			int totalPages = Common.getTotalPage(totalRecords, limit);
			List<Integer> listPaging = Common.getListPaging(totalRecords, limit, currentPage);
			List<ProjectInforBean> listStudent = teacherLogic.getListStudentInstruction(teacherID, formBean, limit, offset);
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
			modelMap.addAttribute("listStudent", listStudent);
			return "formListStudentInstruction";
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
	
	@RequestMapping(value="ChangePublic.tc", method=RequestMethod.GET )
	@ResponseBody
	public void changeTeacherPublic(ModelMap modelMap, HttpServletRequest request) {
		try {
			int projectID = Integer.parseInt(request.getParameter("pID"));
			int teacherPublic = Integer.parseInt(request.getParameter("p"));
			projectLogic.changeTeacherPublic(projectID, teacherPublic);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@RequestMapping(value={"ListStudentReview.tc", "ListStudentReview/Search.tc"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String getListStuReviewed(ModelMap modelMap, @ModelAttribute("inforSearch") InforSearchFormBean formBean, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			AccountInfor account = (AccountInfor) session.getAttribute("teacherSession");  
			int teacherID = teacherLogic.getTeacherIDByUserName(account.getUserName());
			List<ProjectInforBean> listStudent = teacherLogic.getListStudentReview(teacherID, formBean);
			String ssKey = request.getParameter("ssKey");
			// tạo mới session hoặc lấy từ request
			String key = ssKey == null ? Common.getKey() : ssKey;
			// đưa đối tượng lên session
			session.setAttribute(key, formBean);
			// đưa lên request
			modelMap.addAttribute("ssKey", key);
			modelMap.addAttribute("inforSeacrh", formBean);
			modelMap.addAttribute("listStudent", listStudent);
			return "formListStudentReview";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
}
