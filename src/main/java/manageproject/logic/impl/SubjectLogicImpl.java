/**Copyright(C) 2017
 *SubjectLogicImpl.java, Apr 9, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import manageproject.dao.SubjectDao;
import manageproject.entities.ClassBean;
import manageproject.entities.SubjectBean;
import manageproject.logic.SubjectLogic;
import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
@Component
public class SubjectLogicImpl implements SubjectLogic {
	@Autowired
	private SubjectDao subjectDao;
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.SubjectLogic#getListSubject()
	 */
	public List<SubjectBean> getSelectBoxSubject() {
		try{
			List<SubjectBean> listSubject = new ArrayList<SubjectBean>();
			listSubject = subjectDao.getListSubject(0,0);
			SubjectBean subject = new SubjectBean(0);
			subject.setSubjectName("Hãy chọn môn học");
			subject.setSubjectCode("");
			listSubject.add(0, subject);
			return listSubject;
		} catch(Exception ex) {
			return new ArrayList<SubjectBean>();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.SubjectLogic#getSubjectByID(int)
	 */
	public SubjectBean getSubjectByID(int subjectID) {
		try{
			return subjectDao.getSubjectByID(subjectID);
		} catch(Exception ex) {
			return new SubjectBean();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.SubjectLogic#addSubject(manageproject.entities.SubjectBean)
	 */
	public boolean addSubject(SubjectBean subjectBean) {
		try{ 
			return subjectDao.addSubject(subjectBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.SubjectLogic#updateSubject(manageproject.entities.SubjectBean)
	 */
	public boolean updateSubject(SubjectBean subjectBean) {
		try{ 
			return subjectDao.updateSubject(subjectBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.SubjectLogic#deleteSubject(int)
	 */
	public boolean deleteSubject(int subjectID) {
		try{ 
			return subjectDao.deleteSubject(getSubjectByID(subjectID));
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.SubjectLogic#getSelectBoxSubject()
	 */
	public List<SubjectBean> getListSubject(int limit, int offset) {
		try{
			return subjectDao.getListSubject(limit, offset);
		} catch(Exception ex) {
			return new ArrayList<SubjectBean>();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.SubjectLogic#getTotalRecords()
	 */
	public int getTotalRecords() {
		try{
			return subjectDao.getTotalRecords();
		} catch(Exception ex) {
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.SubjectLogic#getSubjectIDBySubjectCode(java.lang.String)
	 */
	public String getSubjectIDBySubjectCode(String subjectCode) {
		try {
			for(SubjectBean bean : subjectDao.getListSubject(0, 0)) {
				if(Common.standardizedString(subjectCode).equals(Common.standardizedString(bean.getSubjectCode()))) {
					return String.valueOf(bean.getSubjectID());
				}
			}
			return "";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

}
