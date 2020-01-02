package com.chris.ser.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {

	@Id
	@Column(name="aid")
	private String aid;

	@Column(name="apassword")
	private String apassword;

	@Column(name="aname")
	private String aname;

	@Column(name="asex")
	private String asex;

	@Column(name="aage")
	private String aage;

	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAsex() {
		return asex;
	}
	public void setAsex(String asex) {
		this.asex = asex;
	}
	public String getAage() {
		return aage;
	}
	public void setAage(String aage) {
		this.aage = aage;
	}
}
