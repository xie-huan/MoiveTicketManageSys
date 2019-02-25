package wnd;

import java.awt.Color;
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
import mtmsys.Managersys;

public class ManagerLoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField admin_name_text;
	private JPasswordField admin_pwd;

	/**
	 * Create the frame.
	 */
	public ManagerLoginFrame() {
		setTitle("π‹¿Ì‘±µ«¬º");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel admin_name = new JLabel("’       ∫≈£∫");
		admin_name.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 14));
		admin_name.setForeground(Color.WHITE);
		admin_name.setBounds(58, 67, 84, 15);
		contentPane.add(admin_name);

		JLabel pwd = new JLabel("√‹      ¬Î£∫");
		pwd.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.BOLD, 14));
		pwd.setForeground(Color.WHITE);
		pwd.setBounds(58, 125, 81, 15);
		contentPane.add(pwd);

		admin_name_text = new JTextField();
		admin_name_text.setBounds(148, 64, 128, 21);
		contentPane.add(admin_name_text);
		admin_name_text.setColumns(10);

		admin_pwd = new JPasswordField();
		admin_pwd.setBounds(149, 122, 128, 21);
		contentPane.add(admin_pwd);

		JButton login = new JButton("µ«  ¬º");
		login.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		login.setBackground(UIManager.getColor("Button.background"));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Dao.checkLogin(admin_name_text.getText(), admin_pwd.getText())) {
					JOptionPane.showMessageDialog(null, "µ«¬º≥…π¶");
					Managersys ms = new Managersys();
					ms.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "√‹¬Î¥ÌŒÛªÚ”√ªß¥Ê‘⁄,«Î÷ÿ–¬ ‰»Î");
					admin_name_text.setText("");
					admin_pwd.setText("");
				}
			}
		});
		login.setBounds(58, 183, 93, 23);
		contentPane.add(login);

		JButton button = new JButton("∑µ  ªÿ");
		button.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		button.setBackground(UIManager.getColor("Button.background"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectFrame.selectFrame.setVisible(true);
				dispose();
			}
		});
		button.setBounds(181, 183, 93, 23);
		contentPane.add(button);

		JLabel l = new JLabel("");
		l.setIcon(new ImageIcon("img/adminLogin.jpg"));
		l.setBounds(0, 0, 349, 240);
		contentPane.add(l);
	}
}
