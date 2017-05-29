/**Copyright(C) 2017
 *ProjectDaoImpl.java, Apr 1, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.services.drive.Drive;

import manageproject.controller.FileUploadController;
import manageproject.dao.ProjectDao;
import manageproject.entities.AssignmentBean;
import manageproject.entities.EduProgramBean;
import manageproject.entities.ProjectBean;
import manageproject.entities.StudentBean;
import manageproject.entities.SubmitProjectBean;
import manageproject.entities.TeacherBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.InforSearchFormBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
@Repository
@Transactional(rollbackFor=Exception.class)
public class ProjectDaoImpl implements ProjectDao {
	private Session session;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ProjectDao#getListProject(java.lang.String, java.lang.String, int, int, int, java.lang.String, java.lang.String, int)
	 */
	public List<ProjectInforBean> getListProject(String studentNumber, String name, int termID, int eduProgramID,
			int offset, String sortBy, String sortType, int limit) {
		List<ProjectInforBean> listProject = new ArrayList<ProjectInforBean>();
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+ProjectInforBean.class.getName()+"(p.projectID, p.topicName, sp.submitDate, s.name, a, ");
		hqlCommand.append("e.eduProgramName, t.termNumber, p.zipFile, p.pdfFile, sp.studentPublic, sp.teacherPublic, p.score, p.isPass)");
		hqlCommand.append(" FROM "+ProjectBean.class.getName()+" p, "+SubmitProjectBean.class.getName()+" sp, ");
		hqlCommand.append(StudentBean.class.getName()+" s, "+AssignmentBean.class.getName()+" a, ");
		hqlCommand.append(TermBean.class.getName()+" t, "+EduProgramBean.class.getName()+" e ");
		hqlCommand.append("WHERE s.term.termID = t.termID AND s.eduProgram.eduProgramID = e.eduProgramID AND s.studentID = a.studentID AND s.studentID = sp.studentID AND sp.projectID = p.projectID ");
		Map<String, Object> mapAttri = new HashMap<String, Object>();
		Set set = mapAttri.entrySet();
		Iterator iterator;
		if (studentNumber.length() > 0) {
			mapAttri.put("s.studentNumber", studentNumber);
		}
		if (name.length() > 0) {
			mapAttri.put("s.name", name);
		}
		if(termID != 0) {
			mapAttri.put("t.termID", termID);
		}
		if(eduProgramID != 0) {
			mapAttri.put("e.eduProgramID", eduProgramID);
		}
		if (mapAttri.size() > 0) {
			iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				hqlCommand.append(" AND ");
				if(mapEntry.getValue() instanceof Integer) {
					hqlCommand.append(mapEntry.getKey() + " = ? ");
				} else {
					hqlCommand.append(mapEntry.getKey() + " LIKE ? ");
				}
			}
		}
		Query<ProjectInforBean> query = session.createQuery(hqlCommand.toString(), ProjectInforBean.class);
		query.setFirstResult(offset);
		if(limit > 0) {				
			query.setMaxResults(limit);
		}
		if (mapAttri.size() > 0) {
			iterator = set.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				if(mapEntry.getValue() instanceof Integer) {
					query.setParameter(i++, mapEntry.getValue());
				} else {
					query.setParameter(i++, "%"+mapEntry.getValue()+"%");
				}
			}
		}
		listProject = query.getResultList();
		return listProject;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ProjectDao#getTotalRecords(java.lang.String, java.lang.String, int, int)
	 */
	public int getTotalRecords(String studentNumber, String name, int termID, int eduProgramID) {
		Long result = (long)0.0;
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT count(*) ");
		hqlCommand.append(" FROM "+ProjectBean.class.getName()+" p, "+SubmitProjectBean.class.getName()+" sp, ");
		hqlCommand.append(StudentBean.class.getName()+" s, "+AssignmentBean.class.getName()+" a, ");
		hqlCommand.append(TermBean.class.getName()+" t, "+EduProgramBean.class.getName()+" e ");
		hqlCommand.append("WHERE s.studentID = a.studentID AND s.studentID = sp.studentID AND sp.projectID = p.projectID ");
		hqlCommand.append("AND s.term.termID = t.termID AND s.eduProgram.eduProgramID = e.eduProgramID ");
		hqlCommand.append("AND sp.submitDate != ''");
		Map<String, Object> mapAttri = new HashMap<String, Object>();
		Set set = mapAttri.entrySet();
		Iterator iterator;
		if (studentNumber.length() > 0) {
			mapAttri.put("s.studentNumber", studentNumber);
		}
		if (name.length() > 0) {
			mapAttri.put("s.name", name);
		}
		if(termID != 0) {
			mapAttri.put("t.termID", termID);
		}
		if(eduProgramID != 0) {
			mapAttri.put("e.eduProgramID", eduProgramID);
		}
		if (mapAttri.size() > 0) {
			iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				hqlCommand.append(" AND ");
				if(mapEntry.getValue() instanceof Integer) {
					hqlCommand.append(mapEntry.getKey() + " = ? ");
				} else {
					hqlCommand.append(mapEntry.getKey() + " LIKE ? ");
				}
			}
		}
		Query query = session.createQuery(hqlCommand.toString());
		if (mapAttri.size() > 0) {
			iterator = set.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				if(mapEntry.getValue() instanceof Integer) {
					query.setParameter(i++, mapEntry.getValue());
				} else {
					query.setParameter(i++, "%"+mapEntry.getValue()+"%");
				}
			}
		}
		result = (Long) query.getSingleResult();
		return result.intValue();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ProjectDao#getProjectInforByID(int)
	 */
	public ProjectInforBean getProjectInforByID(int projectID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+ProjectInforBean.class.getName()+"(p.projectID, p.topicName, s.studentNumber,p.score, p.isPass, p.wordKey, p.zipFile, p.pdfFile, p.description, p.note, sp.studentPublic, sp.teacherPublic)");
		hqlCommand.append(" FROM "+ProjectBean.class.getName()+" p, ");
		hqlCommand.append(StudentBean.class.getName()+" s, "+SubmitProjectBean.class.getName()+" sp ");
		hqlCommand.append("WHERE p.projectID = sp.projectID AND s.studentID = sp.studentID ");
		hqlCommand.append("AND p.projectID =:projectID");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("projectID", projectID);
		ProjectInforBean inforBean = (ProjectInforBean) query.getSingleResult();
		return inforBean;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ProjectDao#insertOrUpdate(manageproject.entities.ProjectBean, manageproject.entities.SubmitProjectBean)
	 */
	public boolean insertOrUpdate(ProjectBean projectBean, SubmitProjectBean submitProjectBean) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(projectBean);
		submitProjectBean.setProjectID(projectBean.getProjectID());
		session.saveOrUpdate(submitProjectBean);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ProjectDao#getSubmitProByProID(int)
	 */
	public SubmitProjectBean getSubmitProByProID(int projectID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT s FROM "+SubmitProjectBean.class.getName()+" s ");
		hqlCommand.append("WHERE s.projectID =:projectID");
		Query<SubmitProjectBean> query = session.createQuery(hqlCommand.toString(), SubmitProjectBean.class);
		query.setParameter("projectID", projectID);
		SubmitProjectBean submitProjectBean = query.getSingleResult();
		return submitProjectBean;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ProjectDao#getListReferProject(java.lang.String, java.lang.String, int, int, java.lang.String, int, int)
	 */
	public List<ProjectInforBean> getListReferProject(String projectName, String keyword, int termID, int eduProgramID, String instructorTeacherName, int limit, int offset) {
		List<ProjectInforBean> listProject = new ArrayList<ProjectInforBean>();
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+ProjectInforBean.class.getName()+"(p.projectID, p.topicName, s.name, e.eduProgramName, tc, tcc, ");
		hqlCommand.append("p.zipFile, p.pdfFile, sp.studentPublic, sp.teacherPublic)");
		hqlCommand.append(" FROM "+StudentBean.class.getName()+" s ");
		hqlCommand.append("inner join "+EduProgramBean.class.getName()+" e on s.eduProgram.eduProgramID = e.eduProgramID ");
		hqlCommand.append("inner join "+AssignmentBean.class.getName()+" a on s.studentID = a.studentID ");
		hqlCommand.append("inner join "+SubmitProjectBean.class.getName()+" sp on s.studentID = sp.studentID ");
		hqlCommand.append("inner join "+ProjectBean.class.getName()+" p on sp.projectID = p.projectID ");
		hqlCommand.append("inner join "+TeacherBean.class.getName()+" tc on a.instructorTeacher.teacherID = tc.teacherID ");
		hqlCommand.append("inner join "+TeacherBean.class.getName()+" tcc on a.reviewTeacher.teacherID = tcc.teacherID");
		Map<String, Object> mapAttri = new HashMap<String, Object>();
		Set set = mapAttri.entrySet();
		Iterator iterator;
		if (projectName.length() > 0) {
			mapAttri.put("p.topicName", projectName);
		}
		if (keyword.length() > 0) {
			mapAttri.put("p.wordKey", keyword);
		}
		if(termID != 0) {
			mapAttri.put("s.term.termID", termID);
		}
		if(eduProgramID != 0) {
			mapAttri.put("e.eduProgramID", eduProgramID);
		}
		if (instructorTeacherName.length() > 0) {
			mapAttri.put("tc.teacherName", instructorTeacherName);
		}
		if (mapAttri.size() > 0) {
			hqlCommand.append(" WHERE ");
			iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				if(mapEntry.getValue() instanceof Integer) {
					hqlCommand.append(mapEntry.getKey() + " = ? ");
				} else {
					hqlCommand.append(mapEntry.getKey() + " LIKE ? ");
				}
				if(iterator.hasNext()) {
					hqlCommand.append(" AND ");
				}
			}
		}
		Query<ProjectInforBean> query = session.createQuery(hqlCommand.toString(), ProjectInforBean.class);
		query.setFirstResult(offset);
		if(limit > 0) {				
			query.setMaxResults(limit);
		}
		if (mapAttri.size() > 0) {
			iterator = set.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				if(mapEntry.getValue() instanceof Integer) {
					query.setParameter(i++, mapEntry.getValue());
				} else {
					query.setParameter(i++, "%"+mapEntry.getValue()+"%");
				}
			}
		}
		listProject = query.getResultList();
		return listProject;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ProjectDao#getTotalRecordReferPro(java.lang.String, java.lang.String, int, int, java.lang.String)
	 */
	public int getTotalRecordReferPro(String projectName, String keyword, int termID, int eduProgramID,String instructorTeacher) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT count(*) ");
		hqlCommand.append(" FROM "+StudentBean.class.getName()+" s inner join "+TermBean.class.getName()+" t on s.term.termID = t.termID ");
		hqlCommand.append("inner join "+EduProgramBean.class.getName()+" e on s.eduProgram.eduProgramID = e.eduProgramID ");
		hqlCommand.append("inner join "+AssignmentBean.class.getName()+" a on s.studentID = a.studentID ");
		hqlCommand.append("left join "+SubmitProjectBean.class.getName()+" sp on s.studentID = sp.studentID ");
		hqlCommand.append("inner join "+ProjectBean.class.getName()+" p on sp.projectID = p.projectID ");
		hqlCommand.append("inner join "+TeacherBean.class.getName()+" tc on a.instructorTeacher.teacherID = tc.teacherID ");
		hqlCommand.append("inner join "+TeacherBean.class.getName()+" tcc on a.reviewTeacher.teacherID = tcc.teacherID");
		Map<String, Object> mapAttri = new HashMap<String, Object>();
		Set set = mapAttri.entrySet();
		Iterator iterator;
		if (projectName.length() > 0) {
			mapAttri.put("p.topicName", projectName);
		}
		if (keyword.length() > 0) {
			mapAttri.put("p.wordKey", keyword);
		}
		if(termID != 0) {
			mapAttri.put("t.termID", termID);
		}
		if(eduProgramID != 0) {
			mapAttri.put("e.eduProgramID", eduProgramID);
		}
		if (instructorTeacher.length() > 0) {
			mapAttri.put("tc.teacherName", instructorTeacher);
		}
		if (mapAttri.size() > 0) {
			iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				hqlCommand.append(" AND ");
				if(mapEntry.getValue() instanceof Integer) {
					hqlCommand.append(mapEntry.getKey() + " = ? ");
				} else {
					hqlCommand.append(mapEntry.getKey() + " LIKE ? ");
				}
			}
		}
		Query query = session.createQuery(hqlCommand.toString());
		if (mapAttri.size() > 0) {
			iterator = set.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				Map.Entry mapEntry = (Map.Entry) iterator.next();
				if(mapEntry.getValue() instanceof Integer) {
					query.setParameter(i++, mapEntry.getValue());
				} else {
					query.setParameter(i++, "%"+mapEntry.getValue()+"%");
				}
			}
		}
		Long rs = (Long) query.getSingleResult();
		return rs.intValue();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ProjectDao#getProjectByStudentNumber(java.lang.String)
	 */
	public ProjectInforBean getProjectByStudentNumber(String studentNumber) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+ProjectInforBean.class.getName()+"(p.projectID, p.topicName, s.studentNumber, p.wordKey, p.zipFile, p.pdfFile, p.description, p.note, sp.studentPublic)");
		hqlCommand.append("FROM "+StudentBean.class.getName()+" s,"+ProjectBean.class.getName()+" p,"+SubmitProjectBean.class.getName()+" sp ");
		hqlCommand.append("WHERE s.studentID = sp.studentID AND sp.projectID = p.projectID AND s.studentNumber =:studentNumber");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("studentNumber", studentNumber);
		ProjectInforBean bean = (ProjectInforBean) query.getSingleResult();
		return bean;		
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ProjectDao#deleteProject(int)
	 */
	public boolean deleteProject(int projectID) {
		FileUploadController controller = new FileUploadController();
		session = sessionFactory.getCurrentSession();
		ProjectBean projectBean = session.find(ProjectBean.class, projectID);
		SubmitProjectBean submitProjectBean = getSubmitProByProID(projectID);
		session.delete(submitProjectBean);
		session.delete(projectBean);
		controller.deleteFolderOnDrive(Common.getFileId(projectBean.getZipFile()));
		controller.deleteFolderOnDrive(Common.getFileId(projectBean.getPdfFile()));
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ProjectDao#changeTeacherPublic(int, int)
	 */
	public boolean changeTeacherPublic(int projectID, int teacherPublic) {
		try {
			session = sessionFactory.getCurrentSession();
			SubmitProjectBean submitProjectBean = getSubmitProByProID(projectID);
			submitProjectBean.setTeacherPublic(teacherPublic);
			session.update(submitProjectBean);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
