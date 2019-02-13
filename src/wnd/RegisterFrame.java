package wnd;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.Dao;

public class RegisterFrame extends JFrame {
	private JTextField user_name;
	private JPasswordField user_pwd;
	private JPasswordField user_pwd2;
	
	/**
	 * Create the frame.
	 */
	public RegisterFrame() {
		setTitle("注册");
		setBounds(100, 100, 420, 303);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("用   户   名：");
		label.setBounds(87, 73, 84, 15);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("密        码：");
		label_1.setBounds(87, 117, 84, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("再次输入密码：");
		label_2.setBounds(87, 163, 84, 15);
		getContentPane().add(label_2);
		
		user_name = new JTextField();
		user_name.setBounds(181, 70, 128, 21);
		getContentPane().add(user_name);
		user_name.setColumns(10);
		
		user_pwd = new JPasswordField();
		user_pwd.setBounds(181, 114, 128, 21);
		getContentPane().add(user_pwd);
		
		user_pwd2 = new JPasswordField();
		user_pwd2.setBounds(181, 160, 128, 21);
		getContentPane().add(user_pwd2);
		
		JButton sign_in = new JButton("注  册");
		sign_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!user_pwd.getText().equals(user_pwd2.getText()))
					JOptionPane.showMessageDialog(null, "两次输入密码不一致，请重新输入！");
				else{
					int i = Dao.InsertSeller(user_name.getText(), user_pwd.getText());
					if(i==1){
						JOptionPane.showMessageDialog(null, "注册成功！");
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "该用户已存在，请重新输入！");
					}
				}
			}
		});
		sign_in.setBounds(88, 204, 93, 23);
		getContentPane().add(sign_in);
		
		JButton go_back = new JButton("返  回");
		go_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		go_back.setBounds(217, 204, 93, 23);
		getContentPane().add(go_back);
		


	}

}
