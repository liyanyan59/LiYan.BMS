package liyan.BMS.view;

import liyan.BMS.service.UserService;
import liyan.BMS.entity.User;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class RegisterView extends JFrame {

	private JLabel lbId=new JLabel("ѧ��/�˺�:");
	private JTextField tfId=new JTextField();
	private JLabel lbType=new JLabel("�˻����ͣ�");
	private JComboBox<String> cbType=new JComboBox<String>(new String[] {"ѧ��","����Ա"});
	private JLabel lbimg=null;
	private JButton btnRegister=new JButton("ע��");
	
	
	public RegisterView() {
		registerListener();
		this.setTitle("ע��");
		this.setLocationRelativeTo(null);//ʹ�������
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//�����˳��رճ���
		this.setVisible(true);//ʹ����ɼ�
		this.setLayout(null);
		this.setResizable(false);
		ImageIcon img=new ImageIcon("./lib/1.jpg");
		this.setSize(img.getIconWidth(), img.getIconHeight());//���ڴ�С
		lbimg=new JLabel(img);
		lbimg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		//��LayeredPane��ײ�����Ӵ�ͼƬ�ı�ǩ
		this.getLayeredPane().add(lbimg, new Integer(Integer.MIN_VALUE));
				
		//�������������Ϊ͸����ʹ֮�ܹ�����layeredpane�ı���
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
		cbType.setOpaque(false);//͸��
		
		
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
					JOptionPane.showMessageDialog(RegisterView.this, "�û��Ѵ���!���ص�¼!");
					new LoginView();
					RegisterView.this.dispose();
				}else if(status==2) {
					JOptionPane.showMessageDialog(RegisterView.this, "ע��ɹ�!���ص�¼!");
					new LoginView();
					RegisterView.this.dispose();
				}else {
					int op = JOptionPane.showConfirmDialog(RegisterView.this, "ע��ʧ�ܣ��Ƿ�����ע�᣿");
					if(op==1){
						new LoginView();
						RegisterView.this.dispose();
					}
				}
				
			}
		});
	}
}
