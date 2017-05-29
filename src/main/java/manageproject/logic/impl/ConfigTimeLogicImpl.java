/**Copyright(C) 2017
 *ConfigTimeLogicImpl.java, Mar 24, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import manageproject.dao.ConfigTimeDao;
import manageproject.entities.ConfigTimeBean;
import manageproject.entities.EduProgramBean;
import manageproject.entities.TermBean;
import manageproject.entities.formbean.ConfigTimeFormBean;
import manageproject.logic.ConfigTimeLogic;
import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
@Component
public class ConfigTimeLogicImpl implements ConfigTimeLogic {
	@Autowired
	private ConfigTimeDao configTimeDao;

	public boolean insertOrUpdate(ConfigTimeFormBean configTimeFormBean) {
		try {
			TermBean termBean = new TermBean(Integer.parseInt(configTimeFormBean.getTermID()));
			EduProgramBean eduProgramBean = new EduProgramBean(Integer.parseInt(configTimeFormBean.getEduProgramID()));
			ConfigTimeBean configTimeBean = new ConfigTimeBean(configTimeFormBean.getConfigTimeID(), Common.convertDateHQL(configTimeFormBean.getStartDate()), Common.convertDateHQL(configTimeFormBean.getEndDate()),Common.convertDateHQL(configTimeFormBean.getReviewDate()), Common.encryptMD5(configTimeFormBean.getPassword()));
			configTimeBean.setTerm(termBean);
			configTimeBean.setEduProgram(eduProgramBean);
			return configTimeDao.insertOrUpdate(configTimeBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ConfigTimeLogic#getTotalRecords()
	 */
	public int getTotalRecords() {
		try {
			return configTimeDao.getTotalRecords();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ConfigTimeLogic#getListConfigTime(int, int, String, String)
	 */
	public List<ConfigTimeFormBean> getListConfigTime(String sortBy, String sortType) {
		try {
			return configTimeDao.getListConfigTime(sortBy, sortType);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ArrayList<ConfigTimeFormBean>();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ConfigTimeLogic#deleteConfig(int)
	 */
	public boolean deleteConfig(int configID) {
		try {
			return configTimeDao.deleteConfig(configID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ConfigTimeLogic#getConfigByID(int)
	 */
	public ConfigTimeFormBean getConfigByID(int ID) {
		try{
			return configTimeDao.getConfigByID(ID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ConfigTimeFormBean();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ConfigTimeLogic#getGeneralPassword(int, int)
	 */
	public String getGeneralPassword(int termID, int eduProgramID) {
		try{
			return configTimeDao.getGeneralPassword(termID, eduProgramID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ConfigTimeLogic#getDatelineByUserName(java.lang.String)
	 */
	public ConfigTimeFormBean getDatelineByUserName(String userName) {
		try{
			return configTimeDao.getDatelineByUserName(userName);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new ConfigTimeFormBean();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.ConfigTimeLogic#checkExistedConfig(int, int)
	 */
	public boolean checkExistedConfig(int configTimeID, int termID, int eduProgramID) {
		try{
			return configTimeDao.checkExistedConfig(configTimeID, termID, eduProgramID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	

}
