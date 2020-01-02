package com.chris.ser.service;


import com.chris.ser.bo.PageBean;
import com.chris.ser.po.Student;
import com.chris.ser.po.Studentandcurriculum;

import java.util.List;

public interface StudentService {
	public Student getstu(String id) throws Exception;
	public void uppwd(Student stu)throws Exception;
	public List<Student> getallstu() throws Exception;
	public List<Student> getstubyname(String sname) throws Exception;
	public List<Student> getstubymajor(String smajor) throws Exception;
	public void insertstu(Student stu) throws Exception;
	void updatestu(Student stu)throws Exception;
	void stuaddcur(Studentandcurriculum stucur)throws Exception;
	Studentandcurriculum findmyselect(Studentandcurriculum stucur) throws Exception;
	
	//模糊查询
	List<Student> findbyname(String name)throws Exception;
	
	//分页
	PageBean<Student> getfenyestu(int currPage, int pageSize)throws Exception;
	int getcount() throws Exception;
	void delstu(String sid)throws Exception;
}
