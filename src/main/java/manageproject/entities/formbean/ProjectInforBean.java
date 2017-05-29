/**Copyright(C) 2017
 *ProjectInforBean.java, Apr 1, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities.formbean;

import org.hibernate.validator.constraints.NotEmpty;

import manageproject.entities.AssignmentBean;
import manageproject.entities.ProjectBean;
import manageproject.entities.SubmitProjectBean;
import manageproject.entities.TeacherBean;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
public class ProjectInforBean {
	private int projectID;
	@NotEmpty
	private String projectName;
	@NotEmpty
	private String studentNumber;
	@NotEmpty
	private String keyWord;
	private String submitDate;
	private String studentName;
	private String instructorTeacher;
	private String reviewTeacher;
	private String eduProgram;
	private String term;
	private String sourceLink;
	private String descriptionLink;
	private String description;
	private String note;
	private String studentPublic;
	private String teacherPublic;
	private String instructorTeacherName;
	private Float score;
	private String isPass;
	
	/**
	 * 
	 */
	public ProjectInforBean() {
		super();
	}
	
	
	/**
	 * @param projectID
	 * @param projectName
	 * @param studentNumber
	 * @param keyWord
	 * @param sourceLink
	 * @param descriptionLink
	 * @param description
	 * @param note
	 */
	public ProjectInforBean(int projectID, String projectName, String studentNumber, Float score, Integer isPass, String keyWord, String sourceLink,
			String descriptionLink, String description, String note, int studentPublic, int teacherPublic) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.studentNumber = studentNumber;
		this.score = score;
		this.isPass = String.valueOf(isPass);
		this.keyWord = keyWord;
		this.sourceLink = sourceLink;
		this.descriptionLink = descriptionLink;
		this.description = description;
		this.note = note;
		this.studentPublic = studentPublic == 1 ? "1" : "0";
		this.teacherPublic = teacherPublic == 1 ? "1" : "0";
	}

	
	/**
	 * @param projectID
	 * @param projectName
	 * @param keyWord
	 * @param studentName
	 * @param sourceLink
	 * @param descriptionLink
	 * @param description
	 * @param note
	 * @param studentPublic
	 */
	public ProjectInforBean(int projectID, String projectName, String studentNumber, String keyWord, String sourceLink,
			String descriptionLink, String description, String note, int studentPublic) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.keyWord = keyWord;
		this.studentNumber = studentNumber;
		this.sourceLink = sourceLink;
		this.descriptionLink = descriptionLink;
		this.description = description;
		this.note = note;
		this.studentPublic = studentPublic == 1 ? "1" : "0";
	}


	/**
	 * @param projectID
	 * @param projectName
	 * @param dateSubmit
	 * @param studentName
	 * @param instructorTeacher
	 * @param reviewTeacher
	 * @param eduProgramName
	 * @param termNumber
	 * @param source
	 * @param description
	 */
	public ProjectInforBean(int projectID, String projectName, String dateSubmit, String studentName,
			AssignmentBean assignmentBean, String eduProgramName, String termNumber, String sourceLink,
			String descriptionLink, int studentPublic, int teacherPublic, Float score, int isPass) {
		this.projectID = projectID;
		this.projectName = projectName;
		this.submitDate = Common.formatDate(dateSubmit);
		this.studentName = studentName;
		this.instructorTeacher = assignmentBean.getInstructorTeacher().getDegree().getDegreeCode()+"."+assignmentBean.getInstructorTeacher().getTeacherName();
		this.reviewTeacher = assignmentBean.getReviewTeacher() == null ? "" : assignmentBean.getReviewTeacher().getDegree().getDegreeCode()+"."+assignmentBean.getReviewTeacher().getTeacherName();
		this.eduProgram = eduProgramName;
		this.term = termNumber;
		this.sourceLink = sourceLink;
		this.descriptionLink = descriptionLink;
		this.studentPublic = studentPublic == 1 ? "1" : "0";
		this.teacherPublic = teacherPublic == 1 ? "1" : "0";
		this.score = score;
		this.isPass = isPass == 1 ? Constants.PASS : Constants.FAIL;
	}
	
	
	/**
	 * @param projectID
	 * @param projectName
	 * @param studentName
	 * @param instructorTeacher
	 * @param reviewTeacher
	 * @param eduProgram
	 * @param sourceLink
	 * @param descriptionLink
	 */
	public ProjectInforBean(int projectID, String projectName, String studentName, String eduProgram, TeacherBean instructorTeacher,
			TeacherBean reviewTeacher, String sourceLink, String descriptionLink, int studentPublic, int teacherPublic) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.studentName = studentName;
		this.instructorTeacher = instructorTeacher.getDegree().getDegreeCode()+"."+instructorTeacher.getTeacherName();
		this.reviewTeacher = reviewTeacher.getDegree().getDegreeCode()+"."+reviewTeacher.getTeacherName();
		this.eduProgram = eduProgram;
		this.sourceLink = sourceLink;
		this.descriptionLink = descriptionLink;
		this.studentPublic = studentPublic == 1 ? "1" : "0";
		this.teacherPublic = teacherPublic == 1 ? "1" : "0";
	}

	/**
	 * @param studentNumber
	 * @param studentName
	 * @param eduProgram
	 * @param term
	 */
	public ProjectInforBean(ProjectBean projectBean, String studentNumber, String studentName, String eduProgram, String term, SubmitProjectBean submitProjectBean) {
		this.projectID = projectBean == null ? 0 : projectBean.getProjectID();
		this.projectName = projectBean == null ? "" : projectBean.getTopicName();
		this.submitDate = submitProjectBean == null ? "" : Common.formatDate(submitProjectBean.getSubmitDate());
		this.teacherPublic = submitProjectBean == null ? "0" : String.valueOf(submitProjectBean.getTeacherPublic());
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.eduProgram = eduProgram;
		this.term = term;
	}
	
	/**
	 * @param studentNumber
	 * @param studentName
	 * @param eduProgram
	 * @param term
	 */
	public ProjectInforBean(ProjectBean projectBean, SubmitProjectBean submitProjectBean, String studentNumber, String studentName, String eduProgram, String term) {
		this.projectID = projectBean == null ? 0 : projectBean.getProjectID();
		this.projectName = projectBean == null ? "" : projectBean.getTopicName();
		this.submitDate = submitProjectBean == null ? "" : Common.formatDate(submitProjectBean.getSubmitDate());
		this.teacherPublic = submitProjectBean == null ? "0" : String.valueOf(submitProjectBean.getTeacherPublic());
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.eduProgram = eduProgram;
		this.term = term;
		this.sourceLink = projectBean == null ? "" : projectBean.getZipFile();
		this.descriptionLink = projectBean == null ? "" : projectBean.getPdfFile();
	}


	/**
	 * @return the projectID
	 */
	public int getProjectID() {
		return projectID;
	}
	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(int projectID) {
		this.projectID = projectID;
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
	 * @return the dateSubmit
	 */
	public String getSubmitDate() {
		return submitDate;
	}
	/**
	 * @param dateSubmit the dateSubmit to set
	 */
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return the instructorTeacher
	 */
	public String getInstructorTeacher() {
		return instructorTeacher;
	}
	/**
	 * @param instructorTeacher the instructorTeacher to set
	 */
	public void setInstructorTeacher(String instructorTeacher) {
		this.instructorTeacher = instructorTeacher;
	}
	/**
	 * @return the reviewTeacher
	 */
	public String getReviewTeacher() {
		return reviewTeacher;
	}
	/**
	 * @param reviewTeacher the reviewTeacher to set
	 */
	public void setReviewTeacher(String reviewTeacher) {
		this.reviewTeacher = reviewTeacher;
	}
	/**
	 * @return the eduProgramName
	 */
	public String getEduProgram() {
		return eduProgram;
	}
	/**
	 * @param eduProgramName the eduProgramName to set
	 */
	public void setEduProgram(String eduProgramName) {
		this.eduProgram = eduProgramName;
	}
	/**
	 * @return the termNumber
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param termNumber the termNumber to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return the source
	 */
	public String getSourceLink() {
		return sourceLink;
	}
	/**
	 * @param source the source to set
	 */
	public void setSourceLink(String source) {
		this.sourceLink = source;
	}
	
	/**
	 * @return the descriptionLink
	 */
	public String getDescriptionLink() {
		return descriptionLink;
	}


	/**
	 * @param descriptionLink the descriptionLink to set
	 */
	public void setDescriptionLink(String descriptionLink) {
		this.descriptionLink = descriptionLink;
	}


	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}


	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	

	/**
	 * @return the studentPublic
	 */
	public String getStudentPublic() {
		return studentPublic;
	}


	/**
	 * @param studentPublic the studentPublic to set
	 */
	public void setStudentPublic(String studentPublic) {
		this.studentPublic = studentPublic;
	}


	/**
	 * @return the teacherPublic
	 */
	public String getTeacherPublic() {
		return teacherPublic;
	}


	/**
	 * @param teacherPublic the teacherPublic to set
	 */
	public void setTeacherPublic(String teacherPublic) {
		this.teacherPublic = teacherPublic;
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
	 * @return the score
	 */
	public Float getScore() {
		return score;
	}


	/**
	 * @param score the score to set
	 */
	public void setScore(Float score) {
		this.score = score;
	}


	/**
	 * @return the isPass
	 */
	public String getIsPass() {
		return isPass;
	}


	/**
	 * @param isPass the isPass to set
	 */
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	
	
}
