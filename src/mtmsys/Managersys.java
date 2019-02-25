package mtmsys;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import infownd.MoiveManage;
//import infownd.OrderAddFrame;
import infownd.PlayInfoManage;
import infownd.SellerManage;

public class Managersys extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Managersys frame = new Managersys();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Managersys() {
		setTitle("\u4FE1\u606F\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 287);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setBackground(SystemColor.activeCaptionBorder);
		label.setIcon(new ImageIcon("img/manage.jpg"));
		label.setBounds(0, 64, 449, 184);
		contentPane.add(label);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 21, 429, 29);
		toolBar.setOpaque(false);
		contentPane.add(toolBar);

		JButton moiveInfo = new JButton("电影资源管理  ");
		moiveInfo.setBackground(UIManager.getColor("Button.background"));
		moiveInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MoiveManage mm = new MoiveManage();
				mm.setVisible(true);
				mm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		moiveInfo.setOpaque(false);
		toolBar.add(moiveInfo);

		JButton moiveHall = new JButton("放映厅电影安排  ");
		moiveHall.setBackground(SystemColor.activeCaptionBorder);
		moiveHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PlayInfoManage pim = new PlayInfoManage();
				pim.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				pim.setVisible(true);
			}
		});
		moiveHall.setOpaque(false);
		toolBar.add(moiveHall);

		JButton order = new JButton("售票管理  ");
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		order.setBackground(SystemColor.activeCaptionBorder);
		order.setOpaque(false);
		toolBar.add(order);

		JButton sellerInfo = new JButton("售票员信息管理  ");
		sellerInfo.setBackground(UIManager.getColor("Button.background"));
		sellerInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SellerManage sm = new SellerManage();
				sm.setVisible(true);
				sm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		sellerInfo.setOpaque(false);
		toolBar.add(sellerInfo);

	}
}
