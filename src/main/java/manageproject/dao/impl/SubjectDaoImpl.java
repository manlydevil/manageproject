/**Copyright(C) 2017
 *SubjectDaoImpl.java, Apr 9, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import manageproject.dao.SubjectDao;
import manageproject.entities.SubjectBean;
import manageproject.entities.TeacherBean;

/**
 * @author DELL
 *
 */
@Repository
@Transactional
public class SubjectDaoImpl implements SubjectDao {
	private Session session;
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.SubjectDao#getListSubject()
	 */
	public List<SubjectBean> getListSubject(int limit, int offset) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT s FROM "+SubjectBean.class.getName()+ " s ");
		Query query = session.createQuery(hqlCommand.toString());
		query.setFirstResult(offset);
		if(limit > 0) {
			query.setMaxResults(limit);
		}
		List<SubjectBean> listSubject = query.getResultList();
		return listSubject;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.SubjectDao#getSubjectByID(int)
	 */
	public SubjectBean getSubjectByID(int subjectID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+SubjectBean.class.getName()+"(s.subjectID, s.subjectName, s.subjectCode) FROM "+SubjectBean.class.getName()+" s WHERE s.subjectID =:subjectID");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("subjectID", subjectID);
		return (SubjectBean) query.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.SubjectDao#addSubject(manageproject.entities.SubjectBean)
	 */
	public boolean addSubject(SubjectBean subjectBean) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(subjectBean);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.SubjectDao#updateSubject(manageproject.entities.SubjectBean)
	 */
	public boolean updateSubject(SubjectBean subjectBean) {
		session = sessionFactory.getCurrentSession();
		session.update(subjectBean);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.SubjectDao#deleteSubject(manageproject.entities.SubjectBean)
	 */
	public boolean deleteSubject(SubjectBean subjectBean) {
		session = sessionFactory.getCurrentSession();
		session.delete(subjectBean);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.SubjectDao#getTotalRecords()
	 */
	public int getTotalRecords() {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT count(*) FROM "+SubjectBean.class.getName()+ " s ");
		Query query = session.createQuery(hqlCommand.toString());
		Long count = (Long) query.getSingleResult();
		return count.intValue();
	}
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.SubjectDao#getSubjectIDBySubjectCode(java.lang.String)
	 */
	public int getSubjectIDBySubjectCode(String subjectCode) {
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT s.subjectID FROM "+SubjectBean.class.getName()+" s ");
			hqlCommand.append("WHERE s.subjectCode =:subjectCode");
			Query query = session.createQuery(hqlCommand.toString());
			query.setParameter("subjectCode", subjectCode);
			return (Integer) query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	
}
