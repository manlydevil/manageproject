/**Copyright(C) 2017
 *StudentInforBean.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities.formbean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import manageproject.entities.AssignmentBean;
import manageproject.entities.SubmitProjectBean;
import manageproject.logic.impl.StudentLogicImpl;
import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
@Component
@PropertySource(value={"classpath:config.properties"})
public class StudentInforBean {
	private int studentID;
	private String studentNumber;
	private String name;
	private String email;
	private String phone;
	private String workPlace;
	private String office;
	private String password;
	private String term;
	private String eduProgram;
	private int assignmentID;
	private int submitID;
	private int classID;
	private int subjectID;
	private int instructorTeacherID;
	private int reviewTeacherID;
	private int termID;
	private int eduProgramID;	
	private String submit;
	
	public StudentInforBean(){};
	
	
	
	/**
	 * @param studentNumber
	 * @param name
	 * @param email
	 * @param phone
	 * @param workPlace
	 * @param office
	 * @param instructorTeacherID
	 * @param reviewTeacherID
	 * @param termID
	 * @param eduProgramID
	 */
	public StudentInforBean(int studentID, String studentNumber, String name, String email, String phone, String workPlace,
			String office, int classID, int termID, int subjectID, int eduProgramID, AssignmentBean aBean) {
		this.studentID = studentID;
		this.studentNumber = studentNumber;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.workPlace = workPlace;
		this.office = office;
		this.instructorTeacherID = aBean == null ? 0 : (aBean.getInstructorTeacher() == null ? 0 : aBean.getInstructorTeacher().getTeacherID());
		this.reviewTeacherID = aBean == null ? 0 : (aBean.getReviewTeacher() == null ? 0 : aBean.getReviewTeacher().getTeacherID());
		this.termID = termID;
		this.eduProgramID = eduProgramID;
		this.classID = classID;
		this.subjectID = subjectID;
	}

	/**
	 * @param studentNumber
	 * @param name
	 * @param email
	 * @param phone
	 * @param workPlace
	 * @param office
	 * @param password
	 * @param termID
	 * @param eduProgramID
	 */
	public StudentInforBean(int studentID, String studentNumber, String name, String email, String phone, String workPlace, String office,
			String term, String eduProgram, SubmitProjectBean submitProject) {
		this.studentID = studentID;
		this.studentNumber = studentNumber;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.workPlace = workPlace;
		this.office = office;
		this.term = term;
		this.eduProgram = eduProgram;
		this.submit = new StudentLogicImpl().getSubmit(submitProject != null ? 1 : 0);
	}

	
	
	/**
	 * @param term
	 * @param eduProgram
	 */
	public StudentInforBean(String term, String eduProgram, String name) {
		this.term = term;
		this.eduProgram = eduProgram;
		this.name = name;
	}

	
	/**
	 * @return the studentID
	 */
	public int getStudentID() {
		return studentID;
	}



	/**
	 * @param studentID the studentID to set
	 */
	public void setStudentID(int studentID) {
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
	 * @return the termID
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param termID the termID to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return the eduProgramID
	 */
	public String getEduProgram() {
		return eduProgram;
	}
	/**
	 * @param eduProgramID the eduProgramID to set
	 */
	public void setEduProgram(String eduProgram) {
		this.eduProgram = eduProgram;
	}
	
	/**
	 * @return the assignmentID
	 */
	public int getAssignmentID() {
		return assignmentID;
	}



	/**
	 * @param assignmentID the assignmentID to set
	 */
	public void setAssignmentID(int assignmentID) {
		this.assignmentID = assignmentID;
	}



	/**
	 * @return the projectID
	 */
	public int getSubmitID() {
		return submitID;
	}
	/**
	 * @param projectID the projectID to set
	 */
	public void setSubmitID(int submitID) {
		this.submitID = submitID;
	}
	/**
	 * @return the classID
	 */
	public int getClassID() {
		return classID;
	}
	/**
	 * @param classID the classID to set
	 */
	public void setClassID(int classID) {
		this.classID = classID;
	}
	/**
	 * @return the subjectID
	 */
	public int getSubjectID() {
		return subjectID;
	}
	/**
	 * @param subjectID the subjectID to set
	 */
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	/**
	 * @return the instructorTeacherID
	 */
	public int getInstructorTeacherID() {
		return instructorTeacherID;
	}
	/**
	 * @param instructorTeacherID the instructorTeacherID to set
	 */
	public void setInstructorTeacherID(int instructorTeacherID) {
		this.instructorTeacherID = instructorTeacherID;
	}
	/**
	 * @return the reviewTeacherID
	 */
	public int getReviewTeacherID() {
		return reviewTeacherID;
	}
	/**
	 * @param reviewTeacherID the reviewTeacherID to set
	 */
	public void setReviewTeacherID(int reviewTeacherID) {
		this.reviewTeacherID = reviewTeacherID;
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



	/**
	 * @return the submit
	 */
	public String getSubmit() {
		return submit;
	}



	/**
	 * @param submit the submit to set
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	
}
