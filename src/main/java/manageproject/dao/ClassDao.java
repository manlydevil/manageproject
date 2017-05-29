/**Copyright(C) 2017
 *ClassDao.java, Apr 9, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao;

import java.util.List;

import manageproject.entities.ClassBean;

/**
 * @author DELL
 *
 */
public interface ClassDao {
	/**
	 * Phương thức lấy ra danh sách các lớp
	 * @return List<ClassBean>
	 */
	public List<ClassBean> getListClass(int limit, int offset);
	
	/**
	 * Phương thức lấy ra số lượng bản ghi
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
	 * @param classBean ClassBean
	 * @return boolean
	 */
	public boolean deleteClass(ClassBean classBean);
	
	/**
	 * Phương thức lấy classID
	 * @param className
	 * @return int
	 */
	public int getClassID(String className);
}
