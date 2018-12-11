package liyan.BMS.entity;

public class Book {
	private String bookId;
	private String bookName;
	private String authorName;
	private int presentStock;
	private int totalStock;
	private int status;
	
	
	public Book() {
		
	}
	public String getId() {
		return bookId;
	}
	
	public Book(String bookName, String authorName, int presentStock, int totalStock, int status) {
		super();
		this.bookName = bookName;
		this.authorName = authorName;
		this.presentStock = presentStock;
		this.totalStock = totalStock;
		this.status = status;
	}

	public Book(String bookId, String bookName, String authorName, int presentStock, int totalStock, int status) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		this.presentStock = presentStock;
		this.totalStock = totalStock;
		this.status = status;
	}
	public void setId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getPresentStock() {
		return presentStock;
	}
	public void setPresentStock(int presentStock) {
		this.presentStock = presentStock;
	}
	public int getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
