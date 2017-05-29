/**Copyright(C) 2017
 *TeacherLogicImpl.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic;

import java.util.List;

import org.springframework.stereotype.Repository;

import manageproject.entities.DegreeBean;
import manageproject.entities.TeacherBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.AccountInfor;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.InforSearchTeacherBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.StudentFormBean;
import manageproject.entities.formbean.TeacherFormBean;

/**
 * @author DELL
 *
 */
@Repository
public interface TeacherLogic {

	/**
	 * Phương thức lấy danh sách giảng viên
	 * @return List<TeacherFormBean>
	 */
	public List<TeacherFormBean> getAllTeacher();
	
	/**
	 * Phương thức lấy ra danh sách giảng viên theo điều kiện tìm kiếm
	 * @param inforSearch InforSearchTeacherBean
	 * @param limit số lượng bản ghi trên 1 trang
	 * @param offset vị trí bắt đầu lấy trong DB
	 * @return List<TeacherFormBean>
	 */
	public List<TeacherFormBean> getListTeacher(InforSearchTeacherBean inforSearch, int limit, int offset);
	
	/**
	 * Phương thức lấy ra số lượng bản ghi theo điều kiện tìm kiếm
	 * @param inforSearch InforSearchTeacherBean
	 * @return int
	 */
	public int getTotalRecords(InforSearchTeacherBean inforSearch);
	
	/**
	 * Phương thức xóa một giảng viên
	 * @param teacherID mã giảng viên
	 * @return boolean
	 */
	public boolean deleteTeacher(int teacherID);
	
	/**
	 * Phương thức lấy ra thông tin giảng viên theo mã giảng viên
	 * @param teacherID mã giảng viên
	 * @return TeacherFormBean
	 */
	public TeacherFormBean getTeacherByID(int teacherID);
	
	/**
	 * Phương thức thêm mới hoặc cập nhật thông tin giảng viên
	 * @param teacherFormBean TeacherFormBean
	 * @return boolean
	 */
	public boolean insertOrUpdate(TeacherFormBean teacherFormBean);
	
	/**
	 * Phương thức lấy ra danh sách học vị
	 * @return List<DegreeBean>
	 */
	public List<DegreeBean> getListDegree();
	
	/**
	 * Lấy ra danh sách sinh viên được phân công bởi một giảng viên
	 * @param teacherID
	 * @param studentFormBean
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<ProjectInforBean> getListStudentInstruction(int teacherID, InforSearchFormBean studentFormBean, int limit, int offset);
	
	/**
	 * Lấy ra danh sách sinh viên được phân công bởi một giảng viên
	 * @param teacherID
	 * @param studentFormBean
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<ProjectInforBean> getListStudentReview(int teacherID, InforSearchFormBean studentFormBean);
	/**
	 * Lấy ra tổng số sinh viên được hướng dẫn bởi một giảng viên
	 * @param teacherID
	 * @param InforSearchFormBean
	 * @return
	 */
	public int getTotalStudent(int teacherID, InforSearchFormBean studentFormBean);
	
	/**
	 * Kiểm tra tồn tại account giảng viên
	 * @param accountInfor
	 * @return
	 */
	public boolean checkExistedAccountInfor(AccountInfor accountInfor);
	
	/**
	 * Lấy mã giảng viên
	 * @param userName
	 * @return
	 */
	public int getTeacherIDByUserName(String userName);
	
	/**
	 * Phương thức lấy ra mã giảng viên bằng tên
	 * @param name tên giảng viên
	 * @return int
	 */
	public String getTeacherIDByName(String name);
}
