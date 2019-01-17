/**Copyright(C) 2017
 *ConfigTimeDao.java, Mar 24, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao;

import java.util.List;

import manageproject.entities.ConfigTimeBean;
import manageproject.entities.EduProgramBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.ConfigTimeFormBean;

/**
 * @author DELL
 *
 */
public interface ConfigTimeDao {
	
	/**
	 * Phương thức cập nhật hoặc thêm mới cấu hình thời gian
	 * @param eduProgramBean EduProgramBean
	 * @return boolean
	 */
	public boolean insertOrUpdate(ConfigTimeBean configTimeBean);
	
	/**
	 * Phuowng thức lấy số lượng cấu hình thời gian
	 * @return int
	 */
	public int getTotalRecords();
	
	/**
	 * Phương thức lấy ra 1 list tất cả các cấu hình
	 * @param sortBy sắp xếp theo trường
	 * @param sortType sắp xếp theo kiểu
	 * @return List<ConfigTimeFormBean>
	 */
	public List<ConfigTimeFormBean> getListConfigTime(String sortBy, String sortType);
	
	/**
	 * Phương thức xóa cấu hình thời gia n
	 * @param configID configID
	 * @return boolean
	 */
	public boolean deleteConfig(int configID);
	
	/**
	 * Phương thức lấy ra config bằng ID
	 * @param ID ID
	 * @return ConfigTimeFormBean
	 */
	public ConfigTimeFormBean getConfigByID(int ID);
	
	/**
	 * Phương thức lấy password theo cấu hình thời gian
	 * @param termID mã học kì
	 * @param eduProgramID mã chương trình đào tạo
	 * @return password
	 */
	public String getGeneralPassword(int termID, int eduProgramID);
	
	/**
	 * Phương thức lấy ra hạn nộp theo mã sinh viên
	 * @param userName mã sinh viên
	 * @return ConfigTimeFormBean
	 */
	public ConfigTimeFormBean getDatelineByUserName(String userName);
	
	/**
	 * Kiểm tra đã tồn tại cấu hình thời gian với học kì và CTDT này chưa
	 * @param termID mã học kì
	 * @param eduProgramID mã chương trình đào tạo
	 * @return boolean
	 */	
	public boolean checkExistedConfig(int configTimeID, int termID, int eduProgramID);

	/**
	 * Kiểm tra đã tồn tại cấu hình thời gian với học kì và CTDT này chưa
	 * @param termID mã học kì
	 * @param eduProgramID mã chương trình đào tạo
	 * @return boolean
	 */
	public boolean checkExistedConfig111(int configTimeID, int termID, int eduProgramID);
}
