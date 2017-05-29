/**Copyright(C) 2017
 *SubjectBean.java, Apr 7, 2017 [Nguyễn Hưng Thuận]
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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author DELL
 *
 */
@Entity
@Table(name="tbl_monhoc")
public class SubjectBean {
	@NotNull
	private int subjectID;
	@NotEmpty
	private String subjectName;
	@NotEmpty
	private String subjectCode;
	private List<StudentBean> student;
	
	public SubjectBean(){}
	
	
	/**
	 * @param subjectID
	 * @param subjectName
	 * @param subjectCode
	 */
	public SubjectBean(int subjectID, String subjectName, String subjectCode) {
		super();
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.subjectCode = subjectCode;
	}


	/**
	 * @param subjectID
	 */
	public SubjectBean(int subjectID) {
		super();
		this.subjectID = subjectID;
	}
	/**
	 * @return the subjectID
	 */
	@Id
	@Column(name="id_monhoc")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getSubjectID() {
		return subjectID;
	}
	/**
	 * @param subjectID the subjectID to set
	 */
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	/**
	 * @return the subjectName
	 */
	@Column(name="tenmonhoc")
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/**
	 * @return the subjectCode
	 */
	@Column(name="mamonhoc")
	public String getSubjectCode() {
		return subjectCode;
	}
	/**
	 * @param subjectCode the subjectCode to set
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	/**
	 * @return the student
	 */
	@OneToMany(fetch =  FetchType.LAZY, mappedBy = "subject")
	public List<StudentBean> getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(List<StudentBean> student) {
		this.student = student;
	}
	
	
}
