package wnd;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class SelectFrame extends JFrame {

	public static JFrame selectFrame;

	// 系统入口：选择页面，选择用户角色（管理员或售票员）
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selectFrame = new SelectFrame();
					selectFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;

	public SelectFrame() {
		setForeground(Color.WHITE);
		setTitle("欢迎");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 283, 193);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/***************************************
		 * 管理员与售票员的选择界面
		 ****************************************/
		JLabel tips = new JLabel("我是");
		tips.setForeground(new Color(51, 51, 102));
		tips.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tips.setBounds(47, 35, 48, 33);
		contentPane.add(tips);

		JButton admin = new JButton("管理员");
		admin.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		admin.setIcon(
				new ImageIcon(SelectFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/collapsed.gif")));
		admin.setForeground(Color.DARK_GRAY);
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerLoginFrame mlf = new ManagerLoginFrame();
				mlf.setVisible(true);
				// dispose();
				selectFrame.setVisible(false);
			}
		});
		admin.setBackground(UIManager.getColor("Button.background"));
		admin.setBounds(105, 42, 105, 23);
		contentPane.add(admin);

		JLabel label = new JLabel("我是");
		label.setForeground(new Color(51, 51, 102));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(47, 85, 48, 33);
		contentPane.add(label);

		JButton seller = new JButton("售票员");
		seller.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		seller.setIcon(
				new ImageIcon(SelectFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		seller.setForeground(Color.DARK_GRAY);
		seller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerLoginFrame slf = new SellerLoginFrame();
				slf.setVisible(true);
				selectFrame.setVisible(false);
				slf.user_name.grabFocus();
			}
		});
		seller.setBackground(UIManager.getColor("Button.background"));
		seller.setBounds(105, 92, 105, 23);
		contentPane.add(seller);

	}

}
