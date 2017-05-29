/**Copyright(C) 2017
 *SubmitProjectBean.java, Mar 23, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author DELL
 *
 */
@Entity
@Table(name="tbl_nopdoan")
public class SubmitProjectBean {
	private int submitID;
	private int projectID;
	private int studentID;
	private String submitDate;
	private int studentPublic;
	private int teacherPublic;
	private StudentBean student;
	private ProjectBean project;
	
	
	/**
	 * @param submitID
	 * @param projectID
	 * @param submitDate
	 */
	public SubmitProjectBean(int submitID, int projectID, String submitDate) {
		super();
		this.submitID = submitID;
		this.projectID = projectID;
		this.submitDate = submitDate;
	}
	/**
	 * 
	 */
	public SubmitProjectBean() {
		super();
	}
	/**
	 * @param submitID
	 */
	public SubmitProjectBean(int submitID) {
		super();
		this.submitID = submitID;
	}
	/**
	 * @return the submitID
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getSubmitID() {
		return submitID;
	}
	/**
	 * @param submitID the submitID to set
	 */
	public void setSubmitID(int submitID) {
		this.submitID = submitID;
	}
	/**
	 * @return the projectID
	 */
	@Column(name="madoan")
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
	 * @return the studentNumber
	 */
	@Column(name="id_sv")
	public int getStudentID() {
		return studentID;
	}
	/**
	 * @param studentNumber the studentNumber to set
	 */
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	/**
	 * @return the project
	 */
	@OneToOne(fetch=FetchType.LAZY, mappedBy="submitProject")
	public ProjectBean getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(ProjectBean project) {
		this.project = project;
	}
	/**
	 * @return the submitDate
	 */
	@Column(name="ngaynop")
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
	 * @return the student
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "submitProject")
	public StudentBean getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(StudentBean student) {
		this.student = student;
	}
	/**
	 * @return the studentPublic
	 */
	@Column(name="sv_chiase")
	public int getStudentPublic() {
		return studentPublic;
	}
	/**
	 * @param studentPublic the studentPublic to set
	 */
	public void setStudentPublic(int studentPublic) {
		this.studentPublic = studentPublic;
	}
	/**
	 * @return the teacherPublic
	 */
	@Column(name="gv_chiase")
	public int getTeacherPublic() {
		return teacherPublic;
	}
	/**
	 * @param teacherPublic the teacherPublic to set
	 */
	public void setTeacherPublic(int teacherPublic) {
		this.teacherPublic = teacherPublic;
	}
	
}
