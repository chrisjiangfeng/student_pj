package com.chris.ser.service;


import com.chris.ser.dao.AdminDao;
import com.chris.ser.po.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminServiceImpl")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao ad;

	@Override
	public Admin getadm(String aid) throws Exception {
		Admin adm = ad.findadm(aid);
		return adm;
	}

	@Override
	public void uppwd(Admin adm) throws Exception {
		ad.uppwd(adm);
		}
	
	

}
