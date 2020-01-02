package com.chris.ser.service;


import com.chris.ser.bo.PageBean;
import com.chris.ser.po.Curriculum;
import com.chris.ser.po.Evaluate;
import com.chris.ser.pojo.Details;
import com.chris.ser.pojo.ManyTable;
import com.chris.ser.pojo.Tea_Curri;

import java.util.List;
import java.util.Map;

public interface ManyTableService {
	
	public List<ManyTable> getall(String sid) throws Exception;
	
	public List<Curriculum> getcur(String tid) throws Exception;
	
	public List<Evaluate> geteva(int cid) throws Exception;
	
	public List<Tea_Curri> gettea_curri() throws Exception;
	
	public List<Details> getdetails(int ecid) throws Exception;
	List<ManyTable> getstu_curbystuid(String ssid) throws Exception;
	public int getfenyecount(Map map)throws Exception;
	public PageBean<Evaluate> getfenye(Map map, int currPage, int pageSize)throws Exception;
} 
