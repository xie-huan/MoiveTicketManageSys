package mtmsys;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import wnd.SelectFrame;

import infownd.MoiveManage;
//import infownd.OrderAddFrame;
import infownd.PlayInfoManage;
import infownd.SellerManage;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.SystemColor;

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
		
		JButton moiveInfo = new JButton("\u7535\u5F71\u8D44\u6E90\u7BA1\u7406  ");
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
		
		JButton sellerInfo = new JButton("\u552E\u7968\u5458\u4FE1\u606F\u7BA1\u7406  ");
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
		
		JButton moiveHall = new JButton("\u653E\u6620\u5385\u7535\u5F71\u5B89\u6392  ");
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
		
		JButton order = new JButton("\u552E\u7968\u7BA1\u7406  ");
		order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		order.setBackground(SystemColor.activeCaptionBorder);
		order.setOpaque(false);
		toolBar.add(order);

	}
}
