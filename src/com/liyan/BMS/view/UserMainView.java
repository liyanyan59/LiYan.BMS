package com.liyan.BMS.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.liyan.BMS.entity.User;

public class UserMainView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel=null;//�����
	private JPanel welPanel=null;//��ӭ���
	private JPanel btnPanel=null;//��ť�����
	private JDesktopPane funcDesktop=null;//�������
	private JLabel deskLabel=null;//��ͼƬ
	private JButton btnCheckBorrow=null;//��ѯ���鰴ť
	private JButton btnBookRecord=null;//�鿴�����¼
	private JButton btnExit = null;//�˳���ť
	
	private JLabel lbWelcome=null;//��ӭ����
	
	
	public UserMainView(User user) {
		init();
		registerListener(user);
	}
	
	
	
	


	private void init() {
		mainPanel=new JPanel(new BorderLayout());
		btnPanel=new JPanel(new GridLayout(7, 1, 0, 35));
		btnCheckBorrow=new JButton("��ѯ���鹦��");
		btnBookRecord=new JButton("�����¼������");
		btnExit=new JButton("�˳�");
		btnPanel.add(btnCheckBorrow);
		btnPanel.add(btnBookRecord);
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(btnExit);
		
		btnPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "��ݼ�"));
		
		welPanel=new JPanel();
		lbWelcome=new JLabel("��ӭʹ�ô�ͼ�����ϵͳ");
		welPanel.add(lbWelcome);
	
		
		funcDesktop=new JDesktopPane();
		ImageIcon img=new ImageIcon("./lib/2.jpg");
		deskLabel=new JLabel(img);
		deskLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		funcDesktop.add(deskLabel, new Integer(Integer.MIN_VALUE));
		
		
		
		mainPanel.add(btnPanel,BorderLayout.EAST);
		mainPanel.add(welPanel,BorderLayout.NORTH);
		mainPanel.add(funcDesktop, BorderLayout.CENTER);
		
		this.setTitle("--ͼ�����ϵͳ--");
		this.getContentPane().add(mainPanel);
		this.setSize(1000, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
	
	private void registerListener(User user) {
		btnCheckBorrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserCheckBorrowView cbv=new UserCheckBorrowView(user);
				funcDesktop.add(cbv);//��ָ������ͼ������ӵ�������������
				cbv.toFront();//��ͼ��ʾ����ǰ
			}
		});
		
		btnBookRecord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserBookRecordView brv=new UserBookRecordView(user);
				funcDesktop.add(brv);
				brv.toFront();
			}
		});
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
				
			}
		});
	}
	
}
