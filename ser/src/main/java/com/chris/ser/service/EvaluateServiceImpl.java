package com.chris.ser.service;

import com.chris.ser.dao.EvaluateDao;
import com.chris.ser.po.Evaluate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("evaluateServiceImpl")
public class EvaluateServiceImpl  implements EvaluateService{
	
	@Autowired
	private EvaluateDao evadao;
	
	public void uppj(Evaluate eva) throws Exception {
		evadao.updatebyid(eva);
	}

	@Override
	public void addstuselect(Evaluate eva) throws Exception {
		evadao.addstuselect(eva);
		
	}

	@Override
	public List<Evaluate> selectcinzai(int ecid) throws Exception {
		List<Evaluate> eva = evadao.selectcunzai(ecid);
		return eva;
	}

	
       
}
