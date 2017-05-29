/**Copyright(C) 2017 
 *AccountInfor.java, Mar 11, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities.formbean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author DELL
 *
 */
public class AccountInfor {
	@NotEmpty
	public String userName;
	@NotEmpty
	public String password;
	public String priority;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
}
