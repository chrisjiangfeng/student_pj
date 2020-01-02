package com.chris.ser.service;

import com.chris.ser.bo.PageBean;
import com.chris.ser.dao.EvaluateDao;
import com.chris.ser.dao.StudentDao;
import com.chris.ser.dao.StudentandcurriculumDao;
import com.chris.ser.po.Student;
import com.chris.ser.po.Studentandcurriculum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("studentServiceImpl")
public class StudentServiceImpl  implements StudentService{
	@Autowired
	private StudentDao std;
	
	@Autowired
	private StudentandcurriculumDao stuandcurdao;
	@Autowired
	private EvaluateDao evadao;
	
	public Student getstu(String sid) throws Exception {
		Student stu = std.findstu(sid);
		return stu;
	}

	@Override
	public void uppwd(Student stu) throws Exception {
		std.uppwd(stu);	
	}

	@Override
	public List<Student> getallstu() throws Exception {
		List<Student> listallstu = std.findallstu();
		return listallstu;
	}

	@Override
	public List<Student> getstubyname(String sname) throws Exception {
		List<Student> liststubyname = std.findstubyname(sname);
		return liststubyname;
	}

	@Override
	public List<Student> getstubymajor(String smajor) throws Exception {
		List<Student> liststubymajor = std.findstubymajor(smajor);
		return liststubymajor;
	}

	@Override
	public void insertstu(Student stu) throws Exception {
		 std.insertstu(stu);	
	}

	@Override
	public void updatestu(Student stu) throws Exception {
		std.updatestu(stu);
	}

	@Override
	public void stuaddcur(Studentandcurriculum stucur) throws Exception {
		std.stuaddcur(stucur);	
	}

	@Override
	public Studentandcurriculum findmyselect(Studentandcurriculum stucur) throws Exception {
		Studentandcurriculum myselect = std.findmyselect(stucur);
		return myselect;
	}

	@Override
	public PageBean<Student> getfenyestu(int currPage, int pageSize) throws Exception {
		int count=std.stutotalCount();//总条数
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("start", (currPage-1)*pageSize);
		map.put("end", pageSize);
		List<Student>  list = std.pagefindstu(map);
		PageBean pagebean=new PageBean<>(list,currPage,pageSize,count);
		return pagebean;
	}

	@Override
	public int getcount() throws Exception {
		int stutotalCount = std.stutotalCount();
		return stutotalCount;
	}

	
	
	//删除学生
	@Override
	public void delstu(String sid) throws Exception {
		evadao.delpj(sid);
		stuandcurdao.delcur(sid);
		std.delstu(sid);
	}

	
	//模糊查询
	@Override
	public List<Student> findbyname(String name) throws Exception {
		List<Student> student = std.findstubyname(name);
		return student;
	}

}
