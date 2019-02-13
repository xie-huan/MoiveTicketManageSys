package infownd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Dao;
import data.Moive;
import data.Seller;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class SellerManage extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	public static JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public SellerManage() {
		setTitle("售票员管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 242, 198);
		scrollPane.setOpaque(false);  
		scrollPane.getViewport().setOpaque(false); 
		contentPane.add(scrollPane);

		Object[][] results = getFileStates(Dao.selectSellerInfo());
		String[] columnNames = { "售票员编号",  "用户名", "密码"};
		table = new JTable(results,columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("查询售票员");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.removeAll();
				Object[][] results = getFileStates(Dao.selectSellerInfo());
				String[] columnNames = { "售票员编号",  "用户名", "密码"};
				table = new JTable(results,columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				
				scrollPane.setViewportView(table);
			}
		});
		button.setBounds(285, 35, 106, 23);
		contentPane.add(button);

		JButton delete = new JButton("删除售票员");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selrow = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(selrow,0).toString());
				int i = Dao.DeleteSeller(id);
				if(i == 1) {
					JOptionPane.showMessageDialog(null,"删除成功！");
					
					table.removeAll();
					Object[][] results = getFileStates(Dao.selectSellerInfo());
					String[] columnNames = { "售票员编号",  "用户名", "密码"};
					table = new JTable(results,columnNames);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					
					scrollPane.setViewportView(table);
				}
			}
		});
		delete.setBounds(285, 171, 106, 23);
		contentPane.add(delete);
		
		JButton add = new JButton("新增售票员");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerAddFrame saf = new SellerAddFrame();
				saf.setVisible(true);
				saf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		add.setBounds(285, 80, 106, 23);
		contentPane.add(add);
		
		JButton modify = new JButton("修改信息");
		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//修改表格内容
				int selrow = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(selrow,0).toString());
				String name = table.getValueAt(selrow, 1).toString();
				String pwd = table.getValueAt(selrow, 2).toString();
				
				int i = Dao.UpdateSeller(id, name, pwd);
				if(1 == i) {
					JOptionPane.showMessageDialog(null,"修改成功！");
				}
			}
		});
		modify.setBounds(285, 125, 106, 23);
		contentPane.add(modify);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 434, 261);
		label.setIcon(new ImageIcon("img/bubble.jpg"));
		contentPane.add(label);
	}
	public static Object[][] getFileStates(List list){
		String[] columnNames = { "售票员编号",  "用户名", "密码"};
		Object[][]results=new Object[list.size()][columnNames.length];
			
		for(int i=0;i<list.size();i++){
			Seller sellerinfo=(Seller)list.get(i);
			results[i][0]=new String(sellerinfo.s_id+"");
			results[i][1]=sellerinfo.s_username;
			results[i][2]=sellerinfo.s_pwd;
			}
		return results;      		
	}
}
