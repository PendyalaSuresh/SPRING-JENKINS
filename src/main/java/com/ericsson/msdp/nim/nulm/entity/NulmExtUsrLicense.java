package com.ericsson.msdp.nim.nulm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EXT_USR_LICENSE")

public class NulmExtUsrLicense implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	EmbeddedExtUsrLicense id;

	@Column(name="RDB_NAME" , insertable = false , updatable = false )
	private String rdb_name;
	@Column(name="MAX_LICENSE")
	private Integer max_license;
	@Column(name="LOGIN_HISTORY_DAYS")
	private String login_history_days;
	

	public NulmExtUsrLicense() {
	}
	public NulmExtUsrLicense( String rdb_name, Integer max_license, String login_history_days) {
		super();
		this.rdb_name = rdb_name;
		this.max_license = max_license;
		this.login_history_days=login_history_days;
	}
	public String getRdb_name() {
		return rdb_name;
	}
	public void setRdb_name(String rdb_name) {
		this.rdb_name = rdb_name;
	}
	public Integer getMax_license() {
		return max_license;
	}
	public void setMax_license(Integer max_license) {
		this.max_license = max_license;
	}
	public String getLogin_history_days() {
		return login_history_days;
	}
	public void setLogin_history_days(String login_history_days) {
		this.login_history_days = login_history_days;
	}
	
	
}
