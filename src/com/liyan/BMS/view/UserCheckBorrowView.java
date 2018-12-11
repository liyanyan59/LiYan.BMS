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

	
	//private JPanel panelTable=null;//��������Jtable
	private JTable table=null;
	private JPanel panelBtn=null;//�Ű�ť
	private JButton btnBorrow=null;
	private JButton btnSearch=null;
	private JButton btnExit=null;
	//private JComboBox<String> cbType=null;
	private JLabel lbType=null;
	private JTextField tfBorrow=new JTextField("�����鼮���");
	private JTextField tfSearch=new JTextField("����������ѯ");
	List<Book> bookList=null;
	private BookRecordInfoTableModel infoTableModel=null;
	private JButton btnSearchAll=new JButton("��ѯȫ���鼮");
	
	public UserCheckBorrowView(User user) {
		init();
		registerListener(user);
	}
	
	
	
	private void init() {
		this.setTitle("�鼮��ѯ");
		this.setSize(800, 500);
		this.setIconifiable(true);//ʹ���������С��
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		bookList=new ArrayList<Book>();
		table=new JTable();
		//��ģ��
		refreshtable(bookList);
		
		/*panelTable=new JPanel(new BorderLayout());
		//��������ñ߿�
		panelTable.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "��ѯ��Ϣ"));
		panelTable.add(table);
		this.add(panelTable,BorderLayout.CENTER);*/
		
		//���Ź��������ʾ������ͷ
		JScrollPane jsp=new JScrollPane(table);
		this.add(jsp,BorderLayout.CENTER);
		
		
		
		panelBtn=new JPanel(new GridLayout(8, 1,0,30));
		panelBtn.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "��ѯ����"));
		this.add(panelBtn, BorderLayout.EAST);
		
		lbType=new JLabel("��ѯ����");
		panelBtn.add(lbType);
		//cbType=new JComboBox<String>(new String[] {"ȫ������","�ɽ����","���ɽ����"});
		//panelBtn.add(cbType);
		panelBtn.add(btnSearchAll);
		panelBtn.add(tfSearch);
		
		btnSearch=new JButton("��ѯ");
		panelBtn.add(btnSearch);
		
		panelBtn.add(tfBorrow);
		btnBorrow=new JButton("����");
		//btnBorrow.setEnabled(false);//Ĭ�ϲ�����
		panelBtn.add(btnBorrow);
		panelBtn.add(new JLabel());
		panelBtn.add(new JLabel());
		btnExit=new JButton("�˳�");
		panelBtn.add(btnExit);
		
		this.setVisible(true);
		
		
		
		
		
	}
	
	/*
	 * ��������
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
					JOptionPane.showMessageDialog(UserCheckBorrowView.this,"û����Ҫ�����!");
				}else if(status==1) {
					JOptionPane.showMessageDialog(UserCheckBorrowView.this,"�Բ���,�����Ѿ�������!");
				}else if(status==2) {
					
					JOptionPane.showMessageDialog(UserCheckBorrowView.this,"����ɹ�!�㽫���֪ʶ����Ȥ!");
					List<Book> bookList=BookService.checkAllBooks();
					refreshtable(bookList);
				}else if(status==3) {
					JOptionPane.showMessageDialog(UserCheckBorrowView.this,"�Բ���,����ʧ��,������!");
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
