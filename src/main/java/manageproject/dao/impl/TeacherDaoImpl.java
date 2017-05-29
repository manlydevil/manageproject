/**Copyright(C) 2017
 *TeacherDao.java, Mar 22, 2017 [Nguyễn Hưng Thuận]
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
import manageproject.dao.TeacherDao;
import manageproject.entities.AssignmentBean;
import manageproject.entities.DegreeBean;
import manageproject.entities.EduProgramBean;
import manageproject.entities.ProjectBean;
import manageproject.entities.StudentBean;
import manageproject.entities.SubmitProjectBean;
import manageproject.entities.TeacherAccountBean;
import manageproject.entities.TeacherBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.ProjectInforBean;
import manageproject.entities.formbean.StudentFormBean;
import manageproject.entities.formbean.StudentInforBean;
import manageproject.entities.formbean.TeacherFormBean;

/**
 * @author DELL
 *
 */
@Repository
@Transactional
public class TeacherDaoImpl implements TeacherDao {
	private Session session;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#getAllTeacher()
	 */
	public List<TeacherFormBean> getAllTeacher() {
		List<TeacherFormBean> list = new ArrayList<TeacherFormBean>();
		try{
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT new "+TeacherFormBean.class.getName()+"(t.teacherID, t.teacherName, t.degree.degreeCode) FROM "+TeacherBean.class.getName()+" t");
			Query<TeacherFormBean> query = session.createQuery(hqlCommand.toString(), TeacherFormBean.class);
			list = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#getTeacherByID(int)
	 */
	public TeacherBean getTeacherByID(int id) {
		TeacherBean teacherBean = new TeacherBean();
		try {
			session = sessionFactory.getCurrentSession();
			teacherBean = session.find(TeacherBean.class, id);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return teacherBean;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#getListTeacher(java.lang.String, int, int)
	 */
	public List<TeacherFormBean> getListTeacher(String teacherName, int limit, int offset) {
		List<TeacherFormBean> listTeacher = new ArrayList<TeacherFormBean>();
		try{
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT new "+TeacherFormBean.class.getName()+"(t.teacherID, t.teacherName, t.degree.degree, t.birthday, t.address)");
			hqlCommand.append(" FROM "+TeacherBean.class.getName()+" t ");
			Map<String, Object> mapAttri = new HashMap<String, Object>();
			Set set = mapAttri.entrySet();
			Iterator iterator;
			if (teacherName.length() > 0) {
				mapAttri.put("t.teacherName", teacherName);
			}
			if (mapAttri.size() > 0) {
				hqlCommand.append("WHERE ");
				iterator = set.iterator();
				for (int i = 0; i < mapAttri.size(); i++) {
					Map.Entry mapEntry = (Map.Entry) iterator.next();
					if (mapEntry.getValue() instanceof Integer) {
						hqlCommand.append(mapEntry.getKey() + " = ? ");
					} else {
						hqlCommand.append(mapEntry.getKey() + " LIKE ? ");
					}
					if(i < mapAttri.size() - 1) {
						hqlCommand.append(" AND ");
					}
				}
			}
			Query<TeacherFormBean> query = session.createQuery(hqlCommand.toString(), TeacherFormBean.class);
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
			listTeacher = query.getResultList();
	} catch(Exception ex) {
		ex.printStackTrace();
	}
		return listTeacher;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#getTotalRecords(java.lang.String)
	 */
	public int getTotalRecords(String teacherName) {
		Long rs = (long) 0.0;
		try{
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT count(*)");
			hqlCommand.append(" FROM "+TeacherBean.class.getName()+" t ");
			Map<String, Object> mapAttri = new HashMap<String, Object>();
			Set set = mapAttri.entrySet();
			Iterator iterator;
			if (teacherName.length() > 0) {
				mapAttri.put("t.teacherName", teacherName);
			}
			if (mapAttri.size() > 0) {
				hqlCommand.append("WHERE ");
				iterator = set.iterator();
				for (int i = 0; i < mapAttri.size(); i++) {
					Map.Entry mapEntry = (Map.Entry) iterator.next();
					if (mapEntry.getValue() instanceof Integer) {
						hqlCommand.append(mapEntry.getKey() + " = ? ");
					} else {
						hqlCommand.append(mapEntry.getKey() + " LIKE ? ");
					}
					if(i < mapAttri.size() - 1) {
						hqlCommand.append(" AND ");
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
			rs = (Long) query.getSingleResult();
	} catch(Exception ex) {
		ex.printStackTrace();
	}
		return rs.intValue();
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#deleteTeacher(int)
	 */
	public boolean deleteTeacher(int teacherID) {
		session = sessionFactory.getCurrentSession();
		session.delete(session.find(TeacherBean.class, teacherID));
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#insertOrUpdate(manageproject.entities.TeacherBean)
	 */
	public boolean insertOrUpdate(TeacherBean teacherBean) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(teacherBean);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#getListDegree()
	 */
	public List<DegreeBean> getListDegree() {
		List<DegreeBean> listDegree = new ArrayList<DegreeBean>();
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT d FROM "+DegreeBean.class.getName()+" d");
		Query query = session.createQuery(hqlCommand.toString());
		listDegree = query.getResultList();
		return listDegree;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#getListStudentAssigned(int, java.lang.String, java.lang.String, int, int, int, int)
	 */
	public List<ProjectInforBean> getListStudentInstruction(int teacherID, String studentNumber, String studentName,
			int termID, int eduProgramID, int limit, int offset) {
		List<ProjectInforBean> listResult = new ArrayList<ProjectInforBean>();
		try {
			StringBuilder hqlCommand = new StringBuilder();
			session = sessionFactory.getCurrentSession();
			hqlCommand.append("SELECT new "+ProjectInforBean.class.getName()+"(p, s.studentNumber, s.name, e.eduProgramName, t.termNumber, sp) FROM "+StudentBean.class.getName()+" s ");
			hqlCommand.append("INNER JOIN "+TermBean.class.getName()+" t on s.term.termID = t.termID ");
			hqlCommand.append("INNER JOIN "+EduProgramBean.class.getName()+" e on s.eduProgram.eduProgramID = e.eduProgramID ");
			hqlCommand.append("INNER JOIN "+AssignmentBean.class.getName()+" a on a.studentID = s.studentID ");
			hqlCommand.append("LEFT JOIN "+SubmitProjectBean.class.getName()+" sp on s.studentID = sp.studentID ");
			hqlCommand.append("LEFT JOIN "+ProjectBean.class.getName()+" p on sp.projectID = p.projectID ");
			hqlCommand.append("WHERE a.instructorTeacher.teacherID = ? ");
			Map<String, Object> mapAttri = new HashMap<String, Object>();
			Set set = mapAttri.entrySet();
			Iterator iterator;
			if (studentNumber.length() > 0) {
				mapAttri.put("s.studentNumber", studentNumber);
			}
			if (studentName.length() > 0) {
				mapAttri.put("s.name", studentName);
			}
			if(termID != 0) {
				mapAttri.put("t.termID", termID);
			}
			if(eduProgramID != 0) {
				mapAttri.put("e.eduProgramID", eduProgramID);
			}
			if (mapAttri.size() > 0) {
				iterator = set.iterator();
				for (int i = 0; i < mapAttri.size(); i++) {
					hqlCommand.append(" AND ");
					Map.Entry mapEntry = (Map.Entry) iterator.next();
					if (mapEntry.getValue() instanceof Integer) {
						hqlCommand.append(mapEntry.getKey() + " = ? ");
					} else {
						hqlCommand.append(mapEntry.getKey() + " LIKE ? ");
					}
				}
			}
			Query<ProjectInforBean> query = session.createQuery(hqlCommand.toString(), ProjectInforBean.class);
			query.setParameter(0, teacherID);
			query.setFirstResult(offset);
			if(limit > 0) {				
				query.setMaxResults(limit);
			}
			if (mapAttri.size() > 0) {
				iterator = set.iterator();
				int i = 1;
				while (iterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) iterator.next();
					if(mapEntry.getValue() instanceof Integer) {
						query.setParameter(i++, mapEntry.getValue());
					} else {
						query.setParameter(i++, "%"+mapEntry.getValue()+"%");
					}
				}
			}
			listResult =  query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return listResult;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#getTotalStudent(int, java.lang.String, java.lang.String, int, int)
	 */
	public int getTotalStudent(int teacherID, String studentNumber, String studentName, int termID, int eduProgramID) {
		Long rs = (long) 0.0;
		try {
			StringBuilder hqlCommand = new StringBuilder();
			session = sessionFactory.getCurrentSession();
			hqlCommand.append("SELECT count(*) FROM "+StudentBean.class.getName()+" s ");
			hqlCommand.append("INNER JOIN "+TermBean.class.getName()+" t on s.term.termID = t.termID ");
			hqlCommand.append("INNER JOIN "+EduProgramBean.class.getName()+" e on s.eduProgram.eduProgramID = e.eduProgramID ");
			hqlCommand.append("INNER JOIN "+AssignmentBean.class.getName()+" a on a.studentID = s.studentID ");
			hqlCommand.append("LEFT JOIN "+SubmitProjectBean.class.getName()+" sp on s.studentID = sp.studentID ");
			hqlCommand.append("LEFT JOIN "+ProjectBean.class.getName()+" p on sp.projectID = p.projectID ");
			hqlCommand.append("WHERE a.instructorTeacher.teacherID = ?");
			Map<String, Object> mapAttri = new HashMap<String, Object>();
			Set set = mapAttri.entrySet();
			Iterator iterator;
			if (studentNumber.length() > 0) {
				mapAttri.put("s.studentNumber", studentNumber);
			}
			if (studentName.length() > 0) {
				mapAttri.put("s.name", studentName);
			}
			if(termID != 0) {
				mapAttri.put("t.termID", termID);
			}
			if(eduProgramID != 0) {
				mapAttri.put("e.eduProgramID", eduProgramID);
			}
			if (mapAttri.size() > 0) {
				iterator = set.iterator();
				for (int i = 0; i < mapAttri.size(); i++) {
					hqlCommand.append(" AND ");
					Map.Entry mapEntry = (Map.Entry) iterator.next();
					if (mapEntry.getValue() instanceof Integer) {
						hqlCommand.append(mapEntry.getKey() + " = ? ");
					} else {
						hqlCommand.append(mapEntry.getKey() + " LIKE ? ");
					}
				}
			}
			Query query = session.createQuery(hqlCommand.toString());
			query.setParameter(0, teacherID);
			if (mapAttri.size() > 0) {
				iterator = set.iterator();
				int i = 1;
				while (iterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) iterator.next();
					if(mapEntry.getValue() instanceof Integer) {
						query.setParameter(i++, mapEntry.getValue());
					} else {
						query.setParameter(i++, "%"+mapEntry.getValue()+"%");
					}
				}
			}
			rs = (Long) query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs.intValue();
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#checkExistedAccountInfor(java.lang.String, java.lang.String)
	 */
	public boolean checkExistedAccountInfor(String userName, String password) {
		Long rs = (long) 0.0;
		try{
			StringBuilder hqlCommand = new StringBuilder();
			session = sessionFactory.getCurrentSession();
			hqlCommand.append("SELECT count(*) FROM "+TeacherAccountBean.class.getName()+" t ");
			hqlCommand.append("WHERE t.userName=:userName AND t.password=:password");
			Query query = session.createQuery(hqlCommand.toString());
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			rs = (Long) query.getSingleResult();
			if(rs > 0) {
				return true;
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#getTeacherIDByUserName(java.lang.String)
	 */
	public int getTeacherIDByUserName(String userName) {
		try {
			StringBuilder hqlCommand = new StringBuilder();
			session = sessionFactory.getCurrentSession();
			hqlCommand.append("SELECT t.teacherID FROM "+TeacherAccountBean.class.getName()+" t ");
			hqlCommand.append("WHERE t.userName =:userName");
			Query query = session.createQuery(hqlCommand.toString());
			query.setParameter("userName", userName);
			return (Integer) query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TeacherDao#getTeacherIDByName(java.lang.String)
	 */
	public int getTeacherIDByName(String name) {
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT t.teacherID FROM "+TeacherBean.class.getName()+" t ");
			hqlCommand.append("WHERE t.teacherName =:teacherName");
			Query query = session.createQuery(hqlCommand.toString());
			query.setParameter("teacherName", name);
			return (Integer) query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	public List<ProjectInforBean> getListStudentReview(int teacherID, String studentNumber, String studentName,
			int termID, int eduProgramID) {
		List<ProjectInforBean> listResult = new ArrayList<ProjectInforBean>();
		try {
			StringBuilder hqlCommand = new StringBuilder();
			session = sessionFactory.getCurrentSession();
			hqlCommand.append("SELECT new "+ProjectInforBean.class.getName()+"(p, sp, s.studentNumber, s.name, e.eduProgramName, t.termNumber) FROM "+StudentBean.class.getName()+" s ");
			hqlCommand.append("INNER JOIN "+TermBean.class.getName()+" t on s.term.termID = t.termID ");
			hqlCommand.append("INNER JOIN "+EduProgramBean.class.getName()+" e on s.eduProgram.eduProgramID = e.eduProgramID ");
			hqlCommand.append("INNER JOIN "+AssignmentBean.class.getName()+" a on a.studentID = s.studentID ");
			hqlCommand.append("LEFT JOIN "+SubmitProjectBean.class.getName()+" sp on s.studentID = sp.studentID ");
			hqlCommand.append("LEFT JOIN "+ProjectBean.class.getName()+" p on sp.projectID = p.projectID ");
			hqlCommand.append("WHERE a.reviewTeacher.teacherID = ? ");
			Map<String, Object> mapAttri = new HashMap<String, Object>();
			Set set = mapAttri.entrySet();
			Iterator iterator;
			if (studentNumber.length() > 0) {
				mapAttri.put("s.studentNumber", studentNumber);
			}
			if (studentName.length() > 0) {
				mapAttri.put("s.name", studentName);
			}
			if(termID != 0) {
				mapAttri.put("t.termID", termID);
			}
			if(eduProgramID != 0) {
				mapAttri.put("e.eduProgramID", eduProgramID);
			}
			if (mapAttri.size() > 0) {
				iterator = set.iterator();
				for (int i = 0; i < mapAttri.size(); i++) {
					hqlCommand.append(" AND ");
					Map.Entry mapEntry = (Map.Entry) iterator.next();
					if (mapEntry.getValue() instanceof Integer) {
						hqlCommand.append(mapEntry.getKey() + " = ? ");
					} else {
						hqlCommand.append(mapEntry.getKey() + " LIKE ? ");
					}
				}
			}
			Query<ProjectInforBean> query = session.createQuery(hqlCommand.toString(), ProjectInforBean.class);
			query.setParameter(0, teacherID);
			if (mapAttri.size() > 0) {
				iterator = set.iterator();
				int i = 1;
				while (iterator.hasNext()) {
					Map.Entry mapEntry = (Map.Entry) iterator.next();
					if(mapEntry.getValue() instanceof Integer) {
						query.setParameter(i++, mapEntry.getValue());
					} else {
						query.setParameter(i++, "%"+mapEntry.getValue()+"%");
					}
				}
			}
			listResult =  query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return listResult;
	}
	
	
	
}
