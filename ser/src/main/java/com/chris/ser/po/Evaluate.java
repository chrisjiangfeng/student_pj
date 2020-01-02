package com.chris.ser.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="evaluate")
public class Evaluate {

	@Id
	@Column(name="eid")
	private Integer eid;

	@Column(name="esid")
	private String esid;

	@Column(name="ecid")
	private Integer ecid;

	@Column(name="escore")
	private Integer escore;

	@Column(name = "ecomment")
	private String ecomment;

	@Column(name="edate")
	private Date edate;
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEsid() {
		return esid;
	}
	public void setEsid(String esid) {
		this.esid = esid;
	}
	public Integer getEcid() {
		return ecid;
	}
	public void setEcid(Integer ecid) {
		this.ecid = ecid;
	}
	public Integer getEscore() {
		return escore;
	}
	public void setEscore(Integer escore) {
		this.escore = escore;
	}
	public String getEcomment() {
		return ecomment;
	}
	public void setEcomment(String ecomment) {
		this.ecomment = ecomment;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
}
