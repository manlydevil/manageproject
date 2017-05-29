/**Copyright(C) 2017
 *TermLogic.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import manageproject.entities.TermBean;

/**
 * @author DELL
 *
 */
@Service
public interface TermLogic {
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
	 * @return String
	 */
	public String getTermIDByTerm(String term);
}
