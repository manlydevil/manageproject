/**Copyright(C) 2017
 *ProjectLogic.java, Apr 1, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic;

import java.util.List;

import org.springframework.stereotype.Component;

import manageproject.entities.SubmitProjectBean;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.ProjectInforSearch;
import manageproject.utils.CustomException;

/**
 * @author DELL
 *
 */
@Component
public interface ProjectLogic {
	/**
	 * Phương thức lấy ra danh sách đồ án theo điều kiện tìm kiếm
	 * @param formBean InforSearchFormBean
	 * @param offset vị trí bắt đầu lawsy trong DB
	 * @param limit số lượng bản ghi 1 lần lấy
	 * @return List<ProjectInforBean>
	 */
	public List<ProjectInforBean> getListProject(InforSearchFormBean formBean, int offset, int limit);
	
	/**
	 * Phương thức lấy ra tổng số bản ghi theo điều kiện tìm kiếm
	 * @param formBean InforSearchFormBean
	 * @return int
	 */
	public int getTotalRecords (InforSearchFormBean formBean);
	
	/**
	 * Phương thức lấy ra thông tin đồ án theo mã đồ án
	 * @param projectID mã đồ án
	 * @return ProjectInforBean
	 */
	public ProjectInforBean getProjectInforByID(int projectID);
	
	/**
	 * Phương thức thêm mới hoặc cập nhật thông tin đồ án
	 * @param inforBean ProjectInforBean
	 * @return boolean
	 */
	public boolean insertOrUpdate(ProjectInforBean inforBean);
	
	/**
	 * Phương thức lấy ra thông tin nộp đồ án theo mã đồ án
	 * @param projectID mã đồ án
	 * @return SubmitProjectBean
	 */
	public SubmitProjectBean getSubmitProByProID(int projectID);
	
	/**
	 * Phương thức lấy ra danh sách đồ án để thực hiện export
	 * @param formBean InforSearchFormBean
	 * @return List<ProjectInforBean>
	 */
	public List<ProjectInforBean> getListDataExport(InforSearchFormBean formBean);
	
	/**
	 * Phương thức lấy ra danh sách đồ án tham khảo theo điều kiện tìm kiếm
	 * @param inforSearch ProjectInforSearch 
	 * @param limit số lượng bản ghi trên 1 trang
	 * @param offset vị trí bắt đầu lấy trong DB
	 * @return List<ProjectInforBean>
	 */
	public List<ProjectInforBean> getReferProject(ProjectInforSearch inforSearch, int limit, int offset);
	
	/**
	 * Phương thức lấy ra tổng số bản ghi cho danh sách đồ án tham khảo
	 * @param inforSearch ProjectInforSearch
	 * @return int
	 */
	public int getTotalRecordReferPro(ProjectInforSearch inforSearch);
	
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
	 * Phương thức thêm vào DB một list đồ án
	 * @param listProject List<ProjectInforBean>
	 * @return boolean
	 */
	public boolean insertListProject(List<ProjectInforBean> listProject) throws CustomException;
	
	/**
	 * Phương thức thay đổi quyền chia sẻ của giảng viên
	 * @param projectID mã đồ án
	 * @param teacherPublic chia sẻ
	 * @return booleans
	 */
	public boolean changeTeacherPublic(int projectID, int teacherPublic);
}
