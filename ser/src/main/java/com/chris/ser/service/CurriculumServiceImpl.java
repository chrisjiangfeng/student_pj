package com.chris.ser.service;


import com.chris.ser.bo.PageBean;
import com.chris.ser.dao.CurriculumDao;
import com.chris.ser.dao.EvaluateDao;
import com.chris.ser.dao.StudentandcurriculumDao;
import com.chris.ser.po.Curriculum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("curriculumServiceImpl")
public class CurriculumServiceImpl implements CurriculumService {

	@Autowired
	private CurriculumDao curdao;
	
	@Autowired
	private EvaluateDao evadao;
	
	@Autowired
	private StudentandcurriculumDao stucurdao;
	
	
	public Curriculum getbyid(int cid) {
		Curriculum cur = curdao.findbyid(cid);
		return cur;
	}

	@Override
	public void addcur(Curriculum cur) {
		curdao.insertcur(cur);
		
	}

	@Override
	public List<Curriculum> getcurbytid(String tid) {
		List<Curriculum> listcurbytid = curdao.findcurbytid(tid);
		return listcurbytid;
	}

	@Override
	public List<Curriculum> getallcur() {
		List<Curriculum> listallcur = curdao.findall();
		return listallcur;
	}

	
	//删除课程
	@Override
	public void deletecur(int cid) {
		evadao.deleavbyecid(cid);
		stucurdao.delcurbycid(cid);
		curdao.deletecur(cid);
	}

	//确认修改课程
	@Override
	public void querenxiugai(Curriculum curr) {
		
		curdao.querenxiugai(curr);
	}

	
	//分页
	@Override
	public PageBean<Curriculum> getfenyecur(int currPage, int pageSize) throws Exception {
		int count=curdao.curtotalCount();//总条数
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("start", (currPage-1)*pageSize);
		map.put("end", pageSize);
		List<Curriculum>  list = curdao.pagefindcur(map);
		PageBean pagebean=new PageBean<>(list,currPage,pageSize,count);
		return pagebean;
	}

	@Override
	public int getcount() throws Exception {
		int curtotalCount = curdao.curtotalCount();
		return curtotalCount;
	}

}
