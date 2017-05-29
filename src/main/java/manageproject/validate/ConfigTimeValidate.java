/**Copyright(C) 2017  [Cong ty CP phan mem Luvina]
 *LoginValidate.java, Mar 11, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import manageproject.entities.formbean.AccountInfor;
import manageproject.entities.formbean.ConfigTimeFormBean;
import manageproject.logic.ConfigTimeLogic;
import manageproject.logic.StudentLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Component
public class ConfigTimeValidate {	
	@Autowired
	private ConfigTimeLogic configTimeLogic;
	
	public Errors validate(ConfigTimeFormBean configTimeFormBean, Errors errors) {
		// không tồn tại
		String startDate = configTimeFormBean.getStartDate();
		String endDate = configTimeFormBean.getEndDate();
		if(!errors.hasFieldErrors("termID") && !errors.hasFieldErrors("eduProgramID")) {
			if(configTimeLogic.checkExistedConfig(configTimeFormBean.getConfigTimeID(), Integer.parseInt(configTimeFormBean.getTermID()), Integer.parseInt(configTimeFormBean.getEduProgramID()))) {
				errors.rejectValue("termID", "Existed.ConfigTime");
			}
		}
		
		if(configTimeFormBean.getConfigTimeID() == 0 && configTimeFormBean.getPassword().length() == 0) {
			errors.rejectValue("password", "NotEmpty.configTime.password");
		}
		
		if(!errors.hasFieldErrors("startDate")) {
			if(Common.checkExistDate(startDate) == 0) {
				errors.rejectValue("startDate", "NotExist.startDate");
				// không đúng định dạng
			} else if (Common.checkExistDate(startDate) == 2) {
				errors.rejectValue("startDate", "Invalid.startDate");
			}
		}
		
		if(!errors.hasFieldErrors("endDate")) {
			if(Common.checkExistDate(endDate) == 0) {
				errors.rejectValue("endDate", "NotExist.endDate");
				// không đúng định dạng
			} else if (Common.checkExistDate(endDate) == 2) {
				errors.rejectValue("endDate", "Invalid.endDate");
			}
		}
		
		if(!errors.hasFieldErrors("startDate") && !errors.hasFieldErrors("endDate")) {
			if(!Common.compareDate(startDate, endDate)) {
				errors.reject("startDate", "Invalid.Date");
			}
		}
		
		if(!errors.hasFieldErrors("endDate") && !errors.hasFieldErrors("reviewDate")) {
			if(!Common.compareDate(startDate, endDate)) {
				errors.reject("reviewDate", "Invalid.Date");
			}
		}
		return errors;
	}
}
