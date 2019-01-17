/**Copyright(C) 2017
 *StudentDao.java, Mar 13, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import manageproject.entities.AssignmentBean;
import manageproject.entities.StudentBean;
import manageproject.entities.SubmitProjectBean;
import manageproject.entities.formbean.BasicInforFormBean;
import manageproject.entities.formbean.StudentInforBean;

/**
 * @author DELL
 *
 */
@Repository
public interface StudentDao {
	/**
	 * Phương thức kiểm tra tồn tại tài khoản trong DB
	 * @param accountInfor thông tin account người dùng nhập vào
	 * @return boolean
	 */
	public boolean checkExistedAccount(String userName, String password);
	
	/**
	 * Phương thức lấy ra thông tin sinh viên từ mã số sinh viên
	 * @param userName mã số sinh viên
	 * @return StudentBean
	 */
	public StudentBean getStuInforByID(String userName);
	
	/**
	 * Phương thức kiểm tra tồn tại email
	 * @param userName mssv
	 * @param email email
	 * @return boolean
	 */
	public boolean checkExistedEmail(String userName, String email);
	
	/**
	 * Phương thức update thông tin sinh viên	
	 * @param studentBean
	 * @return boolean
	 */
	public boolean saveOrUpdateInfor(StudentBean studentBean, AssignmentBean assignmentBean);
	
	/**
	 * Phương thức lấy ra đầy đủ thông tin sinh viên
	 * @param studentID mã sinh viên
	 * @return StudentInforBean
	 */
	public StudentInforBean getFullInfor(int studentID);
	
	/**
	 * Phương thức lấy ra danh sách sinh viên theo điều kiện tìm kiếm
	 * @param studentNumber mã sinh viên
	 * @param name tên sinh viên
	 * @param termID mã học kì
	 * @param eduProgramID mã CTDT
	 * @param offset vị trí bắt đầu lấy trong DB
	 * @param sortBy sắp xếp theo
	 * @param sortType trình tự sắp xếp
	 * @param limit số bản ghi trên 1 trang
	 * @return List<StudentInforBean>
	 */
	public List<StudentInforBean> getListStudent(String studentNumber, String name, int termID, int eduProgramID, int offset, String sortBy, String sortType, int limit);
	
	/**
	 * Phương thức lấy ra só lượng bản ghi theo điều kiện tìm kiếm
	 * @param studentNumber mã sinh viên
	 * @param name tên sinh viên
	 * @param termID mã học kì
	 * @param eduProgramID mã CTDT
	 * @return
	 */
	public int getTotalRecords(String studentNumber, String name, int termID, int eduProgramID);
	
	/**
	 * Phương thức xóa một sinh viên
	 * @param studentID studentID
	 * @return boolean
	 */
	public boolean deleteStudent(int studentID);
	
	
	/**
	 * Phương thức lấy ra password của sinh viên
	 * @param userName mã số sinh viên
	 * @return password
	 */
	public String getPasswordByUserName(String userName);
	
	/**
	 * Phương thức kiểm tra tồn tại mã số sinh viên trong  DB
	 * @param userName mã số sinh viên
	 * @return boolean
	 */
	public boolean checkExistedUserName(int studentID, String userName);
	
	public boolean updateBasicInfor(StudentBean studentBean);
	
	/**
	 * Phương thức lấy ra thông tin sinh viên theo ID
	 * @param userName
	 * @return
	 */
	public StudentBean getStudentInfor(String userName);
	
	/**
	 * Phương thức lấy ra mã phân công theo mã sinh viên
	 * @param studentID id sinh viên
	 * @return int
	 */
	public int getAssignmentID(int studentID);
	
	/**
	 * Phương thức lấy ra mã nộp đồ án theo id sinh viên
	 * @param studentID id sinh viên
	 * @return int
	 */
	public int getSubmitID(int studentID);
	
	/**
	 * Phương thức thay đổi password
	 * @param studentBean StudentBean
	 * @return boolean
	 */
	public boolean changePass(StudentBean studentBean);
	
	/**
	 * Phương thức kiểm tra sinh viên đã được phân công đồ án chưa
	 * @param studentNumber mã sinh viên
	 * @return true nếu đã được phân công và ngược lại
	 */
	public boolean checkAssignedProject(String studentNumber, int projectID);
	
	/**
	 * Lấy thông tin nộp đồ án theo mã sinh viên
	 * @param studentNumber mã sinh viên
	 * @return SubmitProjectBean
	 */
	public SubmitProjectBean getSubmitByStudentNumber(String studentNumber);
	
	/**
	 * Lấy ra thông tin để tạo thư mục trên drive
	 * @param studentNumber StudentNumber
	 * @return StudentInforBean
	 */
	public StudentInforBean getTermAndEduProgram(String studentNumber);
	
	/**
	 * Cập nhật thông tin sinh viên từ file import
	 * @param studentBean
	 * @param assignmentBean
	 * @return
	 */
	public boolean updateInfor(StudentBean studentBean, AssignmentBean assignmentBean);

	/**
	 * Lấy ra id sinh viên theo mã sinh viên
	 * @param studentNumber mã sinh viên
	 * @return int
	 */
	public int getIDByStudentNumber(String studentNumber);

	/**
	 * Lấy ra id sinh viên theo mã sinh viên
	 * @param studentNumber mã sinh viên
	 * @return int
	 */
	public int getIDByStudentNumber111(String studentNumber);
}
