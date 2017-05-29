/**Copyright(C) 2017
 *ConfigTimeFormBean.java, Mar 17, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities.formbean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
public class ConfigTimeFormBean {
	
	private int configTimeID;
	@Pattern(regexp="^[1-9]\\d*$")
	private String termID;
	@Pattern(regexp="^[1-9]\\d*$")
	private String eduProgramID;
	private String password;
	@NotEmpty
	private String startDate;
	@NotEmpty
	private String endDate;
	@NotEmpty
	private String reviewDate;
	private String submitDate;
	
	private String termNumber;
	private String eduProgramName;
	
	
	
	/**
	 * @param startDate
	 * @param endDate
	 */
	public ConfigTimeFormBean(String startDate, String endDate) {
		super();
		this.startDate = Common.formatDate(startDate);
		this.endDate = Common.formatDate(endDate);
	}
	/**
	 * @param configTimeID
	 * @param termID
	 * @param eduProgramID
	 * @param password
	 * @param startDate
	 * @param endDate
	 */
	public ConfigTimeFormBean(int configTimeID, int termID, int eduProgramID, String startDate,
			String endDate, String reviewDate) {
		this.configTimeID = configTimeID;
		this.termID = String.valueOf(termID);
		this.eduProgramID = String.valueOf(eduProgramID);
		this.startDate = Common.formatDate(startDate);
		this.endDate = Common.formatDate(endDate);
		this.reviewDate = Common.formatDate(reviewDate);
	}
	/**
	 * 
	 */
	public ConfigTimeFormBean() {
		super();
	}
	/**
	 * @param configTimeID
	 * @param startDate
	 * @param endDate
	 * @param termNumber
	 * @param eduProgramName
	 */
	public ConfigTimeFormBean(int configTimeID, String termNumber,String eduProgramName, String startDate, String endDate, String reviewDate ) {
		this.configTimeID = configTimeID;
		this.submitDate = Common.formatDate(startDate)+" - "+Common.formatDate(endDate);
		this.termNumber = termNumber;
		this.eduProgramName = eduProgramName;
		this.reviewDate = Common.formatDate(reviewDate);
	}
	/**
	 * @return the configTimeID
	 */
	public int getConfigTimeID() {
		return configTimeID;
	}
	/**
	 * @param configTimeID the configTimeID to set
	 */
	public void setConfigTimeID(int configTimeID) {
		this.configTimeID = configTimeID;
	}
	/**
	 * @return the termID
	 */
	public String getTermID() {
		return termID;
	}
	/**
	 * @param termID the termID to set
	 */
	public void setTermID(String termID) {
		this.termID = termID;
	}
	/**
	 * @return the eduProgramID
	 */
	public String getEduProgramID() {
		return eduProgramID;
	}
	/**
	 * @param eduProgramID the eduProgramID to set
	 */
	public void setEduProgramID(String eduProgramID) {
		this.eduProgramID = eduProgramID;
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
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the submitDate
	 */
	public String getSubmitDate() {
		return submitDate;
	}
	/**
	 * @param submitDate the submitDate to set
	 */
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	/**
	 * @return the termNumber
	 */
	public String getTermNumber() {
		return termNumber;
	}
	/**
	 * @param termNumber the termNumber to set
	 */
	public void setTermNumber(String termNumber) {
		this.termNumber = termNumber;
	}
	/**
	 * @return the eduProgramName
	 */
	public String getEduProgramName() {
		return eduProgramName;
	}
	/**
	 * @param eduProgramName the eduProgramName to set
	 */
	public void setEduProgramName(String eduProgramName) {
		this.eduProgramName = eduProgramName;
	}
	/**
	 * @return the reviewDate
	 */
	public String getReviewDate() {
		return reviewDate;
	}
	/**
	 * @param reviewDate the reviewDate to set
	 */
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	
}
