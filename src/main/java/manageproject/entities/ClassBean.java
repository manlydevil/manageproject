/**Copyright(C) 2017
 *ClassBean.java, Apr 7, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="tbl_lop")
public class ClassBean {
	@NotNull
	private int classID;
	@NotEmpty
	private String className;
	private List<StudentBean> student;
	
	public ClassBean(int classID, String className) {
		this.classID = classID;
		this.className = className;
	}
	public ClassBean(){}
	/**
	 * @param classID
	 */
	public ClassBean(int classID) {
		super();
		this.classID = classID;
	}
	/**
	 * @return the classID
	 */
	@Id
	@Column(name="id_lop")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getClassID() {
		return classID;
	}
	/**
	 * @param classID the classID to set
	 */
	public void setClassID(int classID) {
		this.classID = classID;
	}
	/**
	 * @return the className
	 */
	@Column(name="tenlop")
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the student
	 */
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "studentClass")
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
