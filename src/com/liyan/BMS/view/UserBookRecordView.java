package com.liyan.BMS.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.liyan.BMS.entity.Record;
import com.liyan.BMS.entity.User;
import com.liyan.BMS.service.BookService;
import com.liyan.BMS.service.RecordService;

public class UserBookRecordView extends JInternalFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JPanel panelTable=null;//用来保存Jtable
	private JTable table=null;
	private JPanel panelBtn=null;//放按钮
	private JButton btnSearch=null;
	private JButton btnExit=null;
	//private JComboBox<String> cbType=null;
	private JLabel lbType=null;
	private JTextField tfCondition=new JTextField("记录编号");
	private JButton btCondition = new JButton("还书");
	private List<Record> recordList=null;
	private BookRecordInfoTableModel infoTableModel=null;
	
	
	public UserBookRecordView(User user) {
		init();
		registerListener(user);
	}
	
	private void init() {
		this.setTitle("书籍记录查询");
		this.setSize(800, 500);
		this.setVisible(true);//使窗体可见
		this.setIconifiable(true);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//设为可关闭
		recordList = new ArrayList<Record>();
		table=new JTable();
		//panelTable=new JPanel(new BorderLayout());
		table.setModel(new BookRecordInfoTableModel(recordList));
		//panelTable.setBorder(BorderFactory.createTitledBorder(
		//		BorderFactory.createEtchedBorder(null, null), "查询信息"));

		//让jtable绑定数据模型呈现数据
		//refreshtable(recordList);
		
		//panelTable.add(table);//放在contentpane中不显示表头
		JScrollPane jsp=new JScrollPane(table);
		this.add(jsp,BorderLayout.CENTER);

		
		panelBtn=new JPanel(new GridLayout(9,1,10,20));
		panelBtn.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "查询条件"));
		this.add(panelBtn, BorderLayout.EAST);
		
		lbType=new JLabel("查询类型");
		panelBtn.add(lbType);
		//cbType=new JComboBox<String>(new String[] {"我自己借的书","不可借的书","可以借的书"});
		//panelBtn.add(cbType);
		btnSearch=new JButton("查询自己所借的书");
		panelBtn.add(btnSearch);
		panelBtn.add(tfCondition);
		panelBtn.add(btCondition);

		panelBtn.add(new JLabel());
		panelBtn.add(new JLabel());
		panelBtn.add(new JLabel());
		btnExit=new JButton("退出");
		panelBtn.add(btnExit);
	    
		
		
	}
	
	private void registerListener(User user) {
		
		String userId=user.getId().trim();
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//JOptionPane.showMessageDialog(UserBookRecordView.this,userId);
				List<Record> recordList=RecordService.checkRecordByuserId(userId);
				//JOptionPane.showMessageDialog(UserBookRecordView.this,recordList);
				refreshtable(recordList);
			}
		});
		
		
		btCondition.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String recordId=tfCondition.getText();
				Record record=RecordService.checkRecordById(recordId);
				Calendar a=Calendar.getInstance();
				Calendar b=Calendar.getInstance();
				a.setTime(new Date());
				b.setTime(record.getLendTime());
				long now=a.getTimeInMillis();
				long lent=b.getTimeInMillis();
				
				long min=(now-lent)/(1000*60);
				if(min>10) {
					JOptionPane.showMessageDialog(UserBookRecordView.this,"超过归还时间,请联系管理员!");
					return;
				}else {
					int status=BookService.returnBook(recordId);
					if(status==1) {
						JOptionPane.showMessageDialog(UserBookRecordView.this,"输入不正确,请重新输入!");
					}else if(status==2) {
						JOptionPane.showMessageDialog(UserBookRecordView.this,"你已经换过这本书,请勿重复操作!");
					}else if(status==3) {
						JOptionPane.showMessageDialog(UserBookRecordView.this,"还书成功!你的知识又提升一次!");
					}else if(status==4) {
						JOptionPane.showMessageDialog(UserBookRecordView.this,"还书失败!请重试!");
					}
				}
			}
		});
	}
	
	private class BookRecordInfoTableModel implements TableModel{
		
		private List<Record> recordList=null;
		public BookRecordInfoTableModel(List<Record> recordList) {
			this.recordList=recordList;
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
			
			return 5;
		}
		//设置列名
		@Override
		public String getColumnName(int columnIndex) {
			if(columnIndex==0) {
				return "记录编号";
			}else if(columnIndex==1) {
				return "用户编号";
			}else if(columnIndex==2) {
				return "书籍编号";
			}else if(columnIndex==3) {
				return "借出时间";
			}else if(columnIndex==4) {
				return "归还时间";
			}else {
				return "出错";
				}
		}
		//Jtable 显示的数据行数
		@Override
		public int getRowCount() {
			
			return recordList.size();
		}
		//获取jtable中指定行指定单元格的数据
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Record record=recordList.get(rowIndex);
			if(columnIndex==0) {
				return record.getId();
			}else if(columnIndex==1) {
				return record.getUserId();
			}else if(columnIndex==2) {
				return record.getBookId();
			}else if(columnIndex==3) {
				return record.getLendTime();
			}else if(columnIndex==4) {
				return record.getReturnTime();
			}else {
			return "error";}
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
	private void refreshtable(List<Record> recordList) {
		infoTableModel=new BookRecordInfoTableModel(recordList);
		table.setModel(infoTableModel);
	}
	
}
