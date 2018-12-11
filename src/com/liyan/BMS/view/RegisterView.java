package liyan.BMS.view;

import liyan.BMS.service.UserService;
import liyan.BMS.entity.User;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class RegisterView extends JFrame {

	private JLabel lbId=new JLabel("学号/账号:");
	private JTextField tfId=new JTextField();
	private JLabel lbType=new JLabel("账户类型：");
	private JComboBox<String> cbType=new JComboBox<String>(new String[] {"学生","管理员"});
	private JLabel lbimg=null;
	private JButton btnRegister=new JButton("注册");
	
	
	public RegisterView() {
		registerListener();
		this.setTitle("注册");
		this.setLocationRelativeTo(null);//使窗体居中
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//窗口退出关闭程序
		this.setVisible(true);//使窗体可见
		this.setLayout(null);
		this.setResizable(false);
		ImageIcon img=new ImageIcon("./lib/1.jpg");
		this.setSize(img.getIconWidth(), img.getIconHeight());//窗口大小
		lbimg=new JLabel(img);
		lbimg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		//在LayeredPane最底层上添加带图片的标签
		this.getLayeredPane().add(lbimg, new Integer(Integer.MIN_VALUE));
				
		//将内容面板设置为透明，使之能够看见layeredpane的背景
		((JPanel) this.getContentPane()).setOpaque(false);
		
		
		add(lbId);
		add(tfId);
		add(lbType);
		add(cbType);
		
		lbId.setBounds(10, 70, 100, 30);
		lbType.setBounds(10, 120, 100, 30);
		lbId.setForeground(Color.WHITE);
		lbType.setForeground(Color.WHITE);
		
		tfId.setBounds(120, 65, 200, 40);
		cbType.setBounds(120, 115, 200, 40);
		tfId.setForeground(Color.white);
//		cbType.setForeground(Color.WHITE);
		
		tfId.setOpaque(false);
		cbType.setOpaque(false);//透明
		
		
		add(btnRegister);
		
		
		btnRegister.setBounds(250, 190, 100, 40);
		
		
	}
	private void registerListener() {
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String userId=tfId.getText();
				int userType=cbType.getSelectedIndex()+1;
				User user=new User(userId, userType);
				int status=UserService.registerUser(user);
				if(status==1) {
					JOptionPane.showMessageDialog(RegisterView.this, "用户已存在!返回登录!");
					new LoginView();
					RegisterView.this.dispose();
				}else if(status==2) {
					JOptionPane.showMessageDialog(RegisterView.this, "注册成功!返回登录!");
					new LoginView();
					RegisterView.this.dispose();
				}else {
					int op = JOptionPane.showConfirmDialog(RegisterView.this, "注册失败，是否重试注册？");
					if(op==1){
						new LoginView();
						RegisterView.this.dispose();
					}
				}
				
			}
		});
	}
}
