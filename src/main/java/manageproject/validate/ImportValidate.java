/**Copyright(C) 2017
 *ImportValidate.java, May 29, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.validate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.StudentFormBean;
import manageproject.logic.ConfigTimeLogic;
import manageproject.logic.StudentLogic;
import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
@Component
@PropertySources({@PropertySource("classpath:msg_error.properties"), @PropertySource("classpath:config.properties")})
public class ImportValidate {
	@Autowired
	private StudentLogic studentLogic;
	@Autowired
	private Environment env;
	@Autowired
	private ConfigTimeLogic configTimeLogic;
	
	public Map<String, String> validate (StudentFormBean formBean) {
		Map<String, String> mapErrors = new HashMap<String, String>();
		mapErrors.put("number", "MSSV:"+formBean.getStudentNumber());
		
		if(!Pattern.matches(env.getProperty("emailPattern"), formBean.getEmail())) {
			mapErrors.put("email", env.getProperty("Invalid.Email"));
		} else if(studentLogic.checkExistedEmail(formBean)) {
			mapErrors.put("email", env.getProperty("Existed.Email"));
		}
		
		if(!Pattern.matches(env.getProperty("telPattern"), formBean.getPhone())) {
			mapErrors.put("phone", env.getProperty("Invalid.Phone"));
		}
		
		if(!configTimeLogic.checkExistedConfig(0, Integer.parseInt(formBean.getTermID()), Integer.parseInt(formBean.getEduProgramID()))) {
			mapErrors.put("termID", env.getProperty("NotExisted.ConfigTime"));
		}
		
		if(!formBean.getInstructorTeacherID().equals("0") && formBean.getInstructorTeacherID().equals(formBean.getReviewTeacherID())) {
			mapErrors.put("instructorTeacherID", env.getProperty("Duplicate.TeacherID"));
		}
		
		if(formBean.getInstructorTeacherID().equals("0") && studentLogic.getSubmitID(Integer.parseInt(formBean.getStudentID())) != 0) {
			mapErrors.put("instructorTeacherID", env.getProperty("Submitted.Project"));
		}
		
		if(formBean.getReviewTeacherID().equals("0") && studentLogic.getSubmitID(Integer.parseInt(formBean.getStudentID())) != 0) {
			mapErrors.put("reviewTeacherID", env.getProperty("Submitted.Project"));
		}
		return mapErrors;
	}
	
	public Map<String, String> validateProject(ProjectInforBean projectInforBean) {
		Map<String, String> mapErrors = new HashMap<String, String>();
		mapErrors.put("number", "MSSV:"+projectInforBean.getProjectName());
		
		if(!Pattern.matches(env.getProperty("studentNumberPattern"), String.valueOf(projectInforBean.getStudentNumber()))) {
			mapErrors.put("studentNumber", env.getProperty("Invalid.StudentNumber"));
		} else if(!studentLogic.checkExistedUserName(0,projectInforBean.getStudentNumber())) {
			mapErrors.put("studentNumber", env.getProperty("NotExisted.StudentNumber"));
		}
		
		if(Common.checkExistDate(projectInforBean.getSubmitDate()) == 2) {
			mapErrors.put("submitDate", env.getProperty("Invalid.SubmitDate"));
		}
		
		return mapErrors;
	}
}
