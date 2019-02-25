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

	// ϵͳ��ڣ�ѡ��ҳ�棬ѡ���û���ɫ������Ա����ƱԱ��
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
		setTitle("��ӭ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 283, 193);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/***************************************
		 * ����Ա����ƱԱ��ѡ�����
		 ****************************************/
		JLabel tips = new JLabel("����");
		tips.setForeground(new Color(51, 51, 102));
		tips.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		tips.setBounds(47, 35, 48, 33);
		contentPane.add(tips);

		JButton admin = new JButton("����Ա");
		admin.setFont(new Font("΢���ź�", Font.PLAIN, 12));
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

		JLabel label = new JLabel("����");
		label.setForeground(new Color(51, 51, 102));
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setBounds(47, 85, 48, 33);
		contentPane.add(label);

		JButton seller = new JButton("��ƱԱ");
		seller.setFont(new Font("΢���ź�", Font.PLAIN, 12));
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
