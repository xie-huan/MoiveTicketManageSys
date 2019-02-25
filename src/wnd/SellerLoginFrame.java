package wnd;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dao.Dao;
//import mtmsys.MoiveTicketsManager;
import mtmsys.Sellersys;

public class SellerLoginFrame extends JFrame {

	private JPanel panel;
	public JTextField user_name;
	private JPasswordField user_pwd;

	/**
	 * Create the frame.
	 */
	public SellerLoginFrame() {
		setTitle("ÊÛÆ±Ô±µÇÂ¼");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 278);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		JLabel username = new JLabel("ÓÃ»§Ãû£º");
		username.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		username.setBounds(74, 80, 54, 15);
		panel.add(username);

		JLabel userpwd = new JLabel("ÃÜ  Âë£º");
		userpwd.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		userpwd.setBounds(74, 126, 54, 15);
		panel.add(userpwd);

		JButton login = new JButton("µÇ  Â¼");
		login.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Dao.checkUserLogin(user_name.getText(), user_pwd.getText())) {
					JOptionPane.showMessageDialog(null, "µÇÂ¼³É¹¦");
					Sellersys ss = new Sellersys(user_name.getText());
					ss.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "ÃÜÂë´íÎó»òÓÃ»§´æÔÚ");
					user_name.setText("");
					user_pwd.setText("");
				}
			}
		});
		login.setBounds(74, 184, 93, 23);
		panel.add(login);

		JButton sign_in = new JButton("×¢  ²á");
		sign_in.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		sign_in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame sf = new RegisterFrame();
				sf.setVisible(true);
			}
		});
		sign_in.setBounds(223, 184, 93, 23);
		panel.add(sign_in);

		JButton button = new JButton("BACK");
		button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		button.setBackground(UIManager.getColor("Button.background"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectFrame.selectFrame.setVisible(true);
				dispose();
			}
		});
		button.setBounds(10, 10, 93, 23);
		panel.add(button);

		user_name = new JTextField();
		user_name.setBounds(142, 77, 174, 21);
		panel.add(user_name);
		user_name.setColumns(10);

		user_pwd = new JPasswordField();
		user_pwd.setBounds(142, 123, 174, 21);
		panel.add(user_pwd);

		JLabel label = new JLabel("");
		// label.setIcon(new ImageIcon("E:\\Eclipse
		// WorkSpace\\MoiveTicketsManagementSystem\\res\\sellerLogin.jpg"));
		label.setIcon(new ImageIcon("img/sellerLogin.jpg"));
		label.setBounds(0, 0, 377, 239);
		panel.add(label);

	}
}
