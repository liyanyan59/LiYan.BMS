package com.liyan.BMS.dao;

import java.util.List;

import com.liyan.BMS.entity.Record;

public interface RecordDao {
	public Record checkRecordById(String recordId);//�鿴ָ��ID��book�軹��¼
	
	public boolean saveRecord(Record record);//����record��¼
	
	public boolean updateRecord(Record record);//����record��¼
	
	public List<Record> checkAllrecords();//��ѯ����book��¼
	
	public List<Record> checkRecordByUserId(String userId);
	//�鿴ָ���û��Ľ軹��¼
	
	public List<Record> checkRecordByBookId(String bookId);
	//��ѯָ�����ŵļ�¼
	
	public List<Record> checkRecordBybookName(String bookName);
	
}
