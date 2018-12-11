package liyan.BMS.dao;

import java.util.List;

import liyan.BMS.entity.Book;

public interface BookDao {
	public boolean saveBook(Book book);//添加book
	public boolean updateBook(Book book);//更新book
	public boolean delBook(String bookId);//删除指定book
	public List<Book> checkBooks();//查询所有books
	public List<Book> checkBookByName(String bookName);//查找指定名字的book
	//public List<book> checkSortBookBy?(int index,int number);//查找指定位置、指定个数的book（附加）
	public List<Book> checkBookBystatus(int status);//根据状态查询book
	public List<Book> checkBookByuserId(String userId);
	public Book checkBookBybookId(String bookId);
	
}
