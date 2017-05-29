/**Copyright(C) 2017
 *TeacherBean.java, Mar 11, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author DELL
 *
 */
@Entity
@Table(name="tbl_giangvien")
public class TeacherBean {
	private int teacherID;
	private String teacherName;
	private DegreeBean degree;
	private String birthday;
	private String address;
	private TeacherAccountBean teacherAccount;
	private List<AssignmentBean> instructorTeacher;
	private List<AssignmentBean> reviewTeacher;
	
	
	/**
	 * @param teacherNumber
	 */
	public TeacherBean(int teacherNumber) {
		super();
		this.teacherID = teacherNumber;
	}
	/**
	 * 
	 */
	public TeacherBean() {
		super();
	}

	/**
	 * @param teacherNumber
	 * @param teacherName
	 */
	public TeacherBean(int teacherNumber, String teacherName) {
		this.teacherID = teacherNumber;
		this.teacherName = teacherName;
	}
	/**
	 * @return the teacherNumber
	 */
	@Id
	@Column(name = "magv")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getTeacherID() {
		return teacherID;
	}
	/**
	 * @param teacherNumber the teacherNumber to set
	 */
	public void setTeacherID(int teacherNumber) {
		this.teacherID = teacherNumber;
	}
	/**
	 * @return the teacherName
	 */
	@Column(name = "tengv")
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
	 * @return the degree
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_hocvi")
	public DegreeBean getDegree() {
		return degree;
	}
	/**
	 * @param degree the degree to set
	 */
	public void setDegree(DegreeBean degree) {
		this.degree = degree;
	}
	/**
	 * @return the birthday
	 */
	@Column(name = "ngaysinh")
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the address
	 */
	@Column(name = "diachi")
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the instructorTeacher
	 */
	@OneToMany(fetch=FetchType.LAZY, mappedBy="instructorTeacher")
	public List<AssignmentBean> getInstructorTeacher() {
		return instructorTeacher;
	}
	/**
	 * @param instructorTeacher the instructorTeacher to set
	 */
	public void setInstructorTeacher(List<AssignmentBean> instructorTeacher) {
		this.instructorTeacher = instructorTeacher;
	}
	/**
	 * @return the reviewTeacher
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewTeacher", cascade=CascadeType.ALL)
	public List<AssignmentBean> getReviewTeacher() {
		return reviewTeacher;
	}
	/**
	 * @param reviewTeacher the reviewTeacher to set
	 */
	public void setReviewTeacher(List<AssignmentBean> reviewTeacher) {
		this.reviewTeacher = reviewTeacher;
	}
	
	/**
	 * @return the teacherAccount
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public TeacherAccountBean getTeacherAccount() {
		return teacherAccount;
	}
	/**
	 * @param teacherAccount the teacherAccount to set
	 */
	public void setTeacherAccount(TeacherAccountBean teacherAccount) {
		this.teacherAccount = teacherAccount;
	}
	
		
}
