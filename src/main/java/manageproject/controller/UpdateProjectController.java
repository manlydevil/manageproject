/**Copyright(C) 2017
 *UpdateProjectController.java, Apr 2, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import manageproject.entities.formbean.AccountInfor;
import manageproject.entities.formbean.ConfigTimeFormBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.logic.ConfigTimeLogic;
import manageproject.logic.ProjectLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;
import manageproject.validate.FileUploadValidate;
import manageproject.validate.UploadProjectValidate;

/**
 * @author DELL
 *
 */
@Controller
public class UpdateProjectController {
	@Autowired
	private ProjectLogic projectLogic;
	@Autowired
	private ConfigTimeLogic configTimeLogic;
	@Autowired 
	private UploadProjectValidate validator;
	@Autowired
	private FileUploadController fileUploadController;
    
    
	@RequestMapping(value="UploadProject.do", method=RequestMethod.GET)
	public String newProject(ModelMap modelMap, HttpServletRequest request, HttpSession session) {
		String ssKey = request.getParameter("ssKey");
		// tạo mới session hoặc lấy từ request
		String key = ssKey == null ? Common.getKey() : ssKey;
		// đưa đối tượng lên session
		session.setAttribute(key, new ProjectInforBean());
		// đưa lên request
		modelMap.addAttribute("ssKey", key);
		modelMap.addAttribute("projectInfor", new ProjectInforBean());
		return "formADUploadProject";
	}
	
	@RequestMapping(value="UpdateProject/{ssKey}", method=RequestMethod.GET)
	public String showProject(ModelMap modelMap, HttpServletRequest request, @PathVariable Optional<String> ssKey) {
		try{
			String projectID = request.getParameter("id");
			ProjectInforBean inforBean = projectLogic.getProjectInforByID(Integer.parseInt(projectID));
			modelMap.addAttribute("ssKey", ssKey.get());
			modelMap.addAttribute("projectInfor", inforBean);
			return "formADUploadProject";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@RequestMapping(value="AddProject.st", method=RequestMethod.GET)
	public String newProject(ModelMap modelMap, HttpSession session) {
		try {
			String userName = ((AccountInfor) session.getAttribute("accountSess")).getUserName();
			ConfigTimeFormBean formBean = configTimeLogic.getDatelineByUserName(userName);
			boolean rsCompare = Common.compareWithCurDate(formBean.getEndDate());
			ProjectInforBean projectInforBean = projectLogic.getProjectByStudentNumber(userName);
			modelMap.addAttribute("configBean", formBean);
			modelMap.addAttribute("rsCompare", rsCompare);
			modelMap.addAttribute("projectInfor", projectInforBean);
			return "formSTUploadProject";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}
	
	@RequestMapping(value="UploadProject.do", method=RequestMethod.POST)
	public String insertOrUpdatePro(ModelMap modelMap,@Valid @ModelAttribute("projectInfor") ProjectInforBean inforBean, BindingResult result, @RequestParam("file") MultipartFile[] files, HttpServletRequest request, RedirectAttributes attribute) throws IOException {
		String ssKey = request.getParameter("ssKey");
		try {
			validator.validate(inforBean, result, files);
			if (!result.hasErrors()) {
				if(Common.checkUploadFile(files)) {
					fileUploadController.uploadAndSetLink(files, inforBean);
				}
				if (projectLogic.insertOrUpdate(inforBean)) {
					attribute.addFlashAttribute("ssKey", ssKey);
					return "redirect:/ListProject.do?ssKey="+ssKey;
				}
			} 
			modelMap.addAttribute("ssKey", ssKey);
			return "formADUploadProject";			
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:/error.do?error="+Constants.SYSTEM_ERROR+"&ssKey="+ssKey;
		}
	}
	
	@RequestMapping(value="UpdatePro.st", method=RequestMethod.POST)
	public String insertOrUpdate(ModelMap modelMap, @Valid @ModelAttribute("projectInfor") ProjectInforBean inforBean, BindingResult result, @RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			String userName = ((AccountInfor) session.getAttribute("accountSess")).getUserName();
			ConfigTimeFormBean formBean = configTimeLogic.getDatelineByUserName(userName);
			boolean rsCompare = Common.compareWithCurDate(formBean.getEndDate());			
			validator.validate(inforBean, result, files);
			if(!result.hasErrors()) {
				if(Common.checkUploadFile(files)) {
					fileUploadController.uploadAndSetLink(files, inforBean);
				}
				if(projectLogic.insertOrUpdate(inforBean)) {
					result.rejectValue("projectName", "UpdateCompleted");
				}
			}
			modelMap.addAttribute("rsCompare", rsCompare);
			modelMap.addAttribute("configBean", formBean);
			modelMap.addAttribute("projectInfor", inforBean);
			return "formSTUploadProject";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "redirect:error.do?error="+Constants.SYSTEM_ERROR;
		}
	}	
}
