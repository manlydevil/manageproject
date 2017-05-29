/**Copyright(C) 2017
 *TermLogicImpl.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import manageproject.dao.TermDao;
import manageproject.entities.TermBean;
import manageproject.logic.TermLogic;
import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
@Service
@PropertySource(value="classpath:label_text.properties", encoding="UTF-8")
public class TermLogicImpl implements TermLogic {
	
	@Autowired
	private TermDao termDao;
	@Autowired
	private Environment env;
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TermLogic#getAllTerm()
	 */
	public List<TermBean> getAllTerm(){
		List<TermBean> lstTerm = termDao.getAllTerm();
		TermBean termBean = new TermBean(0, env.getProperty("LABEL_SELECT_TERM"));
		lstTerm.add(0, termBean);
		return lstTerm;
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TermLogic#getTotalRecords()
	 */
	public int getTotalRecords() {
		return termDao.getTotalRecords();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TermLogic#getListTerm()
	 */
	public List<TermBean> getListTerm() {
		return termDao.getListTerm();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TermLogic#deleteTerm(int)
	 */
	public boolean deleteTerm(int termID) {
		try {
			return termDao.deleteTerm(termID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TermLogic#getTermByID(int)
	 */
	public TermBean getTermByID(int termID) {
		try {
			return termDao.getTermByID(termID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new TermBean();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TermLogic#insertOrUpdateTerm(manageproject.entities.TermBean)
	 */
	public boolean insertOrUpdateTerm(TermBean term) {
		try {
			return termDao.insertOrUpdateTerm(term);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.TermLogic#getTermIDByTerm(java.lang.String)
	 */
	public String getTermIDByTerm(String term) {
		try {
			for(TermBean bean : getAllTerm()) {
				if(Common.standardizedString(term).equals(Common.standardizedString(bean.getTermNumber()))) {
					return String.valueOf(bean.getTermID());
				}
			}
			return "";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
}
