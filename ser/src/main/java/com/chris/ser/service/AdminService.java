package com.chris.ser.service;


import com.chris.ser.po.Admin;

public interface AdminService {
	public Admin getadm(String aid) throws Exception;
	public void uppwd(Admin adm)throws Exception;
}

