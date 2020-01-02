package com.chris.ser.dao;


import com.chris.ser.po.Curriculum;
import com.chris.ser.po.Evaluate;
import com.chris.ser.pojo.Details;
import com.chris.ser.pojo.ManyTable;
import com.chris.ser.pojo.Tea_Curri;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

public 	interface ManyTableDao extends Mapper<ManyTable>, MySqlMapper<ManyTable> {
	public List<ManyTable> findall(String sid);
	public List<Curriculum> findcurbytid(String tid);
	public List<Evaluate> findbycid(int cid);
	public List<Tea_Curri> findalltea_curri();
	public List<Details> finddetails(int ecid);
	List<ManyTable> findstu_curbystuid(String ssid);
	//分页
	List<Evaluate> fenye(Map map);
	int fenyecount(Map map);
}
