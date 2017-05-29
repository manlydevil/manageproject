/**Copyright(C) 2017
 *EduProgramDaoImpl.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import manageproject.dao.EduProgramDao;
import manageproject.entities.EduProgramBean;
import manageproject.entities.TeacherBean;
import manageproject.entities.TermBean;

/**
 * @author DELL
 *
 */
@Repository
@Transactional
public class EduProgramDaoImpl implements EduProgramDao {
	private Session session;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public List<EduProgramBean> getAllEduProgram () {
		List<EduProgramBean> lstPr = new ArrayList<EduProgramBean>();
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT new "+EduProgramBean.class.getName()+"(e.eduProgramID, e.eduProgramName) FROM "+EduProgramBean.class.getName()+" e");
			Query<EduProgramBean> query = session.createQuery(hqlCommand.toString(), EduProgramBean.class);
			lstPr = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return lstPr;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.EduProgramDao#getTotalRecords()
	 */
	public int getTotalRecords() {
		Long result = (long) 0.0;
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT count(*) FROM "+EduProgramBean.class.getName()+" e ");
			Query query = session.createQuery(hqlCommand.toString());
			result = (Long) query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return result.intValue();
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.EduProgramDao#getListEduProgram(int, int)
	 */
	public List<EduProgramBean> getListEduProgram() {
		List<EduProgramBean> listED = new ArrayList<EduProgramBean>();
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT e FROM "+EduProgramBean.class.getName()+" e ");
			Query query = session.createQuery(hqlCommand.toString());
			listED = query.getResultList();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return listED;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.EduProgramDao#deleteEduPr(int)
	 */
	public boolean deleteEduPr(int eduID) {
		session = sessionFactory.getCurrentSession();
		session.delete(session.find(EduProgramBean.class, eduID));
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.EduProgramDao#getEduProgramByID(int)
	 */
	public EduProgramBean getEduProgramByID(int eduID) {
		session = sessionFactory.getCurrentSession();
		StringBuilder hqlCommand = new StringBuilder();
		hqlCommand.append("SELECT new "+EduProgramBean.class.getName()+"(e.eduProgramID, e.eduProgramName) FROM "+EduProgramBean.class.getName()+" e ");
		hqlCommand.append("WHERE e.eduProgramID =:eduProgramID");
		Query query = session.createQuery(hqlCommand.toString());
		query.setParameter("eduProgramID", eduID);
		EduProgramBean eduProgramBean = (EduProgramBean) query.getSingleResult();
		return eduProgramBean;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.EduProgramDao#insertOrUpdateEdu(manageproject.entities.EduProgramBean)
	 */
	public boolean insertOrUpdateEdu(EduProgramBean eduProgramBean) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(eduProgramBean);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.dao.EduProgramDao#getEduProgramID(java.lang.String)
	 */
	public int getEduProgramID(String eduProgramName) {
		try {
			session = sessionFactory.getCurrentSession();
			StringBuilder hqlCommand = new StringBuilder();
			hqlCommand.append("SELECT e.eduProgramID FROM "+EduProgramBean.class.getName()+" e ");
			hqlCommand.append("WHERE e.eduProgramName =:eduProgramName");
			Query query = session.createQuery(hqlCommand.toString());
			query.setParameter("eduProgramName", eduProgramName);
			return (Integer) query.getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
}
