/**Copyright(C) 2017
 *EduProgramLogic.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import manageproject.entities.EduProgramBean;

/**
 * @author DELL
 *
 */
@Service
public interface EduProgramLogic {
	/**
	 * Phương thức lấy toàn bộ chương trình đào tạo
	 * @return List<EduProgramBean>
	 */
	public List<EduProgramBean> getAllEduProgram();
	
	/**
	 * Phương thức lấy toàn bộ CTDT
	 * @return int
	 */
	public int getTotalRecords();
	
	/**
	 * Phương thức lấy danh sách CTDT
	 * @return List<EduProgramBean>
	 */
	public List<EduProgramBean> getListEduProgram();
	
	/**
	 * Phương thức xóa CTDT
	 * @param eduID mã CTDT
	 * @return boolean
	 */
	public boolean deleteEduPr(int eduID);
	
	/**
	 * Phương thức lấy ra CTDT theo mã CTDT
	 * @param eduID mã CTDT
	 * @return EduProgramBean
	 */
	public EduProgramBean getEduProgramByID(int eduID);
	
	/**
	 * Phương thức thêm mới hoặc cập nhật
	 * @param eduProgramBean EduProgramBean
	 * @return boolean
	 */
	public boolean insertOrUpdateEdu(EduProgramBean eduProgramBean);
	
	/**
	 * Lấy ra mã CTDT
	 * @param eduProgramName
	 * @return
	 */
	public String getEduProgramID(String eduProgramName);
}
