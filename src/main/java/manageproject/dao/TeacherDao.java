/**Copyright(C) 2017
 *TeacherDao.java, Mar 22, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import manageproject.entities.DegreeBean;
import manageproject.entities.TeacherBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.StudentFormBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.entities.formbean.TeacherFormBean;

/**
 * @author DELL
 *
 */
@Repository
public interface TeacherDao {
	/**
	 * Phương thức lấy danh sách giảng viên
	 * @return List<TeacherFormBean>
	 */
	public List<TeacherFormBean> getAllTeacher();
	
	public TeacherBean getTeacherByID(int id);
	/**
	 * Phương thức lấy ra danh sách giảng viên theo điều kiện tìm kiếm
	 * @param teacherName tên giảng viên
	 * @param limit số lượng bản ghi trên 1 trang
	 * @param offset vị trí bắt đầu lấy trong DB
	 * @return List<TeacherFormBean>
	 */
	public List<TeacherFormBean> getListTeacher(String teacherName, int limit, int offset);
	
	/**
	 * Phương thức lấy ra số lượng bản ghi phù hợp điều kiện tìm kiếm
	 * @param teacherName tên giảng viên
	 * @return int
	 */
	public int getTotalRecords(String teacherName);
	
	/**
	 * Phương thức xóa một giảng viên
	 * @param teacherID mã giảng viên
	 * @return boolean
	 */
	public boolean deleteTeacher(int teacherID);
	
	/**
	 * Phương thức thêm mới hoặc cập nhật thông tin giảng viên
	 * @param teacherBean TeacherBean
	 * @return boolean
	 */
	public boolean insertOrUpdate(TeacherBean teacherBean);
	
	/**
	 * Phương thức lấy ra danh sách học vị
	 * @return List<DegreeBean>
	 */
	public List<DegreeBean> getListDegree();
	
	/**
	 * Lấy ra danh sách sinh viên được phân công bởi một giảng viên
	 * @param studentNumber
	 * @param studentName
	 * @param termID
	 * @param eduProgramID
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<ProjectInforBean> getListStudentInstruction(int teacherID, String studentNumber, String studentName, int termID, int eduProgramID, int limit, int offset);
	
	/**
	 * Lấy ra danh sách sinh viên được phân công bởi một giảng viên
	 * @param studentNumber
	 * @param studentName
	 * @param termID
	 * @param eduProgramID
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<ProjectInforBean> getListStudentReview(int teacherID, String studentNumber, String studentName, int termID, int eduProgramID);
	/**
	 * Lấy ra tổng số sinh viên được hướng dẫn bởi một giảng viên
	 * @param teacherID
	 * @param studentNumber
	 * @param studentName
	 * @param termID
	 * @param eduProgramID
	 * @return
	 */
	public int getTotalStudent(int teacherID, String studentNumber, String studentName, int termID, int eduProgramID);
	
	/**
	 * Kiểm tra tồn tại account giảng viên
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean checkExistedAccountInfor(String userName, String password);
	
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
	public int getTeacherIDByName(String name);
	
}
