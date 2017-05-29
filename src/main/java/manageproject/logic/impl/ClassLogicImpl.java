/**Copyright(C) 2017
 *ClassLogicImpl.java, Apr 9, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.ls.LSInput;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import manageproject.dao.ClassDao;
import manageproject.entities.ClassBean;
import manageproject.logic.ClassLogic;
import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
@Component
public class ClassLogicImpl implements ClassLogic {
	@Autowired
	private ClassDao classDao;
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ClassLogic#getListClass()
	 */
	public List<ClassBean> getSelectBoxClass() {
		try{
			List<ClassBean> listClass = new ArrayList<ClassBean>();
			listClass = classDao.getListClass(0,0);
			ClassBean cl = new ClassBean(0);
			cl.setClassName("Hãy chọn lớp học");
			listClass.add(0, cl);
			return listClass;
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<ClassBean>();
		}
	}
	
	

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ClassLogic#getClassByID(int)
	 */
	public ClassBean getClassByID(int classID) {
		try{
			return classDao.getClassByID(classID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ClassBean();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ClassLogic#addClass(manageproject.entities.ClassBean)
	 */
	public boolean addClass(ClassBean studentClass) {
		try {
			return classDao.addClass(studentClass);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ClassLogic#deleteClass(int)
	 */
	public boolean deleteClass(int classID) throws MySQLIntegrityConstraintViolationException {
		try {
			return classDao.deleteClass(getClassByID(classID));
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ClassLogic#getListClass()
	 */
	public List<ClassBean> getListClass(int limit, int offset) {
		try{
			return classDao.getListClass(limit, offset);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<ClassBean>();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ClassLogic#getTotalRecords()
	 */
	public int getTotalRecords() {
		try{
			return classDao.getTotalRecords();
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ClassLogic#getClassID(java.lang.String)
	 */
	public String getClassID(String className) {
		try{
			for(ClassBean bean : classDao.getListClass(0, 0)) {
				if(Common.standardizedString(className).equals(Common.standardizedString(bean.getClassName()))) {
					return String.valueOf(bean.getClassID());
				}
			}
			return "";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

}
