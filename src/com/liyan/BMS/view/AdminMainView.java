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
	private JPanel mainPanel=null;//主面板
	private JPanel welPanel=null;//欢迎面板
	private JPanel btnPanel=null;//按钮组面板
	private JDesktopPane funcDesktop=null;//桌面面板
	private JLabel deskLabel=null;//放图片
	private JButton btnAddBook=null;//增
	//private JButton btnDelBook=null;//删
	private JButton btnUpdateBook=null;//改
	private JButton btnCheckBook=null;//查
	
	
	private JButton btnExit = null;//退出按钮
	
	private JLabel lbWelcome=null;//欢迎标题
	
	public AdminMainView(User user) {
		// TODO Auto-generated constructor stub
	
		init();
		registerListener(user);
	}
	
	
	
	
	private void init() {
		mainPanel=new JPanel(new BorderLayout());
		btnPanel=new JPanel(new GridLayout(7, 1, 0, 35));
		btnAddBook=new JButton("添加书籍");
		//btnDelBook=new JButton("删除书籍");
		btnUpdateBook=new JButton("更改书籍信息");
		btnCheckBook=new JButton("查询全部书籍及删除");
		btnExit=new JButton("退出");
		btnPanel.add(btnAddBook);
		//btnPanel.add(btnDelBook);
		btnPanel.add(btnUpdateBook);
		btnPanel.add(btnCheckBook);
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
		
		this.setTitle("--图书管理系统---管理员界面");
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
				funcDesktop.add(aav);//把指定的视图窗口添加到桌面容器当中
				aav.toFront();//视图显示在最前
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
