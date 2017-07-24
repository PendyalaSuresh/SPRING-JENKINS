package com.ericsson.msdp.nim.nulm.domain;

import java.util.List;

public class UserLicense {
	
	private String rdbName;
	private int maxLicense;
	private String loginHistoryDays;
	private List<License> licenses;
	
	public String getRdbName() {
		return rdbName;
	}
	public void setRdbName(String rdb_name) {
		this.rdbName = rdb_name;
	}
	public int getMaxLicense() {
		return maxLicense;
	}
	public void setMaxLicense(int maxLicense) {
		this.maxLicense = maxLicense;
	}
	public String getLoginHistoryDays() {
		return loginHistoryDays;
	}
	public void setLoginHistoryDays(String loginHistoryDays) {
		this.loginHistoryDays = loginHistoryDays;
	}
	public List<License> getLicenses() {
		return licenses;
	}
	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
	}

	
}
