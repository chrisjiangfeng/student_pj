package com.chris.ser.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="studentandcurriculum")
public class Studentandcurriculum {

	@Id
	@Column(name="ssid")
	private String ssid;

	@Id
	@Column(name="scid")
	private Integer scid;

	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public Integer getScid() {
		return scid;
	}
	public void setScid(Integer scid) {
		this.scid = scid;
	}
}
