/**Copyright(C) 2017
 *ClassLogic.java, Apr 9, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic;

import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import manageproject.entities.ClassBean;

/**
 * @author DELL
 *
 */
public interface ClassLogic {
	/**
	 * Phương thức lấy ra danh sách các lớp để hiển thị select box
	 * @return List<ClassBean>
	 */
	public List<ClassBean> getSelectBoxClass();
	
	/**
	 * Phương thức lấy ra danh sách lớp 
	 * @return List<ClassBean>
	 */
	public List<ClassBean> getListClass(int limit, int offset);
	
	/**
	 * Phương thức lấy ra toàn bộ bản ghi
	 * @return tổng số bản ghi
	 */
	public int getTotalRecords();
	
	/**
	 * Phương thức lấy ra thông tin lớp theo mã lớp
	 * @param classID mã lớp
	 * @return ClassBean
	 */
	public ClassBean getClassByID(int classID);
	
	/**
	 * Phương thức thêm mới 1 lớp
	 * @param studentClass Classbean
	 * @return boolean
	 */
	public boolean addClass(ClassBean studentClass);
	
	/**
	 * Phương thức xóa một lớp học
	 * @param int classID
	 * @return boolean
	 */
	public boolean deleteClass(int classID) throws MySQLIntegrityConstraintViolationException;
	
	/**
	 * Phương thức lấy classID
	 * @param className
	 * @return int
	 */
	public String getClassID(String className);
}
