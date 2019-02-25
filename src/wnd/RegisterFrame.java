package wnd;

import java.awt.Font;
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
		setTitle("ע��");
		setBounds(100, 100, 401, 303);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("��   ��   ����");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		label.setBounds(69, 59, 102, 15);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("��        �룺");
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		label_1.setBounds(69, 103, 102, 15);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("�ٴ��������룺");
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		label_2.setBounds(69, 150, 102, 15);
		getContentPane().add(label_2);

		user_name = new JTextField();
		user_name.setBounds(181, 56, 128, 21);
		getContentPane().add(user_name);
		user_name.setColumns(10);

		user_pwd = new JPasswordField();
		user_pwd.setBounds(181, 100, 128, 21);
		getContentPane().add(user_pwd);

		user_pwd2 = new JPasswordField();
		user_pwd2.setBounds(181, 147, 128, 21);
		getContentPane().add(user_pwd2);

		JButton sign_in = new JButton("ע  ��");
		sign_in.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		sign_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!user_pwd.getText().equals(user_pwd2.getText()))
					JOptionPane.showMessageDialog(null, "�����������벻һ�£����������룡");
				else {
					int i = Dao.InsertSeller(user_name.getText(), user_pwd.getText());
					if (i == 1) {
						JOptionPane.showMessageDialog(null, "ע��ɹ���");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "���û��Ѵ��ڣ����������룡");
					}
				}
			}
		});
		sign_in.setBounds(69, 204, 93, 23);
		getContentPane().add(sign_in);

		JButton go_back = new JButton("��  ��");
		go_back.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		go_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		go_back.setBounds(217, 204, 93, 23);
		getContentPane().add(go_back);

	}

}
