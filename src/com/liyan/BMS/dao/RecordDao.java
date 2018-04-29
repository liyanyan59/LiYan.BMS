package com.liyan.BMS.dao;

import java.util.List;

import com.liyan.BMS.entity.Record;

public interface RecordDao {
	public Record checkRecordById(String recordId);//查看指定ID的book借还记录
	
	public boolean saveRecord(Record record);//保存record记录
	
	public boolean updateRecord(Record record);//更新record记录
	
	public List<Record> checkAllrecords();//查询所有book记录
	
	public List<Record> checkRecordByUserId(String userId);
	//查看指定用户的借还记录
	
	public List<Record> checkRecordByBookId(String bookId);
	//查询指定书编号的记录
	
	public List<Record> checkRecordBybookName(String bookName);
	
}
