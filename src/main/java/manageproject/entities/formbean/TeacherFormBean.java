/**Copyright(C) 2017
 *TeacherFormBean.java, May 5, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities.formbean;

import org.hibernate.validator.constraints.NotEmpty;

import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
public class TeacherFormBean {
	private int teacherID;
	@NotEmpty
	private String teacherName;
	private String degreeName;
	private int degreeID;
	private String address;
	private String birthday;
	
	public TeacherFormBean(){}
	/**
	 * @param teacherID
	 * @param teacherName
	 * @param degree
	 * @param address
	 * @param birthday
	 */
	public TeacherFormBean(int teacherID, String teacherName, String degree, String birthday, String address) {
		super();
		this.teacherID = teacherID;
		this.teacherName = teacherName;
		this.degreeName = degree;
		this.address = address;
		this.birthday = Common.formatDate(birthday);
	}
	
	
	/**
	 * @param teacherID
	 * @param teacherName
	 * @param degree
	 */
	public TeacherFormBean(int teacherID, String teacherName, String degree) {
		super();
		this.teacherID = teacherID;
		this.teacherName = teacherName;
		this.degreeName = degree;
	}
	
	
	/**
	 * @param teacherID
	 * @param teacherName
	 */
	public TeacherFormBean(int teacherID, String teacherName) {
		super();
		this.teacherID = teacherID;
		this.teacherName = teacherName;
	}
	/**
	 * @return the teacherID
	 */
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
	 * @return the degree
	 */
	public String getDegreeName() {
		return degreeName;
	}
	/**
	 * @param degree the degree to set
	 */
	public void setDegreeName(String degree) {
		this.degreeName = degree;
	}
	
	/**
	 * @return the degreeID
	 */
	public int getDegreeID() {
		return degreeID;
	}
	/**
	 * @param degreeID the degreeID to set
	 */
	public void setDegreeID(int degreeID) {
		this.degreeID = degreeID;
	}
	/**
	 * @return the address
	 */
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
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
}
