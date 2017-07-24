package com.ericsson.msdp.nim.nulm.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ericsson.msdp.nim.nulm.domain.UserLicense;
import com.ericsson.msdp.nim.nulm.entity.ModelAccessorBean;
import com.ericsson.msdp.nim.nulm.entity.NulmExtUsrLicense;
import com.ericsson.msdp.nim.nulm.exception.NULMException;
import com.ericsson.msdp.nim.nulm.util.HibernateUtil;

@Repository
public class UserLicenseDao {
	private static Logger logger = Logger.getLogger(ModelAccessorBean.class.getName());
	@Autowired
	SessionFactory sessionFactory;

	public List<NulmExtUsrLicense> readAll() throws NULMException {
		List<NulmExtUsrLicense> listExtUsrLicenseInfo = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria crt = session.createCriteria(NulmExtUsrLicense.class);

			listExtUsrLicenseInfo = crt.list();

		} catch (SessionException e) {
			logger.error("NULMException occured " + NULMException.ErrorCode.FAILED_TO_CREATE_SESSION + e);
			throw new NULMException(NULMException.ErrorCode.FAILED_TO_CREATE_SESSION, e);
		} catch (HibernateException e) {
			logger.error("NULMException occured " + NULMException.ErrorCode.OTHER_HIBERNATE_EXCEPTION + e);
			throw new NULMException(NULMException.ErrorCode.OTHER_HIBERNATE_EXCEPTION, e);
		} catch (Throwable e) {
			logger.error("NULMException occured " + e);
			throw new NULMException(NULMException.ErrorCode.OTHER_EXCEPTION, e);
		} finally {
			HibernateUtil.sessionClose(session);
		}
		return listExtUsrLicenseInfo;
	}

	public UserLicense update(UserLicense userLicense) throws NULMException {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.update(userLicense);			
		} catch (SessionException e) {
			logger.error("NULMException occured " + NULMException.ErrorCode.FAILED_TO_CREATE_SESSION + e);
			throw new NULMException(NULMException.ErrorCode.FAILED_TO_CREATE_SESSION, e);
		} catch (HibernateException e) {
			logger.error("NULMException occured " + NULMException.ErrorCode.OTHER_HIBERNATE_EXCEPTION + e);
			throw new NULMException(NULMException.ErrorCode.OTHER_HIBERNATE_EXCEPTION, e);
		} catch (Throwable e) {
			logger.error("NULMException occured " + e);
			throw new NULMException(NULMException.ErrorCode.OTHER_EXCEPTION, e);
		} finally {
			HibernateUtil.sessionClose(session);
		}
		return userLicense;
	}


}
