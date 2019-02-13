package wnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Dao;
//import mtmsys.MoiveTicketsManager;
import mtmsys.Sellersys;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SellerLoginFrame extends JFrame {

	private JPanel panel;
	private JTextField user_name;
	private JPasswordField user_pwd;

	/**
	 * Create the frame.
	 */
	public SellerLoginFrame() {
		setTitle("售票员登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 278);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);	

		JLabel username = new JLabel("用户名：");
		username.setBounds(74, 80, 54, 15);
		panel.add(username);
		
		JLabel userpwd = new JLabel("密  码：");
		userpwd.setBounds(74, 126, 54, 15);
		panel.add(userpwd);
		
		JButton login = new JButton("登  录");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Dao.checkUserLogin(user_name.getText(), user_pwd.getText())){
					JOptionPane.showMessageDialog(null, "登录成功");
					Sellersys ss = new Sellersys(user_name.getText());
					ss.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "密码错误或用户存在");
					user_name.setText("");
					user_pwd.setText("");
				}
			}
		});
		login.setBounds(74, 184, 93, 23);
		panel.add(login);
		
		JButton sign_in = new JButton("注  册");
		sign_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame sf = new RegisterFrame();
				sf.setVisible(true);
			}
		});
		sign_in.setBounds(223, 184, 93, 23);
		panel.add(sign_in);
		
		user_name = new JTextField();
		user_name.setBounds(142, 77, 174, 21);
		panel.add(user_name);
		user_name.setColumns(10);
		
		user_pwd = new JPasswordField();
		user_pwd.setBounds(142, 123, 174, 21);
		panel.add(user_pwd);
		
		JLabel label = new JLabel("");
		//label.setIcon(new ImageIcon("E:\\Eclipse WorkSpace\\MoiveTicketsManagementSystem\\res\\sellerLogin.jpg"));
		label.setIcon(new ImageIcon("img/sellerLogin.jpg"));
		label.setBounds(0, 0, 377, 239);
		panel.add(label);
	}
}
