package wnd;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import dao.Dao;

public class SellerPwdModify extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private String user_name;

	/**
	 * Create the frame.
	 */
	public SellerPwdModify(String s) {
		user_name = s;
		setTitle("售票员密码修改");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 281, 197);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("请输入新密码：");
		label.setForeground(Color.WHITE);
		label.setBounds(25, 43, 91, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("再 次 输 入 ：");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(25, 78, 91, 15);
		contentPane.add(label_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(126, 40, 96, 21);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(126, 75, 96, 21);
		contentPane.add(passwordField_1);

		JButton button = new JButton("确认修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = passwordField.getText();
				String s2 = passwordField_1.getText();
				if (s1.equals(s2)) {
					int i = Dao.UpdateSellerByName(user_name, s1);
					if (i == 1) {
						JOptionPane.showMessageDialog(null, "修改成功！");
						dispose();
					} else
						JOptionPane.showMessageDialog(null, "修改失败,请联系管理员获取更多信息。");
				} else {
					JOptionPane.showMessageDialog(null, "两次输入不一致，修改失败！");
				}
			}
		});
		button.setBounds(25, 125, 93, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("取    消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(138, 125, 93, 23);
		contentPane.add(button_1);

		JLabel label_2 = new JLabel("您好，");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(25, 10, 54, 15);
		contentPane.add(label_2);

		JLabel lblNewLabel = new JLabel(user_name);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(66, 10, 54, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img/fan.jpg"));
		lblNewLabel_1.setBounds(0, 0, 274, 158);
		contentPane.add(lblNewLabel_1);
	}
}
