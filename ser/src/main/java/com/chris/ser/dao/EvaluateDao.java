package com.chris.ser.dao;


import com.chris.ser.po.Evaluate;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface EvaluateDao extends Mapper<Evaluate>, MySqlMapper<Evaluate> {
     public void updatebyid(Evaluate eva);
     void addstuselect(Evaluate eav);
     void delpj(String sid);
     void deleavbyecid(int cid);
     List<Evaluate> selectcunzai(int ecid);
}
