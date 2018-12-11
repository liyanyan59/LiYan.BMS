package liyan.BMS.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.liyan.BMS.entity.Book;
import com.liyan.BMS.entity.User;
import liyan.BMS.service.BookService;

public class UserCheckBorrowView extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	
	//private JPanel panelTable=null;//用来保存Jtable
	private JTable table=null;
	private JPanel panelBtn=null;//放按钮
	private JButton btnBorrow=null;
	private JButton btnSearch=null;
	private JButton btnExit=null;
	//private JComboBox<String> cbType=null;
	private JLabel lbType=null;
	private JTextField tfBorrow=new JTextField("输入书籍编号");
	private JTextField tfSearch=new JTextField("根据书名查询");
	List<Book> bookList=null;
	private BookRecordInfoTableModel infoTableModel=null;
	private JButton btnSearchAll=new JButton("查询全部书籍");
	
	public UserCheckBorrowView(User user) {
		init();
		registerListener(user);
	}
	
	
	
	private void init() {
		this.setTitle("书籍查询");
		this.setSize(800, 500);
		this.setIconifiable(true);//使窗体可以最小化
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		bookList=new ArrayList<Book>();
		table=new JTable();
		//绑定模型
		refreshtable(bookList);
		
		/*panelTable=new JPanel(new BorderLayout());
		//给面板设置边框
		panelTable.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "查询信息"));
		panelTable.add(table);
		this.add(panelTable,BorderLayout.CENTER);*/
		
		//不放滚动面板显示不出表头
		JScrollPane jsp=new JScrollPane(table);
		this.add(jsp,BorderLayout.CENTER);
		
		
		
		panelBtn=new JPanel(new GridLayout(8, 1,0,30));
		panelBtn.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "查询条件"));
		this.add(panelBtn, BorderLayout.EAST);
		
		lbType=new JLabel("查询类型");
		panelBtn.add(lbType);
		//cbType=new JComboBox<String>(new String[] {"全部的书","可借的书","不可借的书"});
		//panelBtn.add(cbType);
		panelBtn.add(btnSearchAll);
		panelBtn.add(tfSearch);
		
		btnSearch=new JButton("查询");
		panelBtn.add(btnSearch);
		
		panelBtn.add(tfBorrow);
		btnBorrow=new JButton("借书");
		//btnBorrow.setEnabled(false);//默认不可用
		panelBtn.add(btnBorrow);
		panelBtn.add(new JLabel());
		panelBtn.add(new JLabel());
		btnExit=new JButton("退出");
		panelBtn.add(btnExit);
		
		this.setVisible(true);
		
		
		
		
		
	}
	
	/*
	 * 监听方法
	 * */
	private void registerListener(User user) {
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookName=tfSearch.getText();
				List<Book> bookList=BookService.checkBookByName(bookName);
				//JOptionPane.showMessageDialog(UserCheckBorrowView.this,bookList);
				refreshtable(bookList);
			}
		});
		btnSearchAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Book> bookList=BookService.checkAllBooks();
				refreshtable(bookList);
				
			}
		});
		btnBorrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookId=tfBorrow.getText();
				String userId=user.getId();
				int status=BookService.lendBook(bookId, userId);
				if(status==0) {
					JOptionPane.showMessageDialog(UserCheckBorrowView.this,"没有你要借的书!");
				}else if(status==1) {
					JOptionPane.showMessageDialog(UserCheckBorrowView.this,"对不起,该书已经被借完!");
				}else if(status==2) {
					
					JOptionPane.showMessageDialog(UserCheckBorrowView.this,"借书成功!你将获得知识的乐趣!");
					List<Book> bookList=BookService.checkAllBooks();
					refreshtable(bookList);
				}else if(status==3) {
					JOptionPane.showMessageDialog(UserCheckBorrowView.this,"对不起,借书失败,请重试!");
				}
				
			}
		});
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String bookId = table.getValueAt(row,0).toString();
				tfBorrow.setText(bookId);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		
	}
	
	
	
	
	
	
	//以下是tablemodel及显示数据的操作

	private class BookRecordInfoTableModel implements TableModel{
		
		private List<Book> bookList=null;
		public BookRecordInfoTableModel(List<Book> bookList) {
			this.bookList=bookList;
		}
		//不实现
		@Override
		public void addTableModelListener(TableModelListener l) {
			
			
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			
			return String.class;
		}
		//jtable数据的列数
		@Override
		public int getColumnCount() {
			
			return 6;
		}
		//设置列名
		@Override
		public String getColumnName(int columnIndex) {
			if(columnIndex==0) {
				return "书籍编号";
			}else if(columnIndex==1) {
				return "书籍名称";
			}else if(columnIndex==2) {
				return "书籍作者";
			}else if(columnIndex==3) {
				return "现有库存";
			}else if(columnIndex==4) {
				return "总库存";
			}else if(columnIndex==5){
				return "状态";
			}else {
				return "出错";
				}
		}
		//Jtable 显示的数据行数
		@Override
		public int getRowCount() {
			
			return bookList.size();
		}
		//获取jtable中指定行指定单元格的数据
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Book book=bookList.get(rowIndex);
			if(columnIndex==0) {
				return book.getId();
			}else if(columnIndex==1) {
				return book.getBookName();
			}else if(columnIndex==2) {
				return book.getAuthorName();
			}else if(columnIndex==3) {
				return book.getPresentStock();
			}else if(columnIndex==4) {
				return book.getTotalStock();
			}else if(columnIndex==5) {
				return book.getStatus();
			}else {
				return "error";
			}
		}
		
		//设置单元格为不可编辑
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
		//不实现
		@Override
		public void removeTableModelListener(TableModelListener l) {
			
			
		}
		//不实现
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
			
		}
		
	}
	//刷新jtable并显示数据
	private void refreshtable(List<Book> bookList) {
		infoTableModel=new BookRecordInfoTableModel(bookList);
		table.setModel(infoTableModel);
	}
	
}
