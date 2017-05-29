/**Copyright(C) 2017
 *TeacherAccountBean.java, May 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author DELL
 *
 */
@Entity
@Table(name="tbl_tkgiangvien")
public class TeacherAccountBean {
	private int id;
	private String userName;
	private String password;
	private int teacherID;
	private TeacherBean teacher;
	
	
	/**
	 * @return the id
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	@Column(name="taikhoan")
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return the teacherID
	 */
	@Column(name="id_gv")
	public int getTeacherID() {
		return teacherID;
	}
	/**
	 * @param teacherID the teacherID to set
	 */
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	/**
	 * @return the teacher
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "teacherAccount")
	public TeacherBean getTeacher() {
		return teacher;
	}
	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(TeacherBean teacher) {
		this.teacher = teacher;
	}
	
	
}
