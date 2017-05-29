/**Copyright(C) 2017
 *StudentDaoImpl.java, Mar 13, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import manageproject.dao.StudentDao;
import manageproject.entities.AssignmentBean;
import manageproject.entities.EduProgramBean;
import manageproject.entities.ProjectBean;
import manageproject.entities.StudentBean;
import manageproject.entities.SubmitProjectBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.StudentInforBean;
/**
 * @author DELL
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class StudentDaoImpl implements StudentDao {
	
	private Session session;
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#checkExistedAccount(java.lang.String, java.lang.String)
	 */
	public boolean checkExistedAccount(String userName, String password) {
		try{
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT count(*) FROM "+StudentBean.class.getName()+" s ");
			hqlCommand.append("WHERE studentNumber=:userName and password=:password");
			Query query = session.createQuery(hqlCommand.toString());
			query.setParameter("userName", userName);
			query.setParameter("password", password);			
			Long rs = (Long) query.getSingleResult();
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
	 * @see manageproject.dao.StudentDao#getStuInforByID(java.lang.String)
	 */
	public StudentBean getStuInforByID(String userName) {
		StudentBean studentBean = new StudentBean();
		try{
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT new "+StudentBean.class.getName()+"(studentID, name, email, phone, workPlace, office)");
			hqlCommand.append(" FROM "+StudentBean.class.getName()+" s ");
			hqlCommand.append(" WHERE studentNumber=:userName");
			Query<StudentBean> query = session.createQuery(hqlCommand.toString(), StudentBean.class);
			query.setParameter("userName", userName);
			studentBean = query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return studentBean;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#checkExistedEmail(java.lang.String)
	 */
	public boolean checkExistedEmail(String userName, String email) {
		try{
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT count(*) FROM "+StudentBean.class.getName()+" s ");
			hqlCommand.append("WHERE studentNumber !=:studentNumber AND email =:email");
			Query query = session.createQuery(hqlCommand.toString());
			query.setParameter("studentNumber", userName);		
			query.setParameter("email", email);		
			Long rs = (Long) query.getSingleResult();
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
	 * @see manageproject.dao.StudentDao#updateInfor(manageproject.entities.StudentBean)
	 */
	public boolean saveOrUpdateInfor(StudentBean studentBean, AssignmentBean assignmentBean) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(studentBean);
		if(assignmentBean != null) {
			assignmentBean.setStudentID(studentBean.getStudentID());
			session.saveOrUpdate(assignmentBean);
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#getFullInfor(int)
	 */
	public StudentInforBean getFullInfor(int studentID) {
		StudentInforBean studentBean = new StudentInforBean();
		try{
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT new "+StudentInforBean.class.getName()+"(s.studentID, s.studentNumber, s.name, s.email, s.phone, s.workPlace, s.office, s.studentClass.classID, t.termID, s.subject.subjectID, e.eduProgramID, a)");
			hqlCommand.append(" FROM "+StudentBean.class.getName()+" s ");
			hqlCommand.append("inner join "+TermBean.class.getName()+" t on s.term.termID = t.termID ");
			hqlCommand.append("inner join "+EduProgramBean.class.getName()+" e on s.eduProgram.eduProgramID = e.eduProgramID ");
			hqlCommand.append("left join "+AssignmentBean.class.getName()+" a on s.studentID = a.studentID ");
			hqlCommand.append("WHERE s.studentID =:studentID");
			Query<StudentInforBean> query = session.createQuery(hqlCommand.toString(), StudentInforBean.class);
			query.setParameter("studentID", studentID);
			studentBean = query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return studentBean;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#getListStudent(java.lang.String, java.lang.String, int, int, int, java.lang.String, java.lang.String, int)
	 */
	public List<StudentInforBean> getListStudent(String studentNumber, String name, int termID, int eduProgramID, int offset, String sortBy, String sortType, int limit) {
		List<StudentInforBean> lstStudent = new ArrayList<StudentInforBean>();
		try{
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT new "+StudentInforBean.class.getName()+"(s.studentID, s.studentNumber, s.name, s.email, s.phone, s.workPlace, s.office, t.termNumber, e.eduProgramName, sp)");
			hqlCommand.append(" FROM "+StudentBean.class.getName()+" s inner join ");
			hqlCommand.append(TermBean.class.getName()+" t on s.term.termID = t.termID inner join ");
			hqlCommand.append(EduProgramBean.class.getName()+" e on s.eduProgram.eduProgramID = e.eduProgramID left join ");
			hqlCommand.append(SubmitProjectBean.class.getName()+" sp on s.studentID = sp.studentID ");
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
			hqlCommand.append(" ORDER BY "+sortBy +" "+ sortType);
			Query<StudentInforBean> query = session.createQuery(hqlCommand.toString(), StudentInforBean.class);
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
			lstStudent = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return lstStudent;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#getTotalRecords(java.lang.String, java.lang.String, int, int)
	 */
	public int getTotalRecords (String studentNumber, String name, int termID, int eduProgramID) {
		Long result = (long)0.0;
		try{
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT count(*) ");
			hqlCommand.append(" FROM "+StudentBean.class.getName()+" s ");
			hqlCommand.append(","+TermBean.class.getName()+" t ");
			hqlCommand.append(","+EduProgramBean.class.getName()+" e ");
			hqlCommand.append(" WHERE s.term.termID = t.termID AND s.eduProgram.eduProgramID = e.eduProgramID ");
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
			Query<Long> query = session.createQuery(hqlCommand.toString());
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
			result = query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return result.intValue();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#deleteStudent(manageproject.entities.StudentBean)
	 */
	public boolean deleteStudent(int studentID) {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			StudentBean studentBean = session.find(StudentBean.class, studentID);
			hqlCommand.append("SELECT a FROM "+AssignmentBean.class.getName()+" a WHERE a.studentID =:studentID");
			Query query = session.createQuery(hqlCommand.toString()).setParameter("studentID", studentID);
			AssignmentBean assignmentBean = (AssignmentBean) query.getSingleResult();
			hqlCommand = new StringBuilder();
		try {
			hqlCommand.append("SELECT sp FROM "+SubmitProjectBean.class.getName()+" sp WHERE sp.studentID =:studentID");
			query = session.createQuery(hqlCommand.toString()).setParameter("studentID", studentID);
			SubmitProjectBean submitProjectBean = (SubmitProjectBean) query.getSingleResult();
			session.delete(assignmentBean);
			session.delete(submitProjectBean);
			session.delete(session.find(ProjectBean.class, submitProjectBean.getProjectID()));
			session.delete(studentBean);
			return true;
		} catch(NoResultException ex) {
			session.delete(assignmentBean);
			session.delete(studentBean);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#getPasswordByUserName(java.lang.String)
	 */
	public String getPasswordByUserName(String userName) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT s.password FROM "+StudentBean.class.getName()+" s ");
		hqlCommand.append("WHERE s.studentNumber =:userName");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("userName", userName);
		String password = (String) query.getSingleResult();
		return password;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#checkExistedUserName(java.lang.String)
	 */
	public boolean checkExistedUserName(int studentID, String userName) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT count(*) FROM "+StudentBean.class.getName()+" s ");
		hqlCommand.append("WHERE s.studentNumber =:userName ");
		if(studentID > 0) {
			hqlCommand.append("AND s.studentID !=:studentID");
		}
		Query query = session.createQuery(hqlCommand.toString());
		if(studentID > 0) {
			query.setParameter("studentID", studentID);
		}
		query.setParameter("userName", userName);
		Long rs = (Long) query.getSingleResult();
		if(rs > 0) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#updateBasicInfor(manageproject.entities.StudentBean)
	 */
	public boolean updateBasicInfor(StudentBean studentBean) {
		session = sessionFactory.getCurrentSession();
		session.update(studentBean);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#getStudentInfor(int)
	 */
	public StudentBean getStudentInfor(String userName) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT s FROM "+StudentBean.class.getName()+" s ");
		hqlCommand.append("WHERE s.studentNumber =:userName");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("userName", userName);
		return (StudentBean) query.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#getAssignmentID(int)
	 */
	public int getAssignmentID(int studentID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT a.assignmentID FROM "+AssignmentBean.class.getName()+" a ");
		hqlCommand.append("WHERE a.studentID =:studentID");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("studentID", studentID);
		return (Integer) query.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#getSubmitID(int)
	 */
	public int getSubmitID(int studentID) {
		int rs = 0;
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT sp.submitID FROM "+StudentBean.class.getName()+" s,"+SubmitProjectBean.class.getName()+" sp ");
		hqlCommand.append("WHERE s.studentID = sp.studentID AND  s.studentID =:studentID");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("studentID", studentID);
		rs = (Integer) query.getSingleResult();
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#changePass(manageproject.entities.StudentBean)
	 */
	public boolean changePass(StudentBean studentBean) {
		session = sessionFactory.getCurrentSession();
		session.update(studentBean);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#checkAssignedProject(java.lang.String, int)
	 */
	public boolean checkAssignedProject(String studentNumber, int projectID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT count(*) FROM "+StudentBean.class.getName()+" s, "+SubmitProjectBean.class.getName()+" sp ");
		hqlCommand.append("WHERE s.studentID = sp.studentID AND s.studentNumber =:studentNumber AND sp.projectID !=:projectID");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("studentNumber", studentNumber);
		query.setParameter("projectID", projectID);
		Long rs = (Long) query.getSingleResult();
		if(rs > 0) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#getSubmitByStudentNumber(java.lang.String)
	 */
	public SubmitProjectBean getSubmitByStudentNumber(String studentNumber) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+SubmitProjectBean.class.getName()+"(sp.submitID, sp.projectID, sp.submitDate)");
		hqlCommand.append("FROM "+StudentBean.class.getName()+" s, "+SubmitProjectBean.class.getName()+" sp ");
		hqlCommand.append("WHERE s.studentID = sp.studentID AND s.studentNumber =:studentNumber");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("studentNumber", studentNumber);
		SubmitProjectBean submitProjectBean = (SubmitProjectBean) query.getSingleResult();
		return submitProjectBean;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#getTermAndEduProgram(java.lang.String)
	 */
	public StudentInforBean getTermAndEduProgram(String studentNumber) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+StudentInforBean.class.getName()+"(s.term.termNumber, s.eduProgram.eduProgramName, s.name) ");
		hqlCommand.append("FROM "+StudentBean.class.getName()+" s WHERE s.studentNumber =:studentNumber");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("studentNumber", studentNumber);
		return (StudentInforBean) query.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#updateInfor(manageproject.entities.StudentBean, manageproject.entities.AssignmentBean)
	 */
	public boolean updateInfor(StudentBean studentBean, AssignmentBean assignmentBean) {
		session = sessionFactory.getCurrentSession();
		session.update(studentBean);
		if(assignmentBean != null) {
			assignmentBean.setStudentID(studentBean.getStudentID());
			session.update(assignmentBean);
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.StudentDao#getIDByStudentNumber(java.lang.String)
	 */
	public int getIDByStudentNumber(String studentNumber) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT s.studentID FROM "+StudentBean.class.getName()+" s ");
		hqlCommand.append("WHERE s.studentNumber =:studentNumber");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("studentNumber", studentNumber);
		return (Integer) query.getSingleResult();
	}

}
