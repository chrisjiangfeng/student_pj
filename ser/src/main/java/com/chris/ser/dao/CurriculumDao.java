package com.chris.ser.dao;


import com.chris.ser.po.Curriculum;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

public interface CurriculumDao extends Mapper<Curriculum>, MySqlMapper<Curriculum> {
	 Curriculum findbyid(int cid);
	 void insertcur(Curriculum cur);
	 List<Curriculum> findcurbytid(String tid);
	 List<Curriculum> findall();
	 void updatecurr(String tid);
	 
	 void updatenull(int cid);
	 void deletecur(int cid);
	 void querenxiugai(Curriculum curr);
	//分页
	 List<Curriculum> pagefindcur(Map map);
	 int curtotalCount();
	
}
