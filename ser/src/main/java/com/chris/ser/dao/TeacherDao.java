package com.chris.ser.dao;

import com.chris.ser.po.Student;
import com.chris.ser.po.Teacher;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


import java.util.List;
import java.util.Map;

public interface TeacherDao extends Mapper<Teacher>, MySqlMapper<Teacher> {
	public Teacher findtea(String tid);
	public void uppwd(Teacher tea);
	public List<Teacher> findalltea();
	public List<Teacher> findteabyname(String tname);
	public void inserttea(Teacher tea);
	void updatetea(Teacher tea);
	
	//分页
		List<Student> pagefindtea(Map map);
		int teatotalCount();
		
		
		//删除老师
		void deltea(String tid);
}	
