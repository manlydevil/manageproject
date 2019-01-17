/**Copyright(C) 2017
 *ProjectDao.java, Apr 1, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import manageproject.entities.ProjectBean;
import manageproject.entities.SubmitProjectBean;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.ProjectInforBean;

/**
 * @author DELL
 *
 */
@Component
public interface ProjectDao {
	
	/**
	 * Phương thức lấy ra danh sách đồ án theo điều kiện tìm kiếm
	 * @param studentNumber mã sinh viên
	 * @param name tên sinh viên
	 * @param termID mã học kì
	 * @param eduProgramID mã CTDT
	 * @param offset vị trí lấy trong DB
	 * @param sortBy sắp xếp theo
	 * @param sortType thứ tự sắp xếp
	 * @param limit số lượng bản ghi trên 1 trang
	 * @return List<ProjectInforBean>
	 */
	public List<ProjectInforBean> getListProject(String studentNumber, String name, int termID, int eduProgramID, int offset, String sortBy, String sortType, int limit);
	
	/**
	 * Phương thức lấy ra tổng số bản ghi theo điều kiện tìm kiếm
	 * @param studentNumber mã sinh viên
	 * @param name tên sinh viên
	 * @param termID mã học kì
	 * @param eduProgramID mã CTDT
	 * @return
	 */
	public int getTotalRecords(String studentNumber, String name, int termID, int eduProgramID);
	
	/**
	 * Phương thức lấy ra thông tin đồ án theo mã đồ án
	 * @param projectID mã đồ án
	 * @return ProjectInforBean
	 */
	public ProjectInforBean getProjectInforByID(int projectID);
	
	/**
	 * Phương thức thêm mới hoặc cập nhật thông tin đồ án
	 * @param projectBean ProjectBean
	 * @param submitProjectBean SubmitProjectBean
	 * @return boolean
	 */
	public boolean insertOrUpdate(ProjectBean projectBean, SubmitProjectBean submitProjectBean);
	
	/**
	 * Phương thức lấy ra thông tin nộp đồ án theo mã đồ án
	 * @param projectID mã đồ án
	 * @return SubmitProjectBean
	 */
	public SubmitProjectBean getSubmitProByProID(int projectID);
	
	/**
	 * Phương thức lấy ra danh sách đồ án tham khảo theo điều kiện tìm kiếm
	 * @param projectName tên đồ án
	 * @param keyword từ khóa 
	 * @param termID mã học kì
	 * @param eduProgramID mã CTDT
	 * @param instructorTeacher tên giảng viên hướng dẫn
	 * @param limit giới hạn bản ghi trên 1 trang
	 * @param offset vị trí bắt đầu lấy trong DB
	 * @return List<ProjectInforBean>
	 */
	public List<ProjectInforBean> getListReferProject(String projectName, String keyword, int termID, int eduProgramID, String instructorTeacher, int limit, int offset);

	/**
	 * Phương thức lấy ra tổng số bản ghi cho danh sách đồ án tham khảo
	 * @param projectName tên đồ án
	 * @param keyword từ khóa
	 * @param termID mã học kì
	 * @param eduProgramID mã CTDT
	 * @param instructorTeacher tên giảng viên hướng dẫn
	 * @return int
	 */
	public int getTotalRecordReferPro(String projectName, String keyword, int termID, int eduProgramID, String instructorTeacher);
	
	/**
	 * Phương thức lấy ra thông tin đồ án theo mã sinh viên
	 * @param studentNumber mã sinh viên
	 * @return ProjectInforBean
	 */
	public ProjectInforBean getProjectByStudentNumber(String studentNumber);
	
	/**
	 * Phương thức xóa đồ án
	 * @param projectID mã đồ án
	 * @return boolean
	 */
	public boolean deleteProject(int projectID);
	
	/**
	 * Phương thức thay đổi quyền chia sẻ của giảng viên
	 * @param projectID mã đồ án
	 * @param teacherPublic chia sẻ
	 * @return booleans
	 */
	public boolean changeTeacherPublic(int projectID, int teacherPublic);

	/**
	 * Phương thức thay đổi quyền chia sẻ của giảng viên
	 * @param projectID mã đồ án
	 * @param teacherPublic chia sẻ
	 * @return booleans
	 */
	public boolean changeTeacherPublic111(int projectID, int teacherPublic);
}
