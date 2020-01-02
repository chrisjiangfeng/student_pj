package com.chris.ser.service;


import com.chris.ser.po.Evaluate;

import java.util.List;

public interface EvaluateService {
     public void uppj(Evaluate eva) throws Exception;
     void addstuselect(Evaluate eva) throws Exception;
     List<Evaluate> selectcinzai(int ecid)throws Exception;
     
    
}
