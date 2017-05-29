/**Copyright(C) 2017
 *EduProgramLogicImpl.java, Mar 18, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.logic.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import manageproject.dao.EduProgramDao;
import manageproject.entities.EduProgramBean;
import manageproject.logic.EduProgramLogic;
import manageproject.utils.Common;

/**
 * @author DELL
 *
 */
@Service
@PropertySource(value="classpath:label_text.properties", encoding="UTF-8")
public class EduProgramLogicImpl implements EduProgramLogic {
	
	@Autowired
	private EduProgramDao eduProgramDao;
	@Autowired
	private Environment env;
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.EduProgramLogic#getAllEduProgram()
	 */
	public List<EduProgramBean> getAllEduProgram() {
		List<EduProgramBean> list = eduProgramDao.getAllEduProgram();
		EduProgramBean bean = new EduProgramBean(0, env.getProperty("LABEL_SELECT_EDUPRO"));
		list.add(0, bean);
		return list;
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.EduProgramLogic#getTotalRecords()
	 */
	public int getTotalRecords() {
		return eduProgramDao.getTotalRecords();
	}
	
	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.EduProgramLogic#getListEduProgram()
	 */
	public List<EduProgramBean> getListEduProgram() {
		return eduProgramDao.getListEduProgram();
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.EduProgramLogic#deleteEduPr(int)
	 */
	public boolean deleteEduPr(int eduID) {
		try {
			return eduProgramDao.deleteEduPr(eduID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.EduProgramLogic#getEduProgramByID(int)
	 */
	public EduProgramBean getEduProgramByID(int eduID) {
		try {
			return eduProgramDao.getEduProgramByID(eduID);
		} catch(Exception ex) {
			ex.printStackTrace();
			return new EduProgramBean();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.EduProgramLogic#insertOrUpdateEdu(manageproject.entities.EduProgramBean)
	 */
	public boolean insertOrUpdateEdu(EduProgramBean eduProgramBean) {
		try {
			return eduProgramDao.insertOrUpdateEdu(eduProgramBean);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see manageproject.logic.EduProgramLogic#getEduProgramID(java.lang.String)
	 */
	public String getEduProgramID(String eduProgramName) {
		try {
			for(EduProgramBean bean : eduProgramDao.getAllEduProgram()) {
				if(Common.standardizedString(eduProgramName).equals(Common.standardizedString(bean.getEduProgramName()))) {
					return String.valueOf(bean.getEduProgramID());
				}
			}
			return "";
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
}	
