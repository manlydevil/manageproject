/**Copyright(C) 2017
 *DegreeBean.java, May 5, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author DELL
 *
 */
@Entity
@Table(name="tbl_hocvi", uniqueConstraints={@UniqueConstraint(columnNames = {"id_hocvi"})})
public class DegreeBean {
	private int degreeID;
	private String degreeCode;
	private String degree;
	private List<TeacherBean> listTeacher;
	
	public DegreeBean(){}
	/**
	 * @param degreeID
	 * @param degree
	 */
	public DegreeBean(int degreeID, String degree) {
		super();
		this.degreeID = degreeID;
		this.degree = degree;
	}
	/**
	 * @return the degreeID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_hocvi")
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
	 * @return the degreeCode
	 */
	@Column(name="mahocvi", nullable=false)
	public String getDegreeCode() {
		return degreeCode;
	}
	/**
	 * @param degreeCode the degreeCode to set
	 */
	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}
	/**
	 * @return the degree
	 */
	@Column(name="hocvi", nullable=false)
	public String getDegree() {
		return degree;
	}
	/**
	 * @param degree the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}
	/**
	 * @return the listTeacher
	 */
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "degree")
	public List<TeacherBean> getListTeacher() {
		return listTeacher;
	}
	/**
	 * @param listTeacher the listTeacher to set
	 */
	public void setListTeacher(List<TeacherBean> listTeacher) {
		this.listTeacher = listTeacher;
	}
	
	
}
