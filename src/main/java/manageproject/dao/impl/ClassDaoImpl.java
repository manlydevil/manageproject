/**Copyright(C) 2017
 *ClassDaoImpl.java, Apr 9, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import manageproject.dao.ClassDao;
import manageproject.entities.ClassBean;

/**
 * @author DELL
 *
 */
@Repository
@Transactional(rollbackFor=Exception.class)
public class ClassDaoImpl implements ClassDao {
	
	private Session session;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<ClassBean> getListClass(int limit, int offset) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT c FROM "+ClassBean.class.getName()+" c ");
		Query query = session.createQuery(hqlCommand.toString());
		query.setFirstResult(offset);
		if(limit > 0) {
			query.setMaxResults(limit);
		}
		List<ClassBean> listClass = query.getResultList();
		return listClass;
	}

	public ClassBean getClassByID(int classID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+ClassBean.class.getName()+"(c.classID, c.className) FROM "+ClassBean.class.getName()+" c WHERE c.classID =:classID");
		Query<ClassBean> query = session.createQuery(hqlCommand.toString(), ClassBean.class);
		query.setParameter("classID", classID);
		return query.getSingleResult();
	}

	
	public boolean addClass(ClassBean studentClass) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(studentClass);
		return true;
	}
	
	public boolean deleteClass(ClassBean classBean) {
		session = sessionFactory.getCurrentSession();
		session.delete(classBean);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ClassDao#getTotalRecords()
	 */
	public int getTotalRecords() {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT count(*) FROM "+ClassBean.class.getName()+" c ");
		Query query = session.createQuery(hqlCommand.toString());
		Long count = (Long) query.getSingleResult();
		return count.intValue();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ClassDao#getClassID(java.lang.String)
	 */
	public int getClassID(String className) {
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT c.classID FROM "+ClassBean.class.getName()+" c ");
			hqlCommand.append("WHERE c.className =:className");
			Query query = session.createQuery(hqlCommand.toString());
			query.setParameter("className", className);
			return  (Integer) query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	

}
