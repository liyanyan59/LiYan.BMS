package liyan.BMS.dao.impl;

import java.util.ArrayList;
import java.util.List;
import liyan.BMS.dao.impl.BaseDao;
import liyan.BMS.dao.BookDao;
import liyan.BMS.entity.Book;

public class BookDaoImpl extends BaseDao implements BookDao {

	@Override
	public boolean saveBook(Book book) {
		String sql="insert into system_db.book(bookId,bookName,authorName,presentStock,totalStock,status)values(?,?,?,?,?,?)";
		List<Object>params=new ArrayList<Object>();
		params.add(book.getId());
		params.add(book.getBookName());
		params.add(book.getAuthorName());
		params.add(book.getPresentStock());
		params.add(book.getTotalStock());
		params.add(book.getStatus());
		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean updateBook(Book book) {
		String sql="update system_db.book set bookName=?,authorName=?,presentStock=?,totalStock=?,status=? where BookId=?";
		List<Object>params=new ArrayList<Object>();
		params.add(book.getBookName());
		params.add(book.getAuthorName());
		params.add(book.getPresentStock());
		params.add(book.getTotalStock());
		params.add(book.getStatus());
		params.add(book.getId());
		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean delBook(String bookId) {
		String sql="delete from system_db.book where bookId=?";
		List<Object>params=new ArrayList<Object>();
		params.add(bookId);
		return this.operateUpdate(sql, params);
	}

	@Override
	public List<Book> checkBooks() {
		String sql="select bookId,bookName,authorName,presentStock,totalStock,status from system_db.book";
		List<Book> bList=null;
		try {
			bList=this.operateQuery(sql, null, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bList;
	}

	@Override
	public List<Book> checkBookByName(String bookName) {
		String sql="select * from system_db.book where bookName=?";
		List<Book> bList=null;
		List<Object>params=new ArrayList<Object>();
		params.add(bookName);
		try {
			bList=this.operateQuery(sql, params, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bList;
	}

	@Override
	public List<Book> checkBookBystatus(int status) {
		String sql="select * from system_db.book where status=?";
		List<Book> bList=null;
		List<Object>params=new ArrayList<Object>();
		params.add(status);
		try {
			bList=this.operateQuery(sql, params, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bList;
	}

	@Override
	public Book checkBookBybookId(String bookId) {
		// TODO Auto-generated method stub
		String sql="select * from system_db.book where bookId=?";
		List<Book> bList=null;
		List<Object>params=new ArrayList<Object>();
		params.add(bookId);
		try {
			bList=this.operateQuery(sql, params, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(bList.size()>0) {
			return bList.get(0);
		}
		return null;
	}

	@Override
	public List<Book> checkBookByuserId(String userId) {
		String sql="SELECT * FROM system_db.book where userId=?";
		List<Book> bList=null;
		List<Object>params=new ArrayList<Object>();
		params.add(userId);
		try {
			bList=this.operateQuery(sql, params, Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bList;
	}

}
