/**Copyright(C) 2017
 *EduProgramBean.java, Mar 15, 2017 [Nguyễn Hưng Thuận]
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
@Table(name="tbl_chuongtrinhdaotao")
public class EduProgramBean {
	private int eduProgramID;
	private String eduProgramName;
	private List<StudentBean> listStudent;
	private List<ConfigTimeBean> listConfigTime;
	
	
	/**
	 * @param eduProgramID
	 */
	public EduProgramBean(int eduProgramID) {
		super();
		this.eduProgramID = eduProgramID;
	}
	/**
	 * 
	 */
	public EduProgramBean() {
		super();
	}
	/**
	 * @param eduProgramID
	 * @param eduProgramName
	 */
	public EduProgramBean(int eduProgramID, String eduProgramName) {
		this.eduProgramID = eduProgramID;
		this.eduProgramName = eduProgramName;
	}
	/**
	 * @return the eduProgramID
	 */
	@Id
	@Column(name="mactdt")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	 * @return the eduProgramName
	 */
	@Column(name="tenctdt")
	public String getEduProgramName() {
		return eduProgramName;
	}
	/**
	 * @param eduProgramName the eduProgramName to set
	 */
	public void setEduProgramName(String eduProgramName) {
		this.eduProgramName = eduProgramName;
	}
	/**
	 * @return the studentBean
	 */
	@OneToMany(fetch =  FetchType.LAZY, mappedBy = "eduProgram")
	public List<StudentBean> getListStudent() {
		return listStudent;
	}
	/**
	 * @param studentBean the studentBean to set
	 */
	public void setListStudent(List<StudentBean> studentBean) {
		this.listStudent= studentBean;
	}
	/**
	 * @return the configTime
	 */
	@OneToMany(fetch =  FetchType.LAZY, mappedBy = "eduProgram")
	public List<ConfigTimeBean> getListConfigTime() {
		return listConfigTime;
	}
	/**
	 * @param configTime the configTime to set
	 */
	public void setListConfigTime(List<ConfigTimeBean> configTime) {
		this.listConfigTime = configTime;
	}
	
	
}
