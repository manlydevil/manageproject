/**Copyright(C) 2017
 *AssignmentBean.java, Mar 13, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author DELL
 *
 */
@Entity
@Table(name="tbl_phancong", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class AssignmentBean {
	private int assignmentID;
	private int studentID;
	private StudentBean student;
	private TeacherBean instructorTeacher;
	private TeacherBean reviewTeacher;
	
	public AssignmentBean(int assignmentID){
		this.assignmentID = assignmentID;
	}
	/**
	 * @param assignmentID
	 * @param instructorTeacher
	 * @param reviewTeacher
	 */
	public AssignmentBean(int assignmentID, TeacherBean instructorTeacher, TeacherBean reviewTeacher) {
		super();
		this.assignmentID = assignmentID;
		this.instructorTeacher = instructorTeacher;
		this.reviewTeacher = reviewTeacher;
	}
	/**
	 * 
	 */
	public AssignmentBean() {
		super();
	}
	/**
	 * @return the assignmentID
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	 * @return the studentID
	 */
	@Column(name="id_sv")
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
	 * @return the student
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "assignment")
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
	 * @return the instructorTeacher
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="magvhd")
	public TeacherBean getInstructorTeacher() {
		return instructorTeacher;
	}
	/**
	 * @param instructorTeacher the instructorTeacher to set
	 */
	public void setInstructorTeacher(TeacherBean instructorTeacher) {
		this.instructorTeacher = instructorTeacher;
	}
	/**
	 * @return the reviewTeacher
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="magvpb")
	public TeacherBean getReviewTeacher() {
		return reviewTeacher;
	}
	/**
	 * @param reviewTeacher the reviewTeacher to set
	 */
	public void setReviewTeacher(TeacherBean reviewTeacher) {
		this.reviewTeacher = reviewTeacher;
	}	
}
