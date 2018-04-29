package com.liyan.BMS.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import com.liyan.BMS.entity.User;
import com.liyan.BMS.service.UserService;

public class LoginView extends JFrame {

	/**
	 * 111111
	 */
	private static final long serialVersionUID = 5844352042362133886L;

	private JPanel bgpanel=new JPanel();//创建一个面板
	
	private JLabel lbLogin=new JLabel("学号/账号");//创建登录标签
	
	private JLabel lbType = new JLabel("登录类型");//类型标签
	private JLabel lbImg = null;//放图片
	
	private JButton btLogin = new JButton("登录");//创建登录按钮
	//private JButton btRegister = new JButton("注册");//创建注册按钮
	
	private JTextField tfLogin = new JTextField();//创建学号输入框
	private JComboBox<String> cbType=new JComboBox<String>(new String[] {"学生","管理员"});
	
	
	
	
	public LoginView() {
		registerListener();
		this.setTitle("图书管理系统-登录.ly");
		
		this.setSize(960, 600);//设置窗体大小

		this.setLocationRelativeTo(null);//使窗体居中
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//窗口退出关闭程序
		this.setVisible(true);//使窗体可见
		
		//设置图片
		ImageIcon img = new ImageIcon("./lib/2.jpg");
		lbImg = new JLabel(img);
		lbImg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		//在LayeredPane最底层上添加带图片的标签
		this.getLayeredPane().add(lbImg, new Integer(Integer.MIN_VALUE));
		
		//将内容面板设置为透明，使之能够看见layeredpane的背景
		((JPanel) this.getContentPane()).setOpaque(false);
		
		
		//添加组件到bgpanel中，布局为流布局
		bgpanel.setLayout(null);
		add(lbLogin);
		add(tfLogin);
		add(lbType);
		add(cbType);
		add(btLogin);
		//add(btRegister);
		
		//设置组件位置
		lbLogin.setBounds(95, 130, 100, 30);
		tfLogin.setBounds(200, 130, 215, 40);
		lbType.setBounds(95, 180, 100, 30);
		cbType.setBounds(200, 180, 215, 40);
		btLogin.setBounds(450, 180, 140, 40);
		//btRegister.setBounds(255, 230, 140, 35);
		
		bgpanel.setOpaque(false);//设为透明
		getContentPane().add(bgpanel);//添加bgpanel到窗体
		
	}
	
	private void registerListener() {
		btLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//获取用户名和类型
				String userId=tfLogin.getText();
				int userType=cbType.getSelectedIndex()+1;
				if(userId.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this,"请输入您的账号");
					return;
				}
				User user=new User(userId, userType);
				user=UserService.login(user);
				
				if(user!=null) {
					//普通用户
					if(user.getType()==1) {
						
						new UserMainView(user);
						LoginView.this.dispose();
						
						}
					else if(user.getType()==2){
						new AdminMainView(user);
						LoginView.this.dispose();
					}
				}else {
					User user1=new User(userId, userType);
					int status=UserService.registerUser(user1);
					if(2==status) {
						JOptionPane.showMessageDialog(LoginView.this,"注册成功!");
						if(user1.getType()==1) {
							
							new UserMainView(user1);
							LoginView.this.dispose();
							
							}
						else if(user1.getType()==2){
							new AdminMainView(user1);
							LoginView.this.dispose();
						}
					}else if(3==status) {
						JOptionPane.showMessageDialog(LoginView.this,"注册失败!");
					}
				}
				
			}
		});
				
		
		}
	
	
}
