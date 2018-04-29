package com.liyan.BMS.service;

import java.util.List;

import com.liyan.BMS.dao.impl.RecordDaoImpl;
import com.liyan.BMS.entity.Record;

public class RecordService {
	private static RecordDaoImpl recordDao=new RecordDaoImpl();
	
	
	public static List<Record> checkRecordByuserId(String userId) {
	
		return recordDao.checkRecordByUserId(userId);
	}

	public static Record checkRecordById(String recordId){
		return recordDao.checkRecordById(recordId);
	}
	
	public List<Record> checkRecordBybookName(String bookName) {

		return recordDao.checkRecordBybookName(bookName);
	}

	
	public List<Record> checkAllrecords() {
		return recordDao.checkAllrecords();
	}

	public List<Record> checkRecordBybookId(String bookId) {
		return recordDao.checkRecordByBookId(bookId);
	}
}
