package liyan.BMS.dao.impl;

import com.liyan.BMS.dao.impl.BaseDao;
import java.util.ArrayList;
import java.util.List;

import com.liyan.BMS.dao.RecordDao;

import com.liyan.BMS.entity.Record;

public class RecordDaoImpl extends BaseDao implements RecordDao {
 
	@Override
	public Record checkRecordById(String recordId) {
		String sql="select recordId,userId,bookId,lendTime,returnTime from system_db.record where recordId=?";
		List<Record> rList=null;
		List<Object>params=new ArrayList<Object>();
		params.add(recordId);
		try {
			rList=this.operateQuery(sql, params, Record.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rList.size()>0) {
			return rList.get(0);
		}
		return null;
	}

	@Override
	public boolean saveRecord(Record record) {
		String sql="insert into system_db.record(recordId,userId,bookId,lendTime,returnTime)values(?,?,?,?,?)";
		List<Object>params=new ArrayList<Object>();
		params.add(record.getId());
		params.add(record.getUserId());
		params.add(record.getBookId());
		params.add(record.getLendTime());
		params.add(record.getReturnTime());
		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean updateRecord(Record record) {
		String sql="update system_db.record set userId=?,bookId=?,lendTime=?,returnTime=? where recordId=?";
		List<Object>params=new ArrayList<Object>();
		params.add(record.getUserId());
		params.add(record.getBookId());
		params.add(record.getLendTime());
		params.add(record.getReturnTime());
		params.add(record.getId());
		return this.operateUpdate(sql, params);
	}

	@Override
	public List<Record> checkAllrecords() {
		String sql="select recordId,userId,bookId,lendTime,returnTime from system_db.record";
		List<Record> rList=null;
		try {
			rList=this.operateQuery(sql, null, Record.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rList;
	}

	@Override
	public List<Record> checkRecordByUserId(String userId) {
		String sql="SELECT * FROM system_db.record where userId=?";
		List<Record> rList=null;
		List<Object>params=new ArrayList<Object>();
		params.add(userId);
		try {
			rList=this.operateQuery(sql, params, Record.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rList;
	}

	@Override
	public List<Record> checkRecordByBookId(String bookId) {
		String sql="select * from system_db.record where bookId=?";
		List<Record> rList=null;
		List<Object>params=new ArrayList<Object>();
		params.add(bookId);
		try {
			rList=this.operateQuery(sql, params, Record.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rList;
	}

	@Override
	public List<Record> checkRecordBybookName(String bookName) {
		String sql="select * from system_db.record where bookName=?";
		List<Record> rList=null;
		List<Object>params=new ArrayList<Object>();
		params.add(bookName);
		try {
			rList=this.operateQuery(sql, params, Record.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rList;
	}

}
