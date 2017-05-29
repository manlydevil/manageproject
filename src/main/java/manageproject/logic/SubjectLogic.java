/**Copyright(C) 2017
 *SubjectLogic.java, Apr 9, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic;

import java.util.List;

import manageproject.entities.SubjectBean;

/**
 * @author DELL
 *
 */
public interface SubjectLogic {
	/**
	 * Phương thức lấy ra danh sách môn học
	 * @return List<SubjectBean>
	 */
	public List<SubjectBean> getListSubject(int limit, int offset);
	
	/**
	 * Phương thức lấy ra toàn bộ bản ghi
	 * @return tổng số bản ghi
	 */
	public int getTotalRecords();
	
	/**
	 * Phương thức lấy danh sách để hiển thị select box
	 * @return List<SubjectBean>
	 */
	public List<SubjectBean> getSelectBoxSubject();
	
	/**
	 * Phương thức lấy thông tin môn học theo mã môn học
	 * @param subjectID subjectID
	 * @return SubjectBean
	 */
	public SubjectBean getSubjectByID(int subjectID);
	
	/**
	 * Phương thức thêm mới 1 môn học
	 * @param subjectBean SubjectBean
	 * @return boolean
	 */
	public boolean addSubject(SubjectBean subjectBean);
	
	/**
	 * Phương thức cập nhật thông tin môn học
	 * @param subjectBean SubjectBean
	 * @return boolean
	 */
	public boolean updateSubject(SubjectBean subjectBean);
	
	/**
	 * Phương thức xóa một môn học
	 * @param int subjectID
	 * @return boolean
	 */
	public boolean deleteSubject(int subjectID);
	
	/**
	 * Lấy ra id môn học theo mã
	 * @param subjectCode String
	 * @return
	 */
	public String getSubjectIDBySubjectCode(String subjectCode);
}
