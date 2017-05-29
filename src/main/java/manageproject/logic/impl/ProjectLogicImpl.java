/**Copyright(C) 2017
 *ProjectLogicImpl.java, Apr 1, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import manageproject.dao.ProjectDao;
import manageproject.dao.StudentDao;
import manageproject.entities.ProjectBean;
import manageproject.entities.SubmitProjectBean;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.ProjectInforSearch;
import manageproject.logic.ProjectLogic;
import manageproject.logic.StudentLogic;
import manageproject.utils.Common;
import manageproject.utils.CustomException;

/**
 * @author DELL
 *
 */
@Component
@Transactional(rollbackFor=Exception.class)
public class ProjectLogicImpl implements ProjectLogic {
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StudentLogic studentLogic;

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#getListProject(manageproject.entities.formbean.InforSearchFormBean, int, int)
	 */
	public List<ProjectInforBean> getListProject(InforSearchFormBean formBean, int limit, int offset) {
		try{
			formBean.setSortBy("p.projectID");
			return projectDao.getListProject(formBean.getStudentNumber(), formBean.getName(), Integer.parseInt(formBean.getTermID()), Integer.parseInt(formBean.getEduProgramID()), limit, formBean.getSortBy(), formBean.getSortType(), offset);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<ProjectInforBean>();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#getTotalRecords(manageproject.entities.formbean.InforSearchFormBean)
	 */
	public int getTotalRecords(InforSearchFormBean formBean) {
		try {
			return projectDao.getTotalRecords(formBean.getStudentNumber(), formBean.getName(), Integer.parseInt(formBean.getTermID()), Integer.parseInt(formBean.getEduProgramID()));
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#getProjectInforByID(int)
	 */
	public ProjectInforBean getProjectInforByID(int projectID) {
		try {
			return projectDao.getProjectInforByID(projectID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ProjectInforBean();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#insertOrUpdate(manageproject.entities.formbean.ProjectInforBean)
	 */
	public boolean insertOrUpdate(ProjectInforBean inforBean) {
		try {
			ProjectBean projectBean = new ProjectBean(inforBean.getProjectID(), inforBean.getProjectName(), inforBean.getKeyWord(), inforBean.getSourceLink(), 
					inforBean.getDescriptionLink(), inforBean.getDescription(), inforBean.getNote(), inforBean.getScore(), Integer.parseInt("1".equals(inforBean.getIsPass()) ? "1" : "0"));
			SubmitProjectBean submitProjectBean = getSubmitProByProID(inforBean.getProjectID());
			if(submitProjectBean.getSubmitDate() == null) {
				submitProjectBean.setSubmitDate(Common.getCurrentDate());
			}
			submitProjectBean.setProject(projectBean);
			submitProjectBean.setStudentPublic(Integer.parseInt("1".equals(inforBean.getStudentPublic()) ? "1" : "0"));
			submitProjectBean.setTeacherPublic(Integer.parseInt("1".equals(inforBean.getTeacherPublic()) ? "1" : "0"));
			submitProjectBean.setStudentID(studentDao.getStudentInfor(inforBean.getStudentNumber()).getStudentID());
			projectBean.setSubmitProject(submitProjectBean);
			return projectDao.insertOrUpdate(projectBean, submitProjectBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#getSubmitProByProID(int)
	 */
	public SubmitProjectBean getSubmitProByProID(int projectID) {
		try {
			return projectDao.getSubmitProByProID(projectID);
		} catch(Exception ex) {
			return new SubmitProjectBean();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#getListDataExport(manageproject.entities.formbean.InforSearchFormBean)
	 */
	public List<ProjectInforBean> getListDataExport(InforSearchFormBean formBean) {
		try{
			return projectDao.getListProject(formBean.getStudentNumber(), formBean.getName(), Integer.parseInt(formBean.getTermID()), Integer.parseInt(formBean.getEduProgramID()), 0, formBean.getSortBy(), formBean.getSortType(), 0);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<ProjectInforBean>();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#getReferProject(manageproject.entities.formbean.ProjectInforSearch, int, int)
	 */
	public List<ProjectInforBean> getReferProject(ProjectInforSearch inforSearch, int limit, int offset) {
		try{
			List<ProjectInforBean> listReferPro =  projectDao.getListReferProject(inforSearch.getProjectName(), inforSearch.getKeyWord(), inforSearch.getTermID(), inforSearch.getEduProgramID(), inforSearch.getInstructorTeacherName(), limit, offset);
			for(ProjectInforBean inforBean : listReferPro) {
				if(!(inforBean.getStudentPublic().equals("1") && inforBean.getTeacherPublic().equals("1"))) {
					inforBean.setDescriptionLink("");
					inforBean.setSourceLink("");
				}
			}
			return listReferPro;
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<ProjectInforBean>();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#getTotalRecordReferPro(manageproject.entities.formbean.ProjectInforSearch)
	 */
	public int getTotalRecordReferPro(ProjectInforSearch inforSearch) {
		try{
			return projectDao.getTotalRecordReferPro(inforSearch.getProjectName(), inforSearch.getKeyWord(), inforSearch.getTermID(), inforSearch.getEduProgramID(), inforSearch.getInstructorTeacherName());
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#getProjectByStudentNumber(java.lang.String)
	 */
	public ProjectInforBean getProjectByStudentNumber(String studentNumber) {
		ProjectInforBean projectInforBean = new ProjectInforBean();
		try {
			projectInforBean = projectDao.getProjectByStudentNumber(studentNumber);
		} catch(Exception ex) {
			ex.printStackTrace();
			projectInforBean.setStudentNumber(studentNumber);
		}
		return projectInforBean;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#deleteProject(int)
	 */
	public boolean deleteProject(int projectID) {
		try {
			return projectDao.deleteProject(projectID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#insertListProject(java.util.List)
	 */
	public boolean insertListProject(List<ProjectInforBean> listProject) throws CustomException {
		try {
			for(ProjectInforBean project : listProject) {
				if(!studentLogic.checkAssignedProject(project.getStudentNumber(), project.getProjectID())) {
					if(!insertOrUpdate(project)) {
						return false;
					}					
				} else {
					throw new CustomException("Có mã sinh viên đã được phân công. Hãy kiểm tra lại!");
				}
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ProjectLogic#changeTeacherPublic(int, int)
	 */
	public boolean changeTeacherPublic(int projectID, int teacherPublic) {
		try {
			return projectDao.changeTeacherPublic(projectID, teacherPublic);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
