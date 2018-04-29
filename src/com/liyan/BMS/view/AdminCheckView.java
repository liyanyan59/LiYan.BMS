package com.liyan.BMS.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
import com.liyan.BMS.service.BookService;

public class AdminCheckView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable table=new JTable();
	List<Book> bookList=new ArrayList<Book>();
	private JPanel panelBtn=new JPanel();
	private JLabel lbBookId=new JLabel("根据书籍编号删除:");
	private JTextField tfBookId=new JTextField();
	private JButton btnDel=new JButton("删除");
	private JButton btnSearchAll=new JButton("查询全部书籍的信息");
	private JLabel lbBookName=new JLabel("根据书籍名称查询:");
	private JTextField tfBookName=new JTextField();
	private JButton btnSearchByBookName=new JButton("查询");
	
	private BookRecordInfoTableModel infoTableModel=null;
	
	public AdminCheckView() {
		registerListener();
		this.setTitle("查询全部书籍的信息及删除");
		this.setIconifiable(true);//可最小化
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setBounds(2,2,830,530);
		
		//add(table);
		refreshtable(bookList);
		JScrollPane jsp=new JScrollPane(table);
		add(jsp,BorderLayout.CENTER);
		panelBtn.setLayout(new GridLayout(10, 1));
		panelBtn.add(btnSearchAll);
		panelBtn.add(new JLabel());
		panelBtn.add(lbBookName);
		panelBtn.add(tfBookName);
		panelBtn.add(btnSearchByBookName);
		panelBtn.add(new JLabel());
		panelBtn.add(lbBookId);
		panelBtn.add(tfBookId);
		panelBtn.add(btnDel);
		add(panelBtn,BorderLayout.EAST);
		
		
		
	}
	
	
	private void registerListener() {
		btnSearchAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Book> bookList=BookService.checkAllBooks();
				refreshtable(bookList);
				
			}
		});
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookId=tfBookId.getText();
				if(tfBookId.getText().equals("")) {
					JOptionPane.showMessageDialog(AdminCheckView.this,"编号为空!");
				}else {
					boolean addStatus=BookService.delBook(bookId);
					if(addStatus) {
						JOptionPane.showMessageDialog(AdminCheckView.this,"删除成功!");
					}else {
						JOptionPane.showMessageDialog(AdminCheckView.this,"删除失败,请重试!");
					}
				}
				
			}
		});
		btnSearchByBookName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg1) {
				String bookName=tfBookName.getText();
				if(tfBookName.getText().equals("")) {
					JOptionPane.showMessageDialog(AdminCheckView.this,"输入为空!");
				}else {
					List<Book> bookList=null;
					bookList=BookService.checkBookByName(bookName);
					if(bookList==null) {
						JOptionPane.showMessageDialog(AdminCheckView.this,"并没有这本书!");
						
					}else {
						refreshtable(bookList);
					}
				}
				
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
