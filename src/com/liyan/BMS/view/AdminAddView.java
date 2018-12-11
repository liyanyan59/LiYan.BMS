package liyan.BMS.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.liyan.BMS.dao.impl.BookDaoImpl;
import com.liyan.BMS.entity.Book;
import com.liyan.BMS.entity.User;
import liyan.BMS.service.BookService;
import liyan.BMS.service.UserService;

public class AdminAddView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbBookId=new JLabel("书籍Id");
	private JLabel lbBookName=new JLabel("书籍名称");
	private JLabel lbAuthorName=new JLabel("书籍作者");
	private JLabel lbPresentStock=new JLabel("现有库存");
	private JLabel lbTotalStock=new JLabel("总库存");
	private JLabel lbStatus=new JLabel("书籍状态");
	
	private JTextField tfBookId=new JTextField();
	private JTextField tfBookName=new JTextField();
	private JTextField tfAuthorName=new JTextField();
	private JTextField tfPresentStock=new JTextField();
	private JTextField tfTotalStock=new JTextField();
	private JTextField tfStatus=new JTextField();
	
	private JButton btnAdd=new JButton("添加");
	private JButton btnCancel=new JButton("取消");
	
	public AdminAddView(User user) {
		registerlistener(user);
		this.setTitle("添加书籍");
		this.setIconifiable(true);//可最小化
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setBounds(200,100,350,320);
		
		
		this.setLayout(null);
		add(lbBookId);
		add(tfBookId);
		add(lbBookName);
		add(tfBookName);
		add(lbAuthorName);
		add(tfAuthorName);
		add(lbPresentStock);
		add(tfPresentStock);
		add(lbTotalStock);
		add(tfTotalStock);
		add(lbStatus);
		add(tfStatus);
		add(btnAdd);
		add(btnCancel);
		
		lbBookId.setBounds(0,0,80,35);
		lbBookName.setBounds(0,40,80,35);
		lbAuthorName.setBounds(0,80,80,35);
		lbPresentStock.setBounds(0,120,80,35);
		lbTotalStock.setBounds(0,160,80,35);
		lbStatus.setBounds(0,200,80,35);
		
		tfBookId.setBounds(90,0,245,35);
		tfBookName.setBounds(90,40,245,35);
		tfAuthorName.setBounds(90,80,245,35);
		tfPresentStock.setBounds(90,120,245,35);
		tfTotalStock.setBounds(90,160,245,35);
		tfStatus.setBounds(90, 200, 245, 35);
		
		btnAdd.setBounds(50,240,100,40);
		btnCancel.setBounds(195,240,100,40);
	}
	private void registerlistener(User user) {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String bookId=tfBookId.getText();
				String bookName=tfBookName.getText();
				String authorName=tfAuthorName.getText();
				int presentStock=Integer.parseInt(tfPresentStock.getText());
				int totalStock=Integer.parseInt(tfTotalStock.getText());
				int status=Integer.parseInt(tfStatus.getText());
				if(tfBookId.equals("")||tfBookName.equals("")||tfAuthorName.equals("")||
						tfPresentStock.equals("")||tfTotalStock.equals("")||tfStatus.equals("")) {
					JOptionPane.showMessageDialog(AdminAddView.this,"请勿留空!");
				}
				Book book=new Book(bookId, bookName, authorName, presentStock, totalStock, status);
				boolean addStatus=BookService.addBook(book);
				if(addStatus) {
					JOptionPane.showMessageDialog(AdminAddView.this,"添加成功!");
				}else {
					JOptionPane.showMessageDialog(AdminAddView.this,"添加失败,请重试!");
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminAddView.this.dispose();
			}
		});
	}
	
}
