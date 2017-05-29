/**Copyright(C) 2017
 *StudentLogicImpl.java, Mar 13, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.tool.hbm2ddl.ForeignKeyMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manageproject.dao.ConfigTimeDao;
import manageproject.dao.StudentDao;
import manageproject.dao.TeacherDao;
import manageproject.entities.AssignmentBean;
import manageproject.entities.ClassBean;
import manageproject.entities.StudentBean;
import manageproject.entities.SubjectBean;
import manageproject.entities.SubmitProjectBean;
import manageproject.entities.TeacherBean;
import manageproject.entities.TermBean;
import manageproject.entities.ConfigTimeBean;
import manageproject.entities.EduProgramBean;
import manageproject.entities.ProjectBean;
import manageproject.entities.formbean.AccountInfor;
import manageproject.entities.formbean.BasicInforFormBean;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.StudentFormBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.logic.StudentLogic;
import manageproject.utils.Common;
import manageproject.utils.Constants;

/**
 * @author DELL
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class StudentLogicImpl implements StudentLogic {

	@Autowired
	private StudentDao studentDao;
	@Autowired 
	private ConfigTimeDao configTimeDao;
	@Autowired
	private TeacherDao teacherDao;
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#checkExistedAccount(manageproject.entities.AccountInfor)
	 */
	public boolean checkExistedAccount(AccountInfor accountInfor) {
		return studentDao.checkExistedAccount(accountInfor.getUserName(), Common.encryptMD5(accountInfor.getPassword()));
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#getStuInforByID(java.lang.String)
	 */
	public BasicInforFormBean getStuInforByID(String userName) {
		return Common.copProSBToBIFB(studentDao.getStuInforByID(userName));
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#checkExistedEmail(manageproject.entities.formbean.StudentFormBean)
	 */
	public boolean checkExistedEmail(StudentFormBean formBean) {
		return studentDao.checkExistedEmail(formBean.getStudentNumber(), formBean.getEmail());
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#updateInfor(manageproject.entities.formbean.StudentFormBean)
	 */
	public boolean saveOrUpdateInfor(StudentFormBean formBean) {
		try {
		String password = "";
		AssignmentBean assignmentBean = null;
		if(checkExistedUserName(0, formBean.getStudentNumber())) {
			password = getPasswordByUserName(formBean.getStudentNumber());
		} else {
			password = configTimeDao.getGeneralPassword(Integer.parseInt(formBean.getTermID()), Integer.parseInt(formBean.getEduProgramID()));
		}
		TermBean termBean = new TermBean(Integer.parseInt(formBean.getTermID()));
		EduProgramBean eduProgramBean = new EduProgramBean(Integer.parseInt(formBean.getEduProgramID()));
		int instructionTeacherID = formBean.getInstructorTeacherID() == null ? 0 : Integer.parseInt(formBean.getInstructorTeacherID());
		int reviewTeacherID = formBean.getReviewTeacherID() == null ? 0 : Integer.parseInt(formBean.getReviewTeacherID());
		int assignmentID = getAssignmentID(Integer.parseInt(formBean.getStudentID()));
		if(instructionTeacherID != 0 || reviewTeacherID != 0) {
			assignmentBean = new AssignmentBean(assignmentID, teacherDao.getTeacherByID(Integer.parseInt(formBean.getInstructorTeacherID())), teacherDao.getTeacherByID(Integer.parseInt(formBean.getReviewTeacherID())));
		} else if (assignmentID != 0) {
			assignmentBean = new AssignmentBean(assignmentID);
		}
		ClassBean classBean = new ClassBean(Integer.parseInt(formBean.getClassID()));
		SubjectBean subjectBean = new SubjectBean(Integer.parseInt(formBean.getSubjectID()));
		StudentBean studentBean = new StudentBean(Integer.parseInt(formBean.getStudentID()), formBean.getStudentNumber(), formBean.getName(), formBean.getEmail(), formBean.getPhone(), formBean.getWorkPlace(), formBean.getOffice(), termBean, eduProgramBean,password);
		studentBean.setStudentClass(classBean);
		studentBean.setSubject(subjectBean);
		if(assignmentBean != null) {
			studentBean.setAssignment(assignmentBean);
			assignmentBean.setStudent(studentBean);
		}
		return studentDao.saveOrUpdateInfor(studentBean, assignmentBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public StudentInforBean getFullInfor(int studentID) {
		return studentDao.getFullInfor(studentID);
	}

	public List<StudentInforBean> getListStudent(InforSearchFormBean formBean,int offset, int limit) {
		return studentDao.getListStudent(formBean.getStudentNumber(), formBean.getName(), Integer.parseInt(formBean.getTermID()), Integer.parseInt(formBean.getEduProgramID()), offset, formBean.getSortBy(), formBean.getSortType(), limit);
	}
	
	public int getTotalRecords(InforSearchFormBean formBean) {
		return studentDao.getTotalRecords(formBean.getStudentNumber(), formBean.getName(), Integer.parseInt(formBean.getTermID()), Integer.parseInt(formBean.getEduProgramID()));
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#deleteStudent(manageproject.entities.StudentBean)
	 */
	public boolean deleteStudent(int studentID) {
		try{
			return studentDao.deleteStudent(studentID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}


	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#getPasswordByUserName(java.lang.String)
	 */
	public String getPasswordByUserName(String userName) {
		try{
			return studentDao.getPasswordByUserName(userName);
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#checkExistedUserName(java.lang.String)
	 */
	public boolean checkExistedUserName(int studentID, String userName) {
		try{
			return studentDao.checkExistedUserName(studentID, userName);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#updateBasicInfor(manageproject.entities.formbean.BasicInforFormBean)
	 */
	public boolean updateBasicInfor(BasicInforFormBean formBean, String userName) {
		try{
			StudentBean studentBean = getStudentInfor(userName);
			studentBean.setName(formBean.getName());
			studentBean.setEmail(formBean.getEmail());
			studentBean.setPhone(formBean.getPhone());
			studentBean.setWorkPlace(formBean.getWorkPlace());
			studentBean.setOffice(formBean.getOffice());
			return studentDao.updateBasicInfor(studentBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#getStudentInfor(int)
	 */
	public StudentBean getStudentInfor(String userName) {
		try {
			return studentDao.getStudentInfor(userName);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new StudentBean();
		}
	}
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#getAssignmentID(int)
	 */
	public int getAssignmentID(int studentID) {
		try {
			return studentDao.getAssignmentID(studentID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#getSubmitID(int)
	 */
	public int getSubmitID(int studentID) {
		try {
			return studentDao.getSubmitID(studentID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#changePass(java.lang.String, java.lang.String)
	 */
	public boolean changePass(String password, String userName) {
		try {
			StudentBean studentBean = getStudentInfor(userName);
			studentBean.setPassword(Common.encryptMD5(password));
			return studentDao.changePass(studentBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#getListDataExport(manageproject.entities.formbean.InforSearchFormBean)
	 */
	public List<StudentInforBean> getListDataExport(InforSearchFormBean formBean) {
		try {
			return studentDao.getListStudent(formBean.getStudentNumber(), formBean.getName(), Integer.parseInt(formBean.getTermID()), Integer.parseInt(formBean.getEduProgramID()), 0, formBean.getSortBy(), formBean.getSortType(), 0);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<StudentInforBean>();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#checkAssignedProject(java.lang.String)
	 */
	public boolean checkAssignedProject(String studentNumber, int projectID) {
		try {
			return studentDao.checkAssignedProject(studentNumber, projectID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#getTermAndEduProgram(java.lang.String)
	 */
	public StudentInforBean getTermAndEduProgram(String studentNumber) {
		try {
			return studentDao.getTermAndEduProgram(studentNumber);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new StudentInforBean();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#getIconUrl(int)
	 */
	public String getSubmit(int submitID) {
		if(submitID == 1) {
			return Constants.SUBMITTED;
		}
		return Constants.NOT_SUBMITTED;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.StudentLogic#insertListStudent(java.util.List)
	 */
	public boolean insertListStudent(List<StudentFormBean> listStudent) {
		try {
			for(StudentFormBean studentInforBean : listStudent) {
				studentInforBean.setStudentID("0");
				if(checkExistedUserName(Integer.parseInt(studentInforBean.getStudentID()), studentInforBean.getStudentNumber())) {
					AssignmentBean assignmentBean = null;
					StudentBean studentBean = getStudentInfor(studentInforBean.getStudentNumber());
					studentBean.setName(studentInforBean.getName());
					studentBean.setEmail(studentInforBean.getEmail());
					studentBean.setPhone(studentInforBean.getPhone());
					studentBean.setWorkPlace(studentInforBean.getWorkPlace());
					studentBean.setOffice(studentInforBean.getOffice());
					studentBean.setStudentClass(new ClassBean(Integer.parseInt(studentInforBean.getClassID())));
					studentBean.setSubject(new SubjectBean(Integer.parseInt(studentInforBean.getSubjectID())));
					studentBean.setTerm(new TermBean(Integer.parseInt(studentInforBean.getTermID())));
					studentBean.setEduProgram(new EduProgramBean(Integer.parseInt(studentInforBean.getEduProgramID())));
					int studentID = studentDao.getIDByStudentNumber(studentInforBean.getStudentNumber());
					int instructionTeacherID = studentInforBean.getInstructorTeacherID() == null ? 0 : Integer.parseInt(studentInforBean.getInstructorTeacherID());
					int reviewTeacherID = studentInforBean.getReviewTeacherID() == null ? 0 : Integer.parseInt(studentInforBean.getReviewTeacherID());
					int assignmentID = getAssignmentID(studentID);
					if(instructionTeacherID != 0 || reviewTeacherID != 0) {
						assignmentBean = new AssignmentBean(assignmentID, teacherDao.getTeacherByID(instructionTeacherID), teacherDao.getTeacherByID(reviewTeacherID));
					} else if (assignmentID != 0) {
						assignmentBean = new AssignmentBean(assignmentID);
					}
					if(assignmentBean != null) {
						assignmentBean.setStudent(studentBean);
						studentBean.setAssignment(assignmentBean);
					}
					studentDao.updateInfor(studentBean, assignmentBean);
				} else {
					saveOrUpdateInfor(studentInforBean);
				}
			}
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
