/**Copyright(C) 2017
 *UpdateInforValidate.java, Mar 14, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.validate;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import manageproject.entities.formbean.StudentFormBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.logic.ConfigTimeLogic;
import manageproject.logic.StudentLogic;

/**
 * @author DELL
 *
 */
@Component
@PropertySource(value="classpath:config.properties", encoding = "UTF-8")
public class UpdateInforValidate {
	@Autowired
	private StudentLogic studentLogic;
	@Autowired
	private Environment env;
	@Autowired
	private ConfigTimeLogic configTimeLogic;
	public Errors validate(StudentFormBean formBean, Errors errors) {
		if(!errors.hasFieldErrors("studentNumber")) {
			if(studentLogic.checkExistedUserName(Integer.parseInt(formBean.getStudentID()), formBean.getStudentNumber())) {
				errors.rejectValue("studentNumber", "Existed.StudentNumber");
			}
		}
		if(!errors.hasFieldErrors("email")) {
			if(!Pattern.matches(env.getProperty("emailPattern"), formBean.getEmail())) {
				errors.rejectValue("email", "Invalid.Email");
			} else if(studentLogic.checkExistedEmail(formBean)) {
				errors.rejectValue("email", "Existed.Email");
			}
		}
		
		if(!errors.hasFieldErrors("phone")) {
			if(!Pattern.matches(env.getProperty("telPattern"), formBean.getPhone())) {
				errors.rejectValue("phone", "Invalid.Phone");
			}
		}
		
		if(!(errors.hasFieldErrors("termID") && errors.hasFieldErrors("eduProgramID"))) {
			if(!configTimeLogic.checkExistedConfig(0, Integer.parseInt(formBean.getTermID()), Integer.parseInt(formBean.getEduProgramID()))) {
				errors.rejectValue("termID", "NotExisted.ConfigTime");
			}
		}
		
		if(!errors.hasFieldErrors("instructorTeacherID") && !errors.hasFieldErrors("reviewTeacherID")) {
			if(!formBean.getInstructorTeacherID().equals("0") && formBean.getInstructorTeacherID().equals(formBean.getReviewTeacherID())) {
				errors.rejectValue("instructorTeacherID", "Duplicate.TeacherID");
			}
		}
		
		if(formBean.getInstructorTeacherID().equals("0") && studentLogic.getSubmitID(Integer.parseInt(formBean.getStudentID())) != 0) {
			errors.rejectValue("instructorTeacherID", "Submitted.Project");
		}
		
		if(formBean.getReviewTeacherID().equals("0") && studentLogic.getSubmitID(Integer.parseInt(formBean.getStudentID())) != 0) {
			errors.rejectValue("reviewTeacherID", "Submitted.Project");
		}
		
		return errors;
	}
}
