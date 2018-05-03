package com.zhan.mail.mapper;

import java.util.List;

import com.zhan.mail.config.database.BaseMapper;
import com.zhan.mail.entity.MstDict;

public interface MstDictMapper extends BaseMapper<MstDict> {

	List<MstDict> findByStatus(String status);
	
	

}