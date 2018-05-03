package com.zhan.mail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhan.mail.config.database.ReadOnlyConnection;
import com.zhan.mail.entity.MstDict;
import com.zhan.mail.mapper.MstDictMapper;

@Service
public class MstDictService {
	
	@Autowired
	private MstDictMapper mstDictMapper;
	
	
	@ReadOnlyConnection
	public List<MstDict> findByStatus (String status) {
		return this.mstDictMapper.findByStatus(status);
	}

}
