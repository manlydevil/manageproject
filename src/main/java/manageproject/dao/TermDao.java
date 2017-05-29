/**Copyright(C) 2017
 *TeacherLogicImpl.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import manageproject.entities.TermBean;

/**
 * @author DELL
 *
 */
@Repository
public interface TermDao {

	/**
	 * Phương thức lấy toàn bộ các kì học
	 * @return List<TermBean>
	 */
	public List<TermBean> getAllTerm();
	
	/**
	 * Phương thức lấy tổng số bản ghi
	 * @return int
	 */
	public int getTotalRecords();
	
	/**
	 * Phương thức lấy ra danh sách học kỳ
	 * @return List<TermBean>
	 */
	public List<TermBean> getListTerm();
	
	/**
	 * Phương thức xóa học kì
	 * @param termID termID
	 * @return boolean
	 */
	public boolean deleteTerm(int termID);
	
	/**
	 * Phương thức lấy ra học kì theo mã học kì
	 * @param termID termID
	 * @return TermBean
	 */
	public TermBean getTermByID(int termID);
	
	/**
	 * Phương thức thêm mới hoặc cập nhật học kì
	 * @param term termBean
	 */
	public boolean insertOrUpdateTerm(TermBean term);
	
	/**
	 * Phương thức lấy ra mã học kì theo học kì
	 * @param term học kì
	 * @return int
	 */
	public int getTermIDByTerm(String term);
}
