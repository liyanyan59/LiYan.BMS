package liyan.BMS.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.liyan.BMS.entity.User;

public class AdminMainView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String User = null;
	private JPanel mainPanel=null;//�����
	private JPanel welPanel=null;//��ӭ���
	private JPanel btnPanel=null;//��ť�����
	private JDesktopPane funcDesktop=null;//�������
	private JLabel deskLabel=null;//��ͼƬ
	private JButton btnAddBook=null;//��
	//private JButton btnDelBook=null;//ɾ
	private JButton btnUpdateBook=null;//��
	private JButton btnCheckBook=null;//��
	
	
	private JButton btnExit = null;//�˳���ť
	
	private JLabel lbWelcome=null;//��ӭ����
	
	public AdminMainView(User user) {
		// TODO Auto-generated constructor stub
	
		init();
		registerListener(user);
	}
	
	
	
	
	private void init() {
		mainPanel=new JPanel(new BorderLayout());
		btnPanel=new JPanel(new GridLayout(7, 1, 0, 35));
		btnAddBook=new JButton("����鼮");
		//btnDelBook=new JButton("ɾ���鼮");
		btnUpdateBook=new JButton("�����鼮��Ϣ");
		btnCheckBook=new JButton("��ѯȫ���鼮��ɾ��");
		btnExit=new JButton("�˳�");
		btnPanel.add(btnAddBook);
		//btnPanel.add(btnDelBook);
		btnPanel.add(btnUpdateBook);
		btnPanel.add(btnCheckBook);
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
		
		this.setTitle("--ͼ�����ϵͳ---����Ա����");
		this.getContentPane().add(mainPanel);
		this.setSize(1000, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
	
	
	private void registerListener(User user) {
		btnAddBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminAddView aav= new AdminAddView(user);
				funcDesktop.add(aav);//��ָ������ͼ������ӵ�������������
				aav.toFront();//��ͼ��ʾ����ǰ
			}
		});
		
		
		
		btnUpdateBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdminUpdateView auv=new AdminUpdateView();
				funcDesktop.add(auv);
				auv.toFront();
			}
		});
		
		btnCheckBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdminCheckView acv=new AdminCheckView();
				funcDesktop.add(acv);
				acv.toFront();
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
