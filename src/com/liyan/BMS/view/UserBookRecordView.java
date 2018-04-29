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
	//private JPanel panelTable=null;//��������Jtable
	private JTable table=null;
	private JPanel panelBtn=null;//�Ű�ť
	private JButton btnSearch=null;
	private JButton btnExit=null;
	//private JComboBox<String> cbType=null;
	private JLabel lbType=null;
	private JTextField tfCondition=new JTextField("��¼���");
	private JButton btCondition = new JButton("����");
	private List<Record> recordList=null;
	private BookRecordInfoTableModel infoTableModel=null;
	
	
	public UserBookRecordView(User user) {
		init();
		registerListener(user);
	}
	
	private void init() {
		this.setTitle("�鼮��¼��ѯ");
		this.setSize(800, 500);
		this.setVisible(true);//ʹ����ɼ�
		this.setIconifiable(true);
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//��Ϊ�ɹر�
		recordList = new ArrayList<Record>();
		table=new JTable();
		//panelTable=new JPanel(new BorderLayout());
		table.setModel(new BookRecordInfoTableModel(recordList));
		//panelTable.setBorder(BorderFactory.createTitledBorder(
		//		BorderFactory.createEtchedBorder(null, null), "��ѯ��Ϣ"));

		//��jtable������ģ�ͳ�������
		//refreshtable(recordList);
		
		//panelTable.add(table);//����contentpane�в���ʾ��ͷ
		JScrollPane jsp=new JScrollPane(table);
		this.add(jsp,BorderLayout.CENTER);

		
		panelBtn=new JPanel(new GridLayout(9,1,10,20));
		panelBtn.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(null, null), "��ѯ����"));
		this.add(panelBtn, BorderLayout.EAST);
		
		lbType=new JLabel("��ѯ����");
		panelBtn.add(lbType);
		//cbType=new JComboBox<String>(new String[] {"���Լ������","���ɽ����","���Խ����"});
		//panelBtn.add(cbType);
		btnSearch=new JButton("��ѯ�Լ��������");
		panelBtn.add(btnSearch);
		panelBtn.add(tfCondition);
		panelBtn.add(btCondition);

		panelBtn.add(new JLabel());
		panelBtn.add(new JLabel());
		panelBtn.add(new JLabel());
		btnExit=new JButton("�˳�");
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
					JOptionPane.showMessageDialog(UserBookRecordView.this,"�����黹ʱ��,����ϵ����Ա!");
					return;
				}else {
					int status=BookService.returnBook(recordId);
					if(status==1) {
						JOptionPane.showMessageDialog(UserBookRecordView.this,"���벻��ȷ,����������!");
					}else if(status==2) {
						JOptionPane.showMessageDialog(UserBookRecordView.this,"���Ѿ������Ȿ��,�����ظ�����!");
					}else if(status==3) {
						JOptionPane.showMessageDialog(UserBookRecordView.this,"����ɹ�!���֪ʶ������һ��!");
					}else if(status==4) {
						JOptionPane.showMessageDialog(UserBookRecordView.this,"����ʧ��!������!");
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
			
			return 5;
		}
		//��������
		@Override
		public String getColumnName(int columnIndex) {
			if(columnIndex==0) {
				return "��¼���";
			}else if(columnIndex==1) {
				return "�û����";
			}else if(columnIndex==2) {
				return "�鼮���";
			}else if(columnIndex==3) {
				return "���ʱ��";
			}else if(columnIndex==4) {
				return "�黹ʱ��";
			}else {
				return "����";
				}
		}
		//Jtable ��ʾ����������
		@Override
		public int getRowCount() {
			
			return recordList.size();
		}
		//��ȡjtable��ָ����ָ����Ԫ�������
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
	private void refreshtable(List<Record> recordList) {
		infoTableModel=new BookRecordInfoTableModel(recordList);
		table.setModel(infoTableModel);
	}
	
}
