/**Copyright(C) 2017
 *ChangePassValidate.java, Mar 31, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import manageproject.entities.formbean.BasicInforFormBean;

/**
 * @author DELL
 *
 */
@Component
public class ChangePassValidate {
	
	public Errors validate(BasicInforFormBean formBean, Errors errors) {
		if(!errors.hasErrors()) {
			if(!formBean.getNewPass().equals(formBean.getReNewPass())) {
				errors.rejectValue("newPass", "NotMatch.Pass");
			}
		}
		return errors;
	}
}
