/**Copyright(C) 2017
 *TermBean.java, Mar 15, 2017 [Nguyễn Hưng Thuận]
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

/**
 * @author DELL
 *
 */
@Entity
@Table(name="tbl_hocki")
public class TermBean {
	private int termID;
	private String termNumber;
	private List<StudentBean> listStudent;
	private List<ConfigTimeBean> listConfigTime;
	
	
	/**
	 * @param termID
	 */
	public TermBean(int termID) {
		super();
		this.termID = termID;
	}
	/**
	 * 
	 */
	public TermBean() {
		super();
	}
	/**
	 * @param termID
	 * @param termNumber
	 */
	public TermBean(int termID, String termNumber) {
		this.termID = termID;
		this.termNumber = termNumber;
	}
	/**
	 * @return the termID
	 */
	@Id
	@Column(name="mahk")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	 * @return the termNumber
	 */
	@Column(name="hocki")
	public String getTermNumber() {
		return termNumber;
	}
	/**
	 * @param termNumber the termNumber to set
	 */
	public void setTermNumber(String termNumber) {
		this.termNumber = termNumber;
	}
	/**
	 * @return the lstStudent
	 */
	@OneToMany(fetch=FetchType.LAZY, mappedBy="term")
	public List<StudentBean> getListStudent() {
		return listStudent;
	}
	/**
	 * @param lstStudent the lstStudent to set
	 */
	public void setListStudent(List<StudentBean> listStudent) {
		this.listStudent = listStudent;
	}
	/**
	 * @return the lstConfigTime
	 */
	@OneToMany(fetch=FetchType.LAZY, mappedBy="term")
	public List<ConfigTimeBean> getListConfigTime() {
		return listConfigTime;
	}
	/**
	 * @param lstConfigTime the lstConfigTime to set
	 */
	public void setListConfigTime(List<ConfigTimeBean> listConfigTime) {
		this.listConfigTime = listConfigTime;
	}
	
	
}
