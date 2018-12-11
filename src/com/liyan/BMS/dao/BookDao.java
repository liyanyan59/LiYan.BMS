package liyan.BMS.dao;

import java.util.List;

import liyan.BMS.entity.Book;

public interface BookDao {
	public boolean saveBook(Book book);//���book
	public boolean updateBook(Book book);//����book
	public boolean delBook(String bookId);//ɾ��ָ��book
	public List<Book> checkBooks();//��ѯ����books
	public List<Book> checkBookByName(String bookName);//����ָ�����ֵ�book
	//public List<book> checkSortBookBy?(int index,int number);//����ָ��λ�á�ָ��������book�����ӣ�
	public List<Book> checkBookBystatus(int status);//����״̬��ѯbook
	public List<Book> checkBookByuserId(String userId);
	public Book checkBookBybookId(String bookId);
	
}
