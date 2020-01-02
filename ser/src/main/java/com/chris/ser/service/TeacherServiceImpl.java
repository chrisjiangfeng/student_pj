package com.chris.ser.service;

import com.chris.ser.bo.PageBean;
import com.chris.ser.dao.CurriculumDao;
import com.chris.ser.dao.TeacherDao;
import com.chris.ser.po.Student;
import com.chris.ser.po.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("teacherServiceImpl")
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherDao td;
	
	@Autowired
	private CurriculumDao currdao;
	
	public Teacher gettea(String tid) throws Exception {
		Teacher tea = td.findtea(tid);
		return tea;
	}

	@Override
	public void uppwd(Teacher tea) throws Exception {
		td.uppwd(tea);	
	}

	@Override
	public List<Teacher> getalltea() throws Exception {
		List<Teacher> listalltea = td.findalltea();
		return listalltea;
	}

	@Override
	public List<Teacher> getteabyname(String tname) throws Exception {
		List<Teacher> listteabyname = td.findteabyname(tname);
		return listteabyname;
	}

	@Override
	public void inserttea(Teacher tea) throws Exception {
		td.inserttea(tea);
		
	}

	@Override
	public void updatetea(Teacher tea) throws Exception {
		td.updatetea(tea);
		
	}

	@Override
	public PageBean<Teacher> getfenyetea(int currPage, int pageSize) throws Exception {
		int count=td.teatotalCount();//总条数
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("start", (currPage-1)*pageSize);
		map.put("end", pageSize);
		List<Student>  list = td.pagefindtea(map);
		PageBean pagebean=new PageBean<>(list,currPage,pageSize,count);
		return pagebean;
	}

	@Override
	public int getcount() throws Exception {
		int stutotalCount = td.teatotalCount();
		return stutotalCount;
	}

	
	//删除老师
	@Override
	public void deltea(String tid) throws Exception {
		currdao.updatecurr(tid);
		td.deltea(tid);
	}

	@Override
	public List<Teacher> findteabyname(String name) throws Exception {
		List<Teacher> teacher = td.findteabyname(name);
		return teacher;
	}

}
