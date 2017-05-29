/**Copyright(C) 2017
 *StudentBean.java, Mar 11, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author DELL
 *
 */
@Entity
@Table(name = "tbl_sinhvien", uniqueConstraints = {@UniqueConstraint(columnNames = {"MSSV"})})
public class StudentBean {
	private int studentID;
	private String studentNumber;
	private String name;
	private String email;
	private String phone;
	private String workPlace;
	private String office;
	private TermBean term;
	private EduProgramBean eduProgram;
	private AssignmentBean assignment;
	private String password;
	private ClassBean studentClass;
	private SubjectBean subject;
	private SubmitProjectBean submitProject;
	
	
	
	/**
	 * @param studentNumber
	 * @param name
	 * @param email
	 * @param phone
	 * @param workPlace
	 * @param office
	 * @param term
	 * @param eduProgram
	 * @param assignment
	 * @param password
	 * @param project
	 */
	public StudentBean(int studentID, String studentNumber, String name, String email, String phone, String workPlace, String office,
			TermBean term, EduProgramBean eduProgram, String password) {
		this.studentID = studentID;
		this.studentNumber = studentNumber;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.workPlace = workPlace;
		this.office = office;
		this.term = term;
		this.eduProgram = eduProgram;
		this.password = password;
	}


	/**
	 * 
	 */
	public StudentBean() {
		super();
	}
	
	
	/**
	 * @param studentNumber
	 * @param name
	 * @param email
	 * @param phone
	 * @param workPlace
	 * @param office
	 * @param term
	 * @param assignment
	 * @param password
	 * @param project
	 */
	public StudentBean(String name, String email, String phone, String workPlace, String office,
			ConfigTimeBean term, AssignmentBean assignment, String password, SubmitProjectBean projectBean) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.workPlace = workPlace;
		this.office = office;
		this.assignment = assignment;
		this.password = password;
	}


	/**
	 * @param studentID
	 * @param name
	 * @param email
	 * @param phone
	 * @param workPlace
	 * @param office
	 */
	public StudentBean(int studentID, String name, String email, String phone, String workPlace, String office) {
		this.studentID = studentID;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.workPlace = workPlace;
		this.office = office;
	}
	
	/**
	 * @return the studentID
	 */
	@Id
	@Column(name="id_sv")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@Column(name="mssv")
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
	@Column(name="hoten")
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
	@Column(name="email")
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
	@Column(name="phone")
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
	@Column(name="noicongtac")
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
	@Column(name="chucvu")
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
	@Column(name="matkhau")
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
	 * @return the assignment
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public AssignmentBean getAssignment() {
		return assignment;
	}
	/**
	 * @param assignment the assignment to set
	 */
	public void setAssignment(AssignmentBean assignment) {
		this.assignment = assignment;
	}
	/**
	 * @return the termTime
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mahk")
	public TermBean getTerm() {
		return term;
	}

	/**
	 * @param termTime the termTime to set
	 */
	public void setTerm(TermBean term) {
		this.term = term;
	}


	/**
	 * @return the eduProgram
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mactdt")
	public EduProgramBean getEduProgram() {
		return eduProgram;
	}


	/**
	 * @param eduProgram the eduProgram to set
	 */
	public void setEduProgram(EduProgramBean eduProgram) {
		this.eduProgram = eduProgram;
	}
	/**
	 * @return the studentClass
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="malop")
	public ClassBean getStudentClass() {
		return studentClass;
	}


	/**
	 * @param studentClass the studentClass to set
	 */
	public void setStudentClass(ClassBean studentClass) {
		this.studentClass = studentClass;
	}


	/**
	 * @return the subject
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mamonhoc")
	public SubjectBean getSubject() {
		return subject;
	}


	/**
	 * @param subject the subject to set
	 */
	public void setSubject(SubjectBean subject) {
		this.subject = subject;
	}


	/**
	 * @return the submitProject
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public SubmitProjectBean getSubmitProject() {
		return submitProject;
	}


	/**
	 * @param submitProject the submitProject to set
	 */
	public void setSubmitProject(SubmitProjectBean submitProject) {
		this.submitProject = submitProject;
	}
	
	
}
