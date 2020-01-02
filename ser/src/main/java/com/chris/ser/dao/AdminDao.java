package com.chris.ser.dao;


import com.chris.ser.po.Admin;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface AdminDao extends Mapper<Admin>, MySqlMapper<Admin> {
	public Admin findadm(String sid);
	public void uppwd(Admin adm);
}
