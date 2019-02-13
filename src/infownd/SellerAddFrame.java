package infownd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.Dao;
import java.awt.Font;

public class SellerAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField pwd;


	/**
	 * Create the frame.
	 */
	public SellerAddFrame() {
		setTitle("新增售票员");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 299, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("售票员用户名：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setForeground(Color.WHITE);
		label.setBounds(23, 61, 115, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6      \u7801\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(51, 111, 80, 15);
		contentPane.add(label_1);
		
		username = new JTextField();
		username.setBounds(141, 58, 93, 21);
		contentPane.add(username);
		username.setColumns(10);
		
		pwd = new JTextField();
		pwd.setBounds(141, 109, 93, 21);
		contentPane.add(pwd);
		pwd.setColumns(10);
		
		JButton button = new JButton("新  增");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Dao.InsertSeller(username.getText(), pwd.getText());
				if(i==1){
					JOptionPane.showMessageDialog(null, "成功添加！");
					SellerManage.table.removeAll();
					Object[][] results = SellerManage.getFileStates(Dao.selectSellerInfo());
					String[] columnNames = { "售票员编号",  "用户名", "密码"};
					SellerManage.table = new JTable(results,columnNames);
					SellerManage.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					
					SellerManage.scrollPane.setViewportView(SellerManage.table);
					dispose();
					//doDefaultCloseAction();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "该用户已存在，请重新输入！");
				}
				dispose();
			}
		});
		button.setBounds(92, 173, 93, 23);
		contentPane.add(button);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("img/bubble.jpg"));
		label_2.setBounds(0, 0, 283, 230);
		contentPane.add(label_2);
	}
}
