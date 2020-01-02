package com.chris.ser.service;

import com.chris.ser.bo.PageBean;
import com.chris.ser.dao.ManyTableDao;
import com.chris.ser.po.Curriculum;
import com.chris.ser.po.Evaluate;
import com.chris.ser.pojo.Details;
import com.chris.ser.pojo.ManyTable;
import com.chris.ser.pojo.Tea_Curri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("manyTableServiceImpl")
public class ManyTableServiceImpl implements ManyTableService{

	
	@Autowired
	private ManyTableDao mtd;
	
	
	public List<ManyTable> getall(String sid) throws Exception {
		List<ManyTable> listpj = mtd.findall(sid);
		return listpj;
	}


	@Override
	public List<Curriculum> getcur(String tid) throws Exception {
		List<Curriculum> listcur = mtd.findcurbytid(tid);
		return listcur;
	}

	//查询评价
	@Override
	public List<Evaluate> geteva(int cid) throws Exception {
		List<Evaluate> listeva = mtd.findbycid(cid);
		return listeva;
	}


	@Override
	public List<Tea_Curri> gettea_curri() throws Exception {
		List<Tea_Curri> tea_curri = mtd.findalltea_curri();
		return tea_curri;
	}


	@Override
	public List<Details> getdetails(int ecid) throws Exception {
		List<Details> listdetails = mtd.finddetails(ecid);
		return listdetails;
	}


	@Override
	public List<ManyTable> getstu_curbystuid(String ssid) throws Exception {
		List<ManyTable> liststu_curbystuid = mtd.findstu_curbystuid(ssid);
		return liststu_curbystuid;
	}

	
	//分页方式
	public int getfenyecount(Map map)throws Exception{
		
		return mtd.fenyecount(map);
	}
	
	public PageBean<Evaluate> getfenye(Map map, int currPage, int pageSize)throws Exception{
		int count = mtd.fenyecount(map);
		List<Evaluate> list = mtd.fenye(map);
		PageBean<Evaluate> pb=new PageBean<Evaluate>(list,currPage,pageSize,count);
		return pb;
	}
	
}
