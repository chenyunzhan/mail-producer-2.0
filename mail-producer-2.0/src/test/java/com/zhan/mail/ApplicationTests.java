package com.zhan.mail;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.zhan.mail.entity.MstDict;
import com.zhan.mail.mapper.MstDictMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Resource(name="masterDataSource")
	private DataSource masterDataSource;
	
	@Resource(name="slaveDataSource")
	private DataSource slaveDataSource;
	
	@Autowired
	private MstDictMapper mstDictMapper;
	
	@Test
	public void contextLoads()throws Exception {
		
		Connection c1 = masterDataSource.getConnection("root", "root");
		System.err.println("c1: " + c1.getMetaData().getURL());
		Connection c2 = slaveDataSource.getConnection("root", "root");
		System.err.println("c2: " + c2.getMetaData().getURL());
	}
	
	@Test
	public void pager() throws Exception {
		PageHelper.startPage(1, 2);
		List<MstDict> list = mstDictMapper.selectAll();
		for (MstDict mstDict : list) {
			System.out.println(mstDict.getName());
		}
	}
	
	
	@Test
	public void readOnly() throws Exception {
		List<MstDict> list = this.mstDictMapper.findByStatus("1");
		for (MstDict mstDict : list) {
			System.out.println(mstDict.getName());
		}
	}

}
