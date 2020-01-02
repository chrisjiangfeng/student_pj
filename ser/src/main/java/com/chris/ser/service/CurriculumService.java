package com.chris.ser.service;


import com.chris.ser.bo.PageBean;
import com.chris.ser.po.Curriculum;

import java.util.List;

public interface CurriculumService {
	Curriculum getbyid(int cid);
	void addcur(Curriculum cur);
	List<Curriculum> getcurbytid(String tid);
	List<Curriculum> getallcur();
	void deletecur(int cid);
	void querenxiugai(Curriculum curr);
	
	 PageBean<Curriculum> getfenyecur(int currPage, int pageSize)throws Exception;
 	 int getcount() throws Exception;
}
