/**Copyright(C) 2017
 *ProjectBean.java, Mar 11, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 * @author DELL
 *
 */
@Entity
@Table(name = "tbl_doan", uniqueConstraints = {@UniqueConstraint(columnNames = {"madoan"})})
public class ProjectBean {
	private int projectID;
	private String topicName;
	private String wordKey;
	private String zipFile;
	private String pdfFile;
	private String description;
	private String note;
	private SubmitProjectBean submitProject;
	private Float score;
	private Integer isPass;
	
	
	/**
	 * @param projectID
	 * @param topicName
	 * @param wordKey
	 * @param zipFile
	 * @param pdfFile
	 * @param description
	 * @param note
	 */
	public ProjectBean(int projectID, String topicName, String wordKey, String zipFile, String pdfFile,
			String description, String note, Float score, Integer isPass) {
		super();
		this.projectID = projectID;
		this.topicName = topicName;
		this.wordKey = wordKey;
		this.zipFile = zipFile;
		this.pdfFile = pdfFile;
		this.description = description;
		this.note = note;
		this.score = score;
		this.isPass = isPass;
	}
	public ProjectBean() {}
	/**
	 * @param projectID
	 */
	public ProjectBean(int projectID) {
		this.projectID = projectID;
	}
	/**
	 * @return the projectID
	 */
	@Id
	@Column(name="madoan")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getProjectID() {
		return projectID;
	}
	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	
	/**
	 * @return the topicName
	 */
	@Column(name="tendetai", nullable = false)
	public String getTopicName() {
		return topicName;
	}
	/**
	 * @param topicName the topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	/**
	 * @return the wordKey
	 */
	@Column(name="tukhoa", nullable = false)
	public String getWordKey() {
		return wordKey;
	}
	/**
	 * @param wordKey the wordKey to set
	 */
	public void setWordKey(String wordKey) {
		this.wordKey = wordKey;
	}
	/**
	 * @return the zipFile
	 */
	@Column(name="sourcelink", nullable = false)
	public String getZipFile() {
		return zipFile;
	}
	/**
	 * @param zipFile the zipFile to set
	 */
	public void setZipFile(String zipFile) {
		this.zipFile = zipFile;
	}
	/**
	 * @return the pdfFile
	 */
	@Column(name="descriptionlink", nullable = false)
	public String getPdfFile() {
		return pdfFile;
	}
	/**
	 * @param pdfFile the pdfFile to set
	 */
	public void setPdfFile(String pdfFile) {
		this.pdfFile = pdfFile;
	}
	/**
	 * @return the description
	 */
	@Column(name="mota")
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the note
	 */
	@Column(name="ghichu")
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the submitProject
	 */
	@OneToOne(fetch=FetchType.LAZY)
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
	/**
	 * @return the score
	 */
	@Column(name="diem")
	public Float getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	/**
	 * @return the isPass
	 */
	@Column(name="ispass")
	public Integer getIsPass() {
		return isPass;
	}
	/**
	 * @param isPass the isPass to set
	 */
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	
}
