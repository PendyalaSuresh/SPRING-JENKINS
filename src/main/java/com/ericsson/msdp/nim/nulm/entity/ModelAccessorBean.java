package com.ericsson.msdp.nim.nulm.entity;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ericsson.msdp.nim.nulm.exception.NULMException;
import com.ericsson.msdp.nim.nulm.util.HibernateUtil;
/**
 * @author eabibha
 */
public class ModelAccessorBean{
  private  List<NulmExtUsrLicense> listExtUsrLicenseInfo;
  private static final String LOG4J_PROPERTIES = "log4j.xml";
  private static Logger logger = Logger.getLogger(ModelAccessorBean.class.getName());
	@Autowired
	SessionFactory sessionFactory;
	/**
	 * <p> returns max license and login history days based on 
	 * rdb name .
	 *
	 * 
	 * @param rdbname
	 * @return
	 * @throws NULMException 
	 */
  
  public Object[] getExtUsrLicenseInfoImpl(String rdbname) throws NULMException
    {
	Object[] licenseInfo = new Object[2];
	boolean isMapped=false;
    DOMConfigurator.configure("resources//"+ LOG4J_PROPERTIES);
    logger.debug("App Settings values for getExtUsrLicenseInfoImpl.");
    Session session = null;
    try{
      session = sessionFactory.openSession();
      Criteria crt = session.createCriteria(NulmExtUsrLicense.class); 
    		  
      listExtUsrLicenseInfo = crt.list();
      
      for (NulmExtUsrLicense conf : listExtUsrLicenseInfo){  
        if (rdbname.equals(conf.getRdb_name())){
        	 isMapped=true;
        	 licenseInfo[0] = conf.getMax_license();
        	 licenseInfo[1] = conf.getLogin_history_days();
             }
      }
    
  
      if (!isMapped){
    	  logger.info("getExtUsrLicenseInfoImpl method executed successfully");
          throw new NULMException(NULMException.ErrorCode.RDB_DOES_NOT_EXIST);
         
      }
        return licenseInfo;
    }
    catch (SessionException e){
      logger.error("NULMException occured "+ NULMException.ErrorCode.FAILED_TO_CREATE_SESSION + e );
      throw new NULMException(NULMException.ErrorCode.FAILED_TO_CREATE_SESSION, e);
    }
    catch (HibernateException e){
      logger.error("NULMException occured "+ NULMException.ErrorCode.OTHER_HIBERNATE_EXCEPTION + e );
      throw new NULMException(NULMException.ErrorCode.OTHER_HIBERNATE_EXCEPTION, e);
    }
    catch (Exception e){
      logger.error("NULMException occured "+ e);
      throw new NULMException(NULMException.ErrorCode.OTHER_EXCEPTION, e);
    }
    finally{
    	HibernateUtil.sessionClose(session);
    }
  }
  

 }
