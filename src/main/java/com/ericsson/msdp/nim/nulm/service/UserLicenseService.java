package com.ericsson.msdp.nim.nulm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.msdp.nim.nulm.dao.UserLicenseDao;
import com.ericsson.msdp.nim.nulm.domain.License;
import com.ericsson.msdp.nim.nulm.domain.UserLicense;
import com.ericsson.msdp.nim.nulm.entity.NulmExtUsrLicense;
import com.ericsson.msdp.nim.nulm.exception.NULMException;

@Service
public class UserLicenseService {
	
	@Autowired
	UserLicenseDao userLicenseDao;

	public List<UserLicense> readAllUserLicenses() throws NULMException {
		List<UserLicense> userLicenses = new ArrayList<>();
		
		 List<NulmExtUsrLicense> listExtUsrLicenseInfo = userLicenseDao.readAll();
		
		if(listExtUsrLicenseInfo == null || listExtUsrLicenseInfo.isEmpty()){
			return userLicenses;
		}

		for (NulmExtUsrLicense licenseInfo : listExtUsrLicenseInfo) {
			UserLicense userLicense = new UserLicense();
			userLicense.setRdbName(licenseInfo.getRdb_name());
			userLicense.setMaxLicense(licenseInfo.getMax_license());
			userLicense.setLoginHistoryDays(licenseInfo.getLogin_history_days());
			List<License> licenses = fetchUsedLicenseCount(licenseInfo.getRdb_name());
			userLicense.setLicenses(licenses );
			userLicenses.add(userLicense);
		}
		
		return userLicenses;
	}
	
	private List<License> fetchUsedLicenseCount(String rdbName) {
		List<License> licenses = new ArrayList<>();
		
		License licence = new License();
		licence.setName("EAI concurrent user license (GUI)");
		licence.setUsedLicense((int) (Math.random() * 100));
		licenses.add(licence );
		
		License licence1 = new License();
		licence1.setName("EAI concurrent connections license (API)");
		licence1.setUsedLicense((int) (Math.random() * 100));
		licenses.add(licence1);
		return licenses;
	}

	public UserLicense updateUserLicense(UserLicense userLicense) throws NULMException {
		return userLicenseDao.update(userLicense);
	}

}
