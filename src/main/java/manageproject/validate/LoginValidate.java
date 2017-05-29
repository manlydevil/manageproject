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
import manageproject.logic.StudentLogic;
import manageproject.logic.TeacherLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Component
@PropertySource(value={"classpath:account_admin.properties"})
public class LoginValidate {	
	@Autowired
	private Environment env;
	@Autowired
	private StudentLogic studentLogic;
	@Autowired
	private TeacherLogic teacherLogic;
	
	public Errors validate(AccountInfor accountInfor, Errors errors) {
		// nếu giá trị của username và password nhập vào không phải là rỗng thì mới tiếp tục validate
		if (!errors.hasErrors()) {
			String priority = accountInfor.getPriority();
			if(priority.equals(Constants.ADMIN)) {
				if(!checkAdminAccount(accountInfor)) {
					errors.rejectValue("userName", "InvalidAccount");
				}
			} else if(priority.equals(Constants.STUDENT)) {
				if(!studentLogic.checkExistedAccount(accountInfor)) {
					errors.rejectValue("userName", "InvalidAccount");
				}
			} else {
				if(!teacherLogic.checkExistedAccountInfor(accountInfor)) {
					errors.rejectValue("userName", "InvalidAccount");
				}
			}
		}
		return errors;
	}
	
	/**
	 * Phương thức kiểm tra tài khoản admin từ file properties
	 * @param accountInfor account người dùng nhập 
	 * @return boolean
	 */
	private boolean checkAdminAccount(AccountInfor accountInfor) {
		String userName = env.getProperty("userNameAdmin");
		String password = env.getProperty("passwordAdmin");
		if(accountInfor.getUserName().equals(userName) && 
		   Common.encryptMD5(accountInfor.getPassword()).equals(password)) {
			return true;
		}
		return false;
	}
}
