/**Copyright(C) 2017
 *BasicInforFormBean.java, Mar 30, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities.formbean;

import javax.validation.GroupSequence;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author DELL
 *
 */
public class BasicInforFormBean {
	@NotEmpty
	private String name;
	private String email;
	private String phone;
	private String workPlace;
	private String office;
	
	@NotEmpty(groups=changePass.class)
	private String newPass;
	
	@NotEmpty(groups=changePass.class)
	private String reNewPass;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the workPlace
	 */
	public String getWorkPlace() {
		return workPlace;
	}
	/**
	 * @param workPlace the workPlace to set
	 */
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	/**
	 * @return the office
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * @param office the office to set
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * @return the newPass
	 */
	public String getNewPass() {
		return newPass;
	}
	/**
	 * @param newPass the newPass to set
	 */
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	/**
	 * @return the reNewPass
	 */
	public String getReNewPass() {
		return reNewPass;
	}
	/**
	 * @param reNewPass the reNewPass to set
	 */
	public void setReNewPass(String reNewPass) {
		this.reNewPass = reNewPass;
	}
	
	public interface changePass{};
	
}
