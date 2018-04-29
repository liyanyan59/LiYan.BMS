package com.liyan.BMS.entity;

import java.util.Date;

public class Record {
	private String recordId;
	private String userId;
	private String bookId;
	private Date lendTime;
	private Date returnTime;
	
	public Record() {
		
	}
	public Record(String userId, String bookId, Date lendTime, Date returnTime) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
	}
	public Record(String recordId, String userId, String bookId, Date lendTime, Date returnTime) {
		super();
		this.recordId = recordId;
		this.userId = userId;
		this.bookId = bookId;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
	}
	public String getId() {
		return recordId;
	}
	public void setId(String recordId) {
		this.recordId = recordId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public Date getLendTime() {
		return lendTime;
	}
	public void setLendTime(Date lendTime) {
		this.lendTime = lendTime;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	
}
