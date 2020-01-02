package com.chris.ser.service;

import com.chris.ser.bo.PageBean;
import com.chris.ser.po.Teacher;

import java.util.List;

public interface TeacherService {
	public Teacher gettea(String tid) throws Exception;
	public void uppwd(Teacher tea)throws Exception;
	public List<Teacher> getalltea() throws Exception;
	public List<Teacher> getteabyname(String tname) throws Exception;
	public void inserttea(Teacher tea) throws Exception;
	void updatetea(Teacher tea) throws Exception;
	
	//分页
		PageBean<Teacher> getfenyetea(int currPage, int pageSize)throws Exception;
		int getcount() throws Exception;
		
		//删除老师
		void deltea(String tid) throws Exception;
		
	//模糊查询
	List<Teacher> findteabyname(String name)throws Exception;
}
