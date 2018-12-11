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

	private static BookDao bookDao=new BookDaoImpl();;//����Book���ݷ��ʲ����
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
		Book book = bookDao.checkBookBybookId(bookId);//��ѯ
		if(book==null) {
			return 0;//û���ҵ�Ҫ���Book
		}else {
			if(book.getPresentStock()==0) {
				return 1;//�����Ѿ�����
			}else {
				boolean flag1=false;
				boolean flag2=false;
				if(book.getPresentStock()>0) {
					book.setPresentStock(book.getPresentStock()-1);//�����ֿ��      //new SimpleDateFormat("yyyy-MM-dd").format(new Date())
					flag1 = bookDao.updateBook(book);//����Book
				}
				if(flag1) {
				Record record=new Record(String.valueOf(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())),userId, book.getId(),new Date(), null);
				
				flag2 = recordDao.saveRecord(record);//��������¼
				}
				if(flag1&&flag2) {
					return 2;//����ɹ�
				}else {
					return 3;//���ʧ��
				}
			}
		}
		
	}

	public static int returnBook(String recordId) {
		Record record=recordDao.checkRecordById(recordId);
		if(record==null) {
			return 1;//���벻��ȷ
		}else {
			
			if(record.getReturnTime()==null){
			record.setReturnTime(new Date());
			boolean flag1=recordDao.updateRecord(record);//����record��
			
			Book book = bookDao.checkBookBybookId(record.getBookId());//�ҵ���Ӧ��book
			boolean flag2=false;
			if(book.getPresentStock()<book.getTotalStock()) {
				book.setPresentStock(book.getPresentStock()+1);//�����ֿ��
				flag2=bookDao.updateBook(book);//����Book
			}
			
			if(flag1&&flag2) {
				return 3;//�黹�ɹ�
			}else {
				return 4;//�黹ʧ��
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
	public boolean addBook(Book book);//���Book
	public boolean delBook(String bookId);//ɾ��Book
	public boolean modifyBook(Book book);//�޸�Book
	public List<Book> checkAllBooks();//��ѯ���е�Book��Ϣ
	public List<Book> checkBookByName(String bookName);//����book�����ֲ�ѯ
	public Book checkBookBybookId(String bookId);//����bookID��ѯ
	public int lendBook(String bookId,String userId);//��book��ź�ѧ������Book
	public int returnBook(String recordId);//���ݽ����¼ID�黹book
	public List<Book> checkBookBypStock(int presentStock);//�鿴�Լ��ܹ������
	public List<Book> checkBookByuId(String userId);//�鿴�Լ������
*/
}
