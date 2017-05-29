/**Copyright(C) 2017
 *ConfigTimeDaoImpl.java, Mar 25, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import manageproject.dao.ConfigTimeDao;
import manageproject.entities.ConfigTimeBean;
import manageproject.entities.EduProgramBean;
import manageproject.entities.StudentBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.ConfigTimeFormBean;

/**
 * @author DELL
 *
 */
@Transactional
@Repository
public class ConfigTimeDaoImpl implements ConfigTimeDao {
	private Session session;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ConfigTimeDao#insertOrUpdate(manageproject.entities.ConfigTimeBean, manageproject.entities.TermBean, manageproject.entities.EduProgramBean)
	 */
	public boolean insertOrUpdate(ConfigTimeBean configTimeBean) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(configTimeBean);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ConfigTimeDao#getTotalRecords()
	 */
	public int getTotalRecords() {
		Long total = (long) 0.0;
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT count(*) ");
		hqlCommand.append("FROM "+ConfigTimeBean.class.getName()+" c");
		Query query = session.createQuery(hqlCommand.toString());
		total = (Long) query.getSingleResult();
		return total.intValue();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ConfigTimeDao#getListConfigTime(int, int, String, String)
	 */
	public List<ConfigTimeFormBean> getListConfigTime(String sortBy, String sortType) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+ConfigTimeFormBean.class.getName()+"(c.configTimeID, t.termNumber, e.eduProgramName, c.startDate, c.endDate, c.reviewDate)");
		hqlCommand.append("FROM "+ConfigTimeBean.class.getName()+" c, ");
		hqlCommand.append(TermBean.class.getName()+" t, "+EduProgramBean.class.getName()+" e ");
		hqlCommand.append("WHERE c.term.termID = t.termID AND c.eduProgram.eduProgramID = e.eduProgramID ");
		Query<ConfigTimeFormBean> query = session.createQuery(hqlCommand.toString(), ConfigTimeFormBean.class);
		List<ConfigTimeFormBean> list = query.getResultList();
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ConfigTimeDao#deleteConfig(int)
	 */
	public boolean deleteConfig(int configID) {
		session = sessionFactory.getCurrentSession();
		ConfigTimeBean configTimeBean = session.find(ConfigTimeBean.class, configID);
		session.delete(configTimeBean);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ConfigTimeDao#getConfigByID(int)
	 */
	public ConfigTimeFormBean getConfigByID(int ID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+ConfigTimeFormBean.class.getName()+"(c.configTimeID, t.termID, e.eduProgramID, c.startDate, c.endDate, c.reviewDate)");
		hqlCommand.append("FROM "+TermBean.class.getName()+" t ,");
		hqlCommand.append(EduProgramBean.class.getName()+" e ,");
		hqlCommand.append(ConfigTimeBean.class.getName()+" c ");
		hqlCommand.append("WHERE c.term.termID = t.termID AND c.eduProgram.eduProgramID = e.eduProgramID AND c.configTimeID =:ID");
		Query<ConfigTimeFormBean> query = session.createQuery(hqlCommand.toString(), ConfigTimeFormBean.class);
		query.setParameter("ID", ID);
		ConfigTimeFormBean bean = query.getSingleResult();
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ConfigTimeDao#getGeneralPassword(int, int)
	 */
	public String getGeneralPassword(int termID, int eduProgramID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT c.password FROM "+ConfigTimeBean.class.getName()+" c ");
		hqlCommand.append("WHERE c.term.termID =:termID AND c.eduProgram.eduProgramID =:eduProgramID");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("termID", termID);
		query.setParameter("eduProgramID", eduProgramID);
		String password = (String) query.getSingleResult();
		return password;
	}
	
	public ConfigTimeFormBean getDatelineByUserName(String userName) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+ConfigTimeFormBean.class.getName()+"(c.startDate, c.endDate) FROM "+ConfigTimeBean.class.getName()+" c,");
		hqlCommand.append(StudentBean.class.getName()+" s, "+TermBean.class.getName()+" t, "+EduProgramBean.class.getName()+ " e ");
		hqlCommand.append("WHERE s.term.termID = t.termID AND s.eduProgram.eduProgramID = e.eduProgramID AND c.term.termID = t.termID AND c.eduProgram.eduProgramID = e.eduProgramID ");
		hqlCommand.append("AND s.studentNumber =:userName");
		Query<ConfigTimeFormBean> query = session.createQuery(hqlCommand.toString(), ConfigTimeFormBean.class);
		query.setParameter("userName", userName);
		ConfigTimeFormBean bean = query.getSingleResult();
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.ConfigTimeDao#checkExistedConfig(int, int)
	 */
	public boolean checkExistedConfig(int configTimeID, int termID, int eduProgramID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT count(*) FROM "+ConfigTimeBean.class.getName()+" c ");
		hqlCommand.append("WHERE c.term.termID =:termID AND c.eduProgram.eduProgramID =:eduProgramID ");
		if(configTimeID > 0) {
			hqlCommand.append("AND c.configTimeID !=:configTimeID");
		}
		Query query = session.createQuery(hqlCommand.toString());
		if(configTimeID > 0) {
			query.setParameter("configTimeID", configTimeID);
		}		
		query.setParameter("termID", termID);
		query.setParameter("eduProgramID", eduProgramID);
		Long rs = (Long) query.getSingleResult();
		if(rs > 0) {
			return true;
		}
		return false;
	}
	
}
