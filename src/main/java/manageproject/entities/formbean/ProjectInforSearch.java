/**Copyright(C) 2017
 *ProjectInforSearch.java, Apr 23, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities.formbean;

/**
 * @author DELL
 *
 */
public class ProjectInforSearch {
	private String projectName;
	private String keyWord;
	private String instructorTeacherName;
	private int termID;
	private int eduProgramID;
	
	
	public ProjectInforSearch() {
		this.projectName = "";
		this.keyWord = "";
		this.instructorTeacherName = "";
		this.termID = 0;
		this.eduProgramID = 0;
	}
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the keyWord
	 */
	public String getKeyWord() {
		return keyWord;
	}
	/**
	 * @param keyWord the keyWord to set
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	/**
	 * @return the instructorTeacherName
	 */
	public String getInstructorTeacherName() {
		return instructorTeacherName;
	}
	/**
	 * @param instructorTeacherName the instructorTeacherName to set
	 */
	public void setInstructorTeacherName(String instructorTeacherName) {
		this.instructorTeacherName = instructorTeacherName;
	}
	/**
	 * @return the termID
	 */
	public int getTermID() {
		return termID;
	}
	/**
	 * @param termID the termID to set
	 */
	public void setTermID(int termID) {
		this.termID = termID;
	}
	/**
	 * @return the eduProgramID
	 */
	public int getEduProgramID() {
		return eduProgramID;
	}
	/**
	 * @param eduProgramID the eduProgramID to set
	 */
	public void setEduProgramID(int eduProgramID) {
		this.eduProgramID = eduProgramID;
	}
	
	
}
