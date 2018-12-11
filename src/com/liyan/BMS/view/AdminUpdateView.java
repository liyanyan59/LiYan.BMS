package liyan.BMS.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import liyan.BMS.entity.Book;
import liyan.BMS.service.BookService;

public class AdminUpdateView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lbBookId=new JLabel("需修改的书籍ID");
	private JComboBox<String> cbSeletion=new JComboBox<String>(new String[] {"修改书籍名称","修改作者名称","修改现有库存","修改总库存",
			"修改书籍状态","修改书籍编号"});
	
	private JLabel lbSeletion=new JLabel("请选择修改内容");
	private JLabel lbInfo=new JLabel("请输入修改内容");
	
	private JTextField tfBookId=new JTextField();
	private JTextField tfInfo=new JTextField();
	
	
	private JButton btnUpdate=new JButton("修改");
	private JButton btnCancel=new JButton("取消");
	
	public AdminUpdateView() {
		registerListener();
 
		this.setTitle("更改书籍信息");
		this.setIconifiable(true);//可最小化
		this.setClosable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setBounds(220,100,350,250);
		
		
		this.setLayout(null);
		add(lbBookId);
		add(tfBookId);
		add(lbSeletion);
		add(cbSeletion);
		add(lbInfo);
		add(tfInfo);
		add(btnUpdate);
		add(btnCancel);
		
		lbBookId.setBounds(0,15,100,35);
		lbSeletion.setBounds(0,55,100,35);
		lbInfo.setBounds(0,95,100,35);
		
		
		tfBookId.setBounds(103,15,230,35);
		cbSeletion.setBounds(103,55,230,35);
		tfInfo.setBounds(103,95,230,35);
		
		
		btnUpdate.setBounds(50,170,100,40);
		btnCancel.setBounds(195,170,100,40);
	
	}

	private void registerListener() {
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminUpdateView.this.dispose();
				
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String bookId=tfBookId.getText();
				int selected=cbSeletion.getSelectedIndex();
				String info=tfInfo.getText();
				Book book=BookService.checkBookBybookId(bookId);
				if(selected==0) {
					book.setBookName(info);
				}else if(selected==1) {
					book.setAuthorName(info);
				}else if(selected==2) {
					book.setPresentStock(Integer.parseInt(info));
				}else if(selected==3){
					book.setTotalStock(Integer.parseInt(info));
				}else if(selected==4){
					book.setStatus(Integer.parseInt(info));
				}else {
					book.setId(info);
				}
				
				boolean flag=BookService.modifyBook(book);
				
				if(flag) {
					JOptionPane.showMessageDialog(AdminUpdateView.this, "修改成功!");
				}else {

					JOptionPane.showMessageDialog(AdminUpdateView.this, "修改失败,请重试!");
				}
				
			}
		});
	}
}
