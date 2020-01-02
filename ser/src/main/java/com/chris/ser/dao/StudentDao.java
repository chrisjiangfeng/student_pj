package com.chris.ser.dao;

import com.chris.ser.po.Student;
import com.chris.ser.po.Studentandcurriculum;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

public interface StudentDao extends Mapper<Student>, MySqlMapper<Student> {
	
	public Student findstu(String sid);
	public void uppwd(Student stu);
	public List<Student> findstubyname(String sname);
	public List<Student> findstubymajor(String smajor);
	public List<Student> findallstu();
	public void insertstu(Student stu);
	void updatestu(Student stu);
	void stuaddcur(Studentandcurriculum stucur);
	Studentandcurriculum findmyselect(Studentandcurriculum stucur);
	//分页
	List<Student> pagefindstu(Map map);
	int stutotalCount();
	void delstu(String sid);
}
