package liyan.BMS.view;

import com.liyan.BMS.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMainView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel=null;
	private JPanel welPanel=null;
	private JPanel btnPanel=null;
	private JDesktopPane funcDesktop=null;
	private JLabel deskLabel=null;
	private JButton btnCheckBorrow=null;
	private JButton btnBookRecord=null;
	private JButton btnExit = null;
	
	private JLabel lbWelcome=null;
	
	
	public UserMainView(User user) {
		init();
		registerListener(user);
	}
	
	
	
	


	private void init() {
		mainPanel=new JPanel(new BorderLayout());
		btnPanel=new JPanel(new GridLayout(7, 1, 0, 35));
		btnCheckBorrow=new JButton("查找书籍");
		btnBookRecord=new JButton("查询记录");
		btnExit=new JButton("退出");
		btnPanel.add(btnCheckBorrow);
		btnPanel.add(btnBookRecord);
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(new JLabel());
		btnPanel.add(btnExit);
		
		btnPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), ""));
		
		welPanel=new JPanel();
		lbWelcome=new JLabel("欢迎!!");
		welPanel.add(lbWelcome);
	
		
		funcDesktop=new JDesktopPane();
		ImageIcon img=new ImageIcon("./lib/2.jpg");
		deskLabel=new JLabel(img);
		deskLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		funcDesktop.add(deskLabel, new Integer(Integer.MIN_VALUE));
		
		
		
		mainPanel.add(btnPanel,BorderLayout.EAST);
		mainPanel.add(welPanel,BorderLayout.NORTH);
		mainPanel.add(funcDesktop, BorderLayout.CENTER);
		
		this.setTitle("用户界面");
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
				funcDesktop.add(cbv);//??????????????????????????????
				cbv.toFront();//???????????
			}
		});
		
		btnBookRecord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserBookRecordView  brv=new UserBookRecordView (user);
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
