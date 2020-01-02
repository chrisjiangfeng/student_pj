package com.chris.ser.dao;

import com.chris.ser.po.Studentandcurriculum;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface StudentandcurriculumDao extends Mapper<Studentandcurriculum>, MySqlMapper<Studentandcurriculum> {
	void delcur(String sid);
	void delcurbycid(int cid);
}
