/**Copyright(C) 2017
 *TeacherLogicImpl.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import manageproject.dao.TeacherDao;
import manageproject.entities.DegreeBean;
import manageproject.entities.TeacherBean;
import manageproject.entities.formbean.AccountInfor;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.InforSearchTeacherBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.StudentFormBean;
import manageproject.entities.formbean.TeacherFormBean;
import manageproject.logic.TeacherLogic;
import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
@Repository
public class TeacherLogicImpl implements TeacherLogic {
	
	@Autowired
	private TeacherDao teacherDao;
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#getAllTeacher()
	 */
	public List<TeacherFormBean> getAllTeacher() {
		List<TeacherFormBean> list = teacherDao.getAllTeacher();
		for(TeacherFormBean teacher : list) {
			teacher.setTeacherName(teacher.getDegreeName()+"."+teacher.getTeacherName());
		}
		TeacherFormBean bean = new TeacherFormBean(0, "Hãy chọn giảng viên");
		list.add(0,bean);
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#getListTeacher(manageproject.entities.formbean.InforSearchTeacherBean, int, int)
	 */
	public List<TeacherFormBean> getListTeacher(InforSearchTeacherBean inforSearch, int limit, int offset) {
		return teacherDao.getListTeacher(inforSearch.getTeacherName(), limit, offset);
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#getTotalRecords(manageproject.entities.formbean.InforSearchTeacherBean)
	 */
	public int getTotalRecords(InforSearchTeacherBean inforSearch) {
		return teacherDao.getTotalRecords(inforSearch.getTeacherName());
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#deleteTeacher(int)
	 */
	public boolean deleteTeacher(int teacherID) {
		try {
			return teacherDao.deleteTeacher(teacherID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#getTeacherByID(int)
	 */
	public TeacherFormBean getTeacherByID(int teacherID){
		try {
			TeacherBean teacherBean = teacherDao.getTeacherByID(teacherID);
			TeacherFormBean formBean = new TeacherFormBean();
			PropertyUtils.copyProperties(formBean, teacherBean);
			formBean.setDegreeID(teacherBean.getDegree().getDegreeID());
			formBean.setBirthday(Common.formatDate(formBean.getBirthday()));
			return formBean;
		} catch(Exception ex) {
			ex.printStackTrace();
			return new TeacherFormBean();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#insertOrUpdate(manageproject.entities.formbean.TeacherFormBean)
	 */
	public boolean insertOrUpdate(TeacherFormBean teacherFormBean) {
		try {
			TeacherBean teacherBean = new TeacherBean();
			PropertyUtils.copyProperties(teacherBean, teacherFormBean);
			DegreeBean degree = new DegreeBean(teacherFormBean.getDegreeID(), teacherFormBean.getDegreeName());
			teacherBean.setBirthday(Common.convertDateHQL(teacherBean.getBirthday()));
			teacherBean.setDegree(degree);
			return teacherDao.insertOrUpdate(teacherBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#getListDegree()
	 */
	public List<DegreeBean> getListDegree() {
		try {
			List<DegreeBean> list = teacherDao.getListDegree();
			DegreeBean degreeBean = new DegreeBean(0, "Hãy chọn học vị");
			list.add(0, degreeBean);
			return list;
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<DegreeBean>();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#getListStudentAssigned(int, java.lang.String, java.lang.String, int, int, int, int)
	 */
	public List<ProjectInforBean> getListStudentInstruction(int teacherID, InforSearchFormBean student, int limit, int offset) {
		try {
			return teacherDao.getListStudentInstruction(teacherID, student.getStudentNumber(), student.getName(), Integer.parseInt(student.getTermID()), Integer.parseInt(student.getEduProgramID()), limit, offset);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<ProjectInforBean>();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#getTotalStudent(int, java.lang.String, java.lang.String, int, int)
	 */
	public int getTotalStudent(int teacherID, InforSearchFormBean student) {
		try {
			return teacherDao.getTotalStudent(teacherID, student.getStudentNumber(), student.getName(), Integer.parseInt(student.getTermID()), Integer.parseInt(student.getEduProgramID()));
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#checkExistedAccountInfor(java.lang.String, java.lang.String)
	 */
	public boolean checkExistedAccountInfor(AccountInfor accountInfor) {
		try{
			return teacherDao.checkExistedAccountInfor(accountInfor.getUserName(), Common.encryptMD5(accountInfor.getPassword()));
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#getTeacherIDByUserName(java.lang.String)
	 */
	public int getTeacherIDByUserName(String userName) {
		try{
			return teacherDao.getTeacherIDByUserName(userName);
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TeacherLogic#getTeacherIDByName(java.lang.String)
	 */
	public String getTeacherIDByName(String name) {
		try {
			for(TeacherFormBean bean : teacherDao.getAllTeacher()) {
				if(Common.standardizedString(name).equals(Common.standardizedString(bean.getTeacherName()))) {
					return String.valueOf(bean.getTeacherID());
				}
			}
			return "";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	public List<ProjectInforBean> getListStudentReview(int teacherID, InforSearchFormBean student) {
		try {
			return teacherDao.getListStudentReview(teacherID, student.getStudentNumber(), student.getName(), Integer.parseInt(student.getTermID()), Integer.parseInt(student.getEduProgramID()));
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<ProjectInforBean>();
		}
	}
	
}
