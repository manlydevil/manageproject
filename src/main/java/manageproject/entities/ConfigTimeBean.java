/**Copyright(C) 2017
 *ConfigTimeBean.java, Mar 11, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author DELL
 *
 */
@Entity
@Table(name="tbl_cauhinhthoigian", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_cauhinh"})})
public class ConfigTimeBean {
	private int configTimeID;
	private TermBean term;
	private EduProgramBean eduProgram;
	private String startDate;
	private String endDate;
	private String reviewDate;
	private String password;
	
	
	/**
	 * 
	 */
	public ConfigTimeBean() {
		super();
	}
	/**
	 * @param configTimeID
	 * @param startDate
	 * @param endDate
	 * @param password
	 */
	public ConfigTimeBean(int configTimeID, String startDate, String endDate, String reviewDate, String password) {
		super();
		this.configTimeID = configTimeID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reviewDate = reviewDate;
		this.password = password;
	}
	/**
	 * @return the termID
	 */
	@Id
	@Column(name="id_cauhinh")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getConfigTimeID() {
		return configTimeID;
	}
	/**
	 * @param termID the termID to set
	 */
	public void setConfigTimeID(int configTimeID) {
		this.configTimeID = configTimeID;
	}
	/**
	 * @return the term
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mahocky")
	public TermBean getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
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
	 * @return the startDate
	 */
	@Column(name="ngaybatdaunopbai", nullable = false)
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	@Column(name="ngayketthucnopbai", nullable = false)
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @return the reviewDate
	 */
	@Column(name="ngaybaove", nullable = false)
	public String getReviewDate() {
		return reviewDate;
	}
	/**
	 * @param reviewDate the reviewDate to set
	 */
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	/**
	 * @return the password
	 */
	@Column(name="matkhau", nullable = false)
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
