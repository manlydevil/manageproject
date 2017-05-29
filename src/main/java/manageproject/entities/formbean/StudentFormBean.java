/**Copyright(C) 2017
 *StudentFormBean.java, Mar 14, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities.formbean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import manageproject.entities.AssignmentBean;
import manageproject.entities.ProjectBean;
import manageproject.entities.ConfigTimeBean;

/**
 * @author DELL
 *
 */
public class StudentFormBean {
	private String studentID;
	@NotEmpty
	private String studentNumber;
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	private String phone;
	private String workPlace;
	private String office;
	private String password;
	private String term;
	private String eduProgram;
	private String studentClass;
	private String subject;
	private String instructorTeacher;
	private String reviewTeacher;
	private String classID;
	private String subjectID;
	private String instructorTeacherID;	
	private String reviewTeacherID;
	@Pattern(regexp="^[1-9]\\d*$")
	private String termID;
	@Pattern(regexp="^[1-9]\\d*$")
	private String eduProgramID;
	
	public StudentFormBean(){}
	/**
	 * @param studentID
	 * @param studentNumber
	 * @param name
	 * @param email
	 * @param phone
	 * @param workPlace
	 * @param office
	 * @param password
	 * @param classID
	 * @param subjectID
	 * @param instructorTeacherID
	 * @param reviewTeacherID
	 * @param termID
	 * @param eduProgramID
	 */
	public StudentFormBean(int studentNumber, String name, String email, String phone,
			String workPlace, String office, String password, String classID, String subjectID, String termID, String eduProgramID,
			String instructorTeacherID, String reviewTeacherID) {
		this.studentNumber = String.valueOf(studentNumber);
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.workPlace = workPlace;
		this.office = office;
		this.password = password;
		this.classID = classID;
		this.subjectID = subjectID;
		this.termID = termID;
		this.eduProgramID = eduProgramID;
		this.instructorTeacherID = instructorTeacherID;
		this.reviewTeacherID = reviewTeacherID;
	}
	
	
	/**
	 * @return the studentID
	 */
	public String getStudentID() {
		return studentID;
	}
	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(String studentID) {
		this.studentID = studentID;
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
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return the eduProgram
	 */
	public String getEduProgram() {
		return eduProgram;
	}
	/**
	 * @param eduProgram the eduProgram to set
	 */
	public void setEduProgram(String eduProgram) {
		this.eduProgram = eduProgram;
	}
	
	/**
	 * @return the studentClass
	 */
	public String getStudentClass() {
		return studentClass;
	}
	/**
	 * @param studentClass the studentClass to set
	 */
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
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
	 * @return the classID
	 */
	public String getClassID() {
		return classID;
	}
	/**
	 * @param classID the classID to set
	 */
	public void setClassID(String classID) {
		this.classID = classID;
	}
	/**
	 * @return the subjectID
	 */
	public String getSubjectID() {
		return subjectID;
	}
	/**
	 * @param subjectID the subjectID to set
	 */
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}
	/**
	 * @return the instructorTeacherID
	 */
	public String getInstructorTeacherID() {
		return instructorTeacherID;
	}
	/**
	 * @param instructorTeacherID the instructorTeacherID to set
	 */
	public void setInstructorTeacherID(String instructorTeacherID) {
		this.instructorTeacherID = instructorTeacherID;
	}
	/**
	 * @return the reviewTeacherID
	 */
	public String getReviewTeacherID() {
		return reviewTeacherID;
	}
	/**
	 * @param reviewTeacherID the reviewTeacherID to set
	 */
	public void setReviewTeacherID(String reviewTeacherID) {
		this.reviewTeacherID = reviewTeacherID;
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
	
	
	
}
