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
	private JLabel lbBookId=new JLabel("�����鼮���ɾ��:");
	private JTextField tfBookId=new JTextField();
	private JButton btnDel=new JButton("ɾ��");
	private JButton btnSearchAll=new JButton("��ѯȫ���鼮����Ϣ");
	private JLabel lbBookName=new JLabel("�����鼮���Ʋ�ѯ:");
	private JTextField tfBookName=new JTextField();
	private JButton btnSearchByBookName=new JButton("��ѯ");
	
	private BookRecordInfoTableModel infoTableModel=null;
	
	public AdminCheckView() {
		registerListener();
		this.setTitle("��ѯȫ���鼮����Ϣ��ɾ��");
		this.setIconifiable(true);//����С��
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
					JOptionPane.showMessageDialog(AdminCheckView.this,"���Ϊ��!");
				}else {
					boolean addStatus=BookService.delBook(bookId);
					if(addStatus) {
						JOptionPane.showMessageDialog(AdminCheckView.this,"ɾ���ɹ�!");
					}else {
						JOptionPane.showMessageDialog(AdminCheckView.this,"ɾ��ʧ��,������!");
					}
				}
				
			}
		});
		btnSearchByBookName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg1) {
				String bookName=tfBookName.getText();
				if(tfBookName.getText().equals("")) {
					JOptionPane.showMessageDialog(AdminCheckView.this,"����Ϊ��!");
				}else {
					List<Book> bookList=null;
					bookList=BookService.checkBookByName(bookName);
					if(bookList==null) {
						JOptionPane.showMessageDialog(AdminCheckView.this,"��û���Ȿ��!");
						
					}else {
						refreshtable(bookList);
					}
				}
				
			}
		});
	}
	
	
	
	

	//������tablemodel����ʾ���ݵĲ���
	private class BookRecordInfoTableModel implements TableModel{
		
		private List<Book> bookList=null;
		public BookRecordInfoTableModel(List<Book> bookList) {
			this.bookList=bookList;
		}
		//��ʵ��
		@Override
		public void addTableModelListener(TableModelListener l) {
			
			
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			
			return String.class;
		}
		//jtable���ݵ�����
		@Override
		public int getColumnCount() {
			
			return 6;
		}
		//��������
		@Override
		public String getColumnName(int columnIndex) {
			if(columnIndex==0) {
				return "�鼮���";
			}else if(columnIndex==1) {
				return "�鼮����";
			}else if(columnIndex==2) {
				return "�鼮����";
			}else if(columnIndex==3) {
				return "���п��";
			}else if(columnIndex==4) {
				return "�ܿ��";
			}else if(columnIndex==5){
				return "״̬";
			}else {
				return "����";
				}
		}
		//Jtable ��ʾ����������
		@Override
		public int getRowCount() {
			
			return bookList.size();
		}
		//��ȡjtable��ָ����ָ����Ԫ�������
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
		
		//���õ�Ԫ��Ϊ���ɱ༭
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
		//��ʵ��
		@Override
		public void removeTableModelListener(TableModelListener l) {
			
			
		}
		//��ʵ��
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
			
		}
		
	}
	//ˢ��jtable����ʾ����
	private void refreshtable(List<Book> bookList) {
		infoTableModel=new BookRecordInfoTableModel(bookList);
		table.setModel(infoTableModel);
	}
	
}
