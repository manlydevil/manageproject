/**Copyright(C) 2017
 *InforSearchFormBean.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities.formbean;

/**
 * @author DELL
 *
 */
public class InforSearchFormBean {
	private String studentNumber = "";
	private String name = "";
	private String termID = "0";
	private String eduProgramID = "0";
	private String sortBy = "s.studentNumber";
	private String sortType = "ASC";
	private String currentPage = "1";
	
	
	/**
	 * 
	 */
	public InforSearchFormBean() {
		super();
	}
	/**
	 * @param currentPage
	 */
	public InforSearchFormBean(String currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the studentNumber
	 */
	public String getStudentNumber() {
		return studentNumber;
	}
	/**
	 * @param studentNumber the studentNumber to set
	 */
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
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
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}
	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	/**
	 * @return the sortType
	 */
	public String getSortType() {
		return sortType;
	}
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	/**
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}		
}
