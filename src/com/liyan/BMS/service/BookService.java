package liyan.BMS.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.liyan.BMS.dao.BookDao;
import com.liyan.BMS.dao.RecordDao;
import com.liyan.BMS.dao.impl.BookDaoImpl;
import liyan.BMS.dao.impl.RecordDaoImpl;
import com.liyan.BMS.entity.Book;
import com.liyan.BMS.entity.Record;

public class BookService {

	private static BookDao bookDao=new BookDaoImpl();;//创建Book数据访问层对象
	private static RecordDao recordDao=new RecordDaoImpl();;
	
	public static boolean addBook(Book book) {
        
		return bookDao.saveBook(book);
	}

	
	public static boolean delBook(String bookId) {
		return bookDao.delBook(bookId);
	}

	
	public static boolean modifyBook(Book book) {
		return bookDao.updateBook(book);
	}

	
	public static List<Book> checkAllBooks() {
		return bookDao.checkBooks();
	}

	public static List<Book> checkBookByName(String bookName) {
		return bookDao.checkBookByName(bookName);
	}

	public static Book checkBookBybookId(String bookId) {
		return bookDao.checkBookBybookId(bookId);
	}

	public static int lendBook(String bookId, String userId) {
		Book book = bookDao.checkBookBybookId(bookId);//查询
		if(book==null) {
			return 0;//没有找到要借的Book
		}else {
			if(book.getPresentStock()==0) {
				return 1;//该书已经借完
			}else {
				boolean flag1=false;
				boolean flag2=false;
				if(book.getPresentStock()>0) {
					book.setPresentStock(book.getPresentStock()-1);//更新现库存      //new SimpleDateFormat("yyyy-MM-dd").format(new Date())
					flag1 = bookDao.updateBook(book);//更新Book
				}
				if(flag1) {
				Record record=new Record(String.valueOf(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())),userId, book.getId(),new Date(), null);
				
				flag2 = recordDao.saveRecord(record);//插入借书记录
				}
				if(flag1&&flag2) {
					return 2;//借出成功
				}else {
					return 3;//借出失败
				}
			}
		}
		
	}

	public static int returnBook(String recordId) {
		Record record=recordDao.checkRecordById(recordId);
		if(record==null) {
			return 1;//输入不正确
		}else {
			
			if(record.getReturnTime()==null){
			record.setReturnTime(new Date());
			boolean flag1=recordDao.updateRecord(record);//更新record表
			
			Book book = bookDao.checkBookBybookId(record.getBookId());//找到对应的book
			boolean flag2=false;
			if(book.getPresentStock()<book.getTotalStock()) {
				book.setPresentStock(book.getPresentStock()+1);//更新现库存
				flag2=bookDao.updateBook(book);//更新Book
			}
			
			if(flag1&&flag2) {
				return 3;//归还成功
			}else {
				return 4;//归还失败
			}
		}else {
			if(record.getReturnTime().after(record.getLendTime())) {
				return 2;
			}
		}
		}
		return 0;
		
	}

	public static List<Book> checkBookBypStock(int presentStock) {
		 
		return bookDao.checkBookBystatus(presentStock);
	}

	public static List<Book> checkBookByuId(String userId) {
	
		return bookDao.checkBookByuserId(userId);
	}
	
	

	
	
	/*
	public boolean addBook(Book book);//添加Book
	public boolean delBook(String bookId);//删除Book
	public boolean modifyBook(Book book);//修改Book
	public List<Book> checkAllBooks();//查询所有的Book信息
	public List<Book> checkBookByName(String bookName);//根据book的名字查询
	public Book checkBookBybookId(String bookId);//根据bookID查询
	public int lendBook(String bookId,String userId);//按book编号和学号来借Book
	public int returnBook(String recordId);//根据借出记录ID归还book
	public List<Book> checkBookBypStock(int presentStock);//查看自己能够借的书
	public List<Book> checkBookByuId(String userId);//查看自己借的书
*/
}
