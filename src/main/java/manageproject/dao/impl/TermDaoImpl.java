/**Copyright(C) 2017
 *TermDaoImpl.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manageproject.dao.TermDao;
import manageproject.entities.TermBean;

/**
 * @author DELL
 *
 */
@Repository
@Transactional
public class TermDaoImpl implements TermDao {
	private Session session;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TermDao#getAllTerm()
	 */
	public List<TermBean> getAllTerm () {
		List<TermBean> lstTerm = new ArrayList<TermBean>();
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT new "+TermBean.class.getName()+"(t.termID, t.termNumber) FROM "+TermBean.class.getName()+" t");
			Query<TermBean> query = session.createQuery(hqlCommand.toString(), TermBean.class);
			lstTerm = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return lstTerm;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TermDao#getTotalRecords()
	 */
	public int getTotalRecords() {
		Long result = (long) 0.0;
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT count(*) FROM "+TermBean.class.getName()+" t ");
			Query query = session.createQuery(hqlCommand.toString());
			result = (Long) query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return result.intValue();
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TermDao#getListTerm()
	 */
	public List<TermBean> getListTerm() {
		List<TermBean> listTerm = new ArrayList<TermBean>();
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT t FROM "+TermBean.class.getName()+" t ");
			Query query = session.createQuery(hqlCommand.toString());
			listTerm = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return listTerm;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TermDao#deleteTerm(int)
	 */
	public boolean deleteTerm(int termID) {
		session = sessionFactory.getCurrentSession();
		session.delete(session.find(TermBean.class, termID));
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TermDao#getTermByID(int)
	 */
	public TermBean getTermByID(int termID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+TermBean.class.getName()+"(t.termID, t.termNumber) FROM "+TermBean.class.getName()+" t " );
		hqlCommand.append("WHERE t.termID =:termID");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("termID", termID);
		TermBean termBean = (TermBean) query.getSingleResult();
		return termBean;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TermDao#insertOrUpdateTerm(manageproject.entities.TermBean)
	 */
	public boolean insertOrUpdateTerm(TermBean term) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(term);
		return true;
	}
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.TermDao#getTermIDByTerm(java.lang.String)
	 */
	public int getTermIDByTerm(String term) {
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT t.termID FROM "+TermBean.class.getName()+" t ");
			hqlCommand.append("WHERE t.termNumber =:termNumber");
			Query query = session.createQuery(hqlCommand.toString());
			query.setParameter("termNumber", term);
			return (Integer) query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
