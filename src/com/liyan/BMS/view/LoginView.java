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

	private JPanel bgpanel=new JPanel();//����һ�����
	
	private JLabel lbLogin=new JLabel("ѧ��/�˺�");//������¼��ǩ
	
	private JLabel lbType = new JLabel("��¼����");//���ͱ�ǩ
	private JLabel lbImg = null;//��ͼƬ
	
	private JButton btLogin = new JButton("��¼");//������¼��ť
	//private JButton btRegister = new JButton("ע��");//����ע�ᰴť
	
	private JTextField tfLogin = new JTextField();//����ѧ�������
	private JComboBox<String> cbType=new JComboBox<String>(new String[] {"ѧ��","����Ա"});
	
	
	
	
	public LoginView() {
		registerListener();
		this.setTitle("ͼ�����ϵͳ-��¼.ly");
		
		this.setSize(960, 600);//���ô����С

		this.setLocationRelativeTo(null);//ʹ�������
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);//�����˳��رճ���
		this.setVisible(true);//ʹ����ɼ�
		
		//����ͼƬ
		ImageIcon img = new ImageIcon("./lib/2.jpg");
		lbImg = new JLabel(img);
		lbImg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		//��LayeredPane��ײ�����Ӵ�ͼƬ�ı�ǩ
		this.getLayeredPane().add(lbImg, new Integer(Integer.MIN_VALUE));
		
		//�������������Ϊ͸����ʹ֮�ܹ�����layeredpane�ı���
		((JPanel) this.getContentPane()).setOpaque(false);
		
		
		//��������bgpanel�У�����Ϊ������
		bgpanel.setLayout(null);
		add(lbLogin);
		add(tfLogin);
		add(lbType);
		add(cbType);
		add(btLogin);
		//add(btRegister);
		
		//�������λ��
		lbLogin.setBounds(95, 130, 100, 30);
		tfLogin.setBounds(200, 130, 215, 40);
		lbType.setBounds(95, 180, 100, 30);
		cbType.setBounds(200, 180, 215, 40);
		btLogin.setBounds(450, 180, 140, 40);
		//btRegister.setBounds(255, 230, 140, 35);
		
		bgpanel.setOpaque(false);//��Ϊ͸��
		getContentPane().add(bgpanel);//���bgpanel������
		
	}
	
	private void registerListener() {
		btLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//��ȡ�û���������
				String userId=tfLogin.getText();
				int userType=cbType.getSelectedIndex()+1;
				if(userId.equals("")) {
					JOptionPane.showMessageDialog(LoginView.this,"�����������˺�");
					return;
				}
				User user=new User(userId, userType);
				user=UserService.login(user);
				
				if(user!=null) {
					//��ͨ�û�
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
						JOptionPane.showMessageDialog(LoginView.this,"ע��ɹ�!");
						if(user1.getType()==1) {
							
							new UserMainView(user1);
							LoginView.this.dispose();
							
							}
						else if(user1.getType()==2){
							new AdminMainView(user1);
							LoginView.this.dispose();
						}
					}else if(3==status) {
						JOptionPane.showMessageDialog(LoginView.this,"ע��ʧ��!");
					}
				}
				
			}
		});
				
		
		}
	
	
}
