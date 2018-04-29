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
	private JPanel mainPanel=null;//主面板
	private JPanel welPanel=null;//欢迎面板
	private JPanel btnPanel=null;//按钮组面板
	private JDesktopPane funcDesktop=null;//桌面面板
	private JLabel deskLabel=null;//放图片
	private JButton btnCheckBorrow=null;//查询借书按钮
	private JButton btnBookRecord=null;//查看借书记录
	private JButton btnExit = null;//退出按钮
	
	private JLabel lbWelcome=null;//欢迎标题
	
	
	public UserMainView(User user) {
		init();
		registerListener(user);
	}
	
	
	
	


	private void init() {
		mainPanel=new JPanel(new BorderLayout());
		btnPanel=new JPanel(new GridLayout(7, 1, 0, 35));
		btnCheckBorrow=new JButton("查询借书功能");
		btnBookRecord=new JButton("借书记录及还书");
		btnExit=new JButton("退出");
		btnPanel.add(btnCheckBorrow);
		btnPanel.add(btnBookRecord);
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(btnExit);
		
		btnPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "快捷键"));
		
		welPanel=new JPanel();
		lbWelcome=new JLabel("欢迎使用此图书管理系统");
		welPanel.add(lbWelcome);
	
		
		funcDesktop=new JDesktopPane();
		ImageIcon img=new ImageIcon("./lib/2.jpg");
		deskLabel=new JLabel(img);
		deskLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		funcDesktop.add(deskLabel, new Integer(Integer.MIN_VALUE));
		
		
		
		mainPanel.add(btnPanel,BorderLayout.EAST);
		mainPanel.add(welPanel,BorderLayout.NORTH);
		mainPanel.add(funcDesktop, BorderLayout.CENTER);
		
		this.setTitle("--图书管理系统--");
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
				funcDesktop.add(cbv);//把指定的视图窗口添加到桌面容器当中
				cbv.toFront();//视图显示在最前
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
