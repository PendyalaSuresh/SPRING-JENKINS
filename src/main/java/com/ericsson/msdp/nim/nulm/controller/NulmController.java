package com.ericsson.msdp.nim.nulm.controller;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ericsson.msdp.nim.nulm.domain.UserLicense;
import com.ericsson.msdp.nim.nulm.exception.NULMException;
import com.ericsson.msdp.nim.nulm.service.UserLicenseService;
import com.telcordia.granite.sdk.service.EjbGraniteServiceFactory;
import com.telcordia.granite.sdk.session.GraniteSession;

@RestController
//@RequestMapping("/api")
@Path("/api")
public class NulmController {
	
	private static Logger logger = Logger.getLogger(NulmController.class.getName());

	@Autowired
	UserLicenseService userLicenseService;

	@RequestMapping(value = "/userlicenses", method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<List<UserLicense>> listAllUsers(HttpServletRequest request,
    		@RequestParam("token") String token) {
		logger.info("entered userlicenses");
		List<UserLicense> userLicenses = null;
		try {
			URL url = new URL(request.getRequestURL().toString());
		    String host  = url.getHost();
		    int port = url.getPort();
		    logger.debug("PROVIDER_URL:"+"t3://"+host+":"+port+"/");
		    
			Properties p = new Properties();
            p.setProperty(Context.PROVIDER_URL, "t3://"+host+":"+port+"/");
            p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            Context ctx = new InitialContext(p);
            EjbGraniteServiceFactory ejbGraniteServiceFactory = new EjbGraniteServiceFactory(ctx);
            GraniteSession graniteSession = ejbGraniteServiceFactory.loginWithWebAuthenticationToken(token);
           
            String userName = graniteSession.getUserName();
            String databaseName = graniteSession.getDatabaseName();
            logger.debug("userName:" + userName +", databaseName:"+databaseName);
            
			userLicenses = userLicenseService.readAllUserLicenses();
    		logger.info("userlicenses found:"+userLicenses.size());
		} catch (NULMException | NamingException e) {
			logger.error("failed to get user licenses",e);
            return new ResponseEntity<List<UserLicense>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(userLicenses == null || userLicenses.isEmpty()){
    		logger.info("entered userlicenses isEmpty");
            return new ResponseEntity<List<UserLicense>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserLicense>>(userLicenses, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/userlicenses", method = RequestMethod.PUT,
	        produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<UserLicense> updateUserLicense(@RequestBody UserLicense userLicense) {
		logger.info("entered userlicenses");
		try {			            
			userLicense = userLicenseService.updateUserLicense(userLicense);
    		logger.info("userlicense updated:"+userLicense.getRdbName());
		} catch (NULMException e) {
			logger.error("failed to get user licenses",e);
            return new ResponseEntity<UserLicense>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
        return new ResponseEntity<UserLicense>(userLicense, HttpStatus.OK);
    }
}