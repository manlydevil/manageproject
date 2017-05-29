/**Copyright(C) 2017
 *StudentLogic.java, Mar 13, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic;

import java.util.List;

import org.springframework.stereotype.Service;
import manageproject.entities.StudentBean;
import manageproject.entities.formbean.AccountInfor;
import manageproject.entities.formbean.BasicInforFormBean;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.StudentFormBean;
import manageproject.entities.formbean.StudentInforBean;

/**
 * @author DELL
 *
 */
@Service
public interface StudentLogic {
	/**
	 * Phương thức kiểm tra tồn tại tài khoản trong DB
	 * @param accountInfor thông tin account người dùng nhập vào
	 * @return boolean
	 */
	public boolean checkExistedAccount(AccountInfor accountInfor);
	
	/**
	 * Phương thức lấy ra thông tin sinh viên từ mã số sinh viên
	 * @param userName mã số sinh viên
	 * @return BasicInforFormBean
	 */
	public BasicInforFormBean getStuInforByID(String userName);
	
	/**
	 * Phương thức kiểm tra tồn tại email
	 * @param formBean StudentFormBean
	 * @return boolean
	 */
	public boolean checkExistedEmail(StudentFormBean formBean);
	
	/**
	 * Phương thức lấy ra đầy đủ thông tin sinh viên
	 * @param studentID mã sinh viên
	 * @return StudentInforBean
	 */
	public StudentInforBean getFullInfor(int studentID);
	
	/**
	 * Phương thức lấy ra danh sách sinh viên theo điều kiện tìm kiếm
	 * @param formBean InforSearchFormBean
	 * @param offset vị trí bắt đầu lấy trong DB
	 * @param limit số lượng bản ghi trên 1 trang
	 * @return List<StudentInforBean>
	 */
	public List<StudentInforBean> getListStudent(InforSearchFormBean formBean, int offset, int limit);
	
	/**
	 * Phương thức lấy ra só lượng bản ghi theo điều kiện tìm kiếm
	 * @param inforSearchFormBean InforSearchFormBean
	 * @return int
	 */
	public int getTotalRecords (InforSearchFormBean inforSearchFormBean);
	
	/**
	 * Phương thức cập nhật thông tin sinh viên
	 * @param formBean StudentFormBean
	 * @return boolean
	 */
	public boolean saveOrUpdateInfor(StudentFormBean formBean) ;
	
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
	
	/**
	 * Phương thức update thông tin cơ bản của sinh viên
	 * @param formBean BasicInforFormBean
	 * @return boolean
	 */
	public boolean updateBasicInfor(BasicInforFormBean formBean, String userName);
	
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
	 * @param password password mới
	 * @param userName username
	 * @return boolean
	 */
	public boolean changePass(String password, String userName);
	
	/**
	 * Lấy ra danh sách sinh viên để export
	 * @param formBean InforSearchFormBean
	 * @return List<StudentInforBean>
	 */
	public List<StudentInforBean> getListDataExport(InforSearchFormBean formBean);
	
	/**
	 * Phương thức kiểm tra sinh viên đã được phân công đồ án chưa
	 * @param studentNumber mã sinh viên
	 * @return true nếu đã được phân công và ngược lại
	 */
	public boolean checkAssignedProject(String studentNumber, int projectID);
	
	/**
	 * Lấy ra thông tin để tạo thư mục trên drive
	 * @param studentNumber StudentNumber
	 * @return StudentInforBean
	 */
	public StudentInforBean getTermAndEduProgram(String studentNumber);
	
	/**
	 * Phương thức lấy ra trạng thái nộp bài cho thuộc tính
	 * @param submitID submitID
	 * @return url
	 */
	public String getSubmit(int submitID);
	
	/**
	 * Phương thức thêm vào DB một danh sách sinh viên
	 * @param listStudent List<StudentFormBean>
	 * @return boolean
	 */
	public boolean insertListStudent(List<StudentFormBean> listStudent);
	
}
