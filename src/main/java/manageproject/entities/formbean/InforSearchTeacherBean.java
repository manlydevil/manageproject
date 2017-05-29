/**Copyright(C) 2017
 *InforSearchTeacherBean.java, May 5, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities.formbean;

/**
 * @author DELL
 *
 */
public class InforSearchTeacherBean {
	private String teacherName="";
	private String currentPage="1";

	/**
	 * @return the teacherName
	 */
	public String getTeacherName() {
		return teacherName;
	}

	/**
	 * @param teacherName the teacherName to set
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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
