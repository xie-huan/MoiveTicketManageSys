package mtmsys;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import dao.Dao;
import data.Moive;
import data.Schedule;
import infownd.OrderAddFrame;
import infownd.OrderUnsubscribeFrame;
import wnd.SellerPwdModify;

public class Sellersys extends JFrame {
	private JPanel contentPane;
	private String user_name;
	private JTable table;

	public void setUserName(String s) {
		user_name = s;
	}

	public String getUserName() {
		return user_name;
	}

	/**
	 * Create the frame.
	 */
	public Sellersys(String user_name) {
		this.user_name = user_name;
		setTitle("��Ʊ����ϵͳ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 374);
		contentPane = new JPanel();
		contentPane.setToolTipText("��Ӱ��Ϣ��ѯ");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setForeground(UIManager.getColor("Button.disabledShadow"));
		toolBar.setBounds(5, 5, 443, 31);
		toolBar.setToolTipText("");
		toolBar.setOpaque(false);
		contentPane.add(toolBar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 88, 406, 237);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton moiveInfo = new JButton("��Ӱ��Ϣ��ѯ");
		moiveInfo.setBackground(Color.DARK_GRAY);
		moiveInfo.setForeground(Color.WHITE);
		moiveInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[][] results = getFileStates(Dao.selectMoiveInfo());
				String[] columnNames = { "��Ӱ���", "ӰƬ����", "����", "����ʱ��", "��Ӱ����" };
				table = new JTable(results, columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

				scrollPane.setViewportView(table);
			}
		});
		moiveInfo.setOpaque(false);
		toolBar.add(moiveInfo);

		JButton playinfo = new JButton("��ӳ��Ϣ��ѯ");
		playinfo.setBackground(Color.DARK_GRAY);
		playinfo.setForeground(SystemColor.window);
		playinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.removeAll();
				Object[][] results = getFileStates2(Dao.selectPlayInfo());
				String[] columnNames = { "��ӳ���", "ӰƬ����", "��ӳ��", "�۸�", "��ӳ����" };
				table = new JTable(results, columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

				scrollPane.setViewportView(table);
			}
		});
		playinfo.setOpaque(false);
		toolBar.add(playinfo);

		JButton moiveOrder = new JButton("��ӰƱԤ��");
		moiveOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selrow = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(selrow, 0).toString());
					List list = Dao.SelectScheduleByID(id);
					OrderAddFrame oaf = new OrderAddFrame(list);
					oaf.setVisible(true);
					oaf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "����ѡ���ӳ����");
				}
			}
		});
		moiveOrder.setBackground(Color.DARK_GRAY);
		moiveOrder.setForeground(SystemColor.window);
		moiveOrder.setOpaque(false);
		toolBar.add(moiveOrder);

		JButton unsubscribe = new JButton("��ӰƱ�˶�");
		unsubscribe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selrow = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(selrow, 0).toString());
					List list = Dao.SelectScheduleByID(id);
					OrderUnsubscribeFrame ouf = new OrderUnsubscribeFrame(list);
					ouf.setVisible(true);
					ouf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "����ѡ���ӳ����!!!");
				}
			}
		});
		unsubscribe.setBackground(Color.DARK_GRAY);
		unsubscribe.setForeground(SystemColor.window);
		/*
		 * unsubscribe.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { OrderUnsubscribe ou = new
		 * OrderUnsubscribe(); ou.setVisible(true);
		 * ou.setDefaultCloseOperation(DISPOSE_ON_CLOSE); } });
		 */
		unsubscribe.setOpaque(false);
		toolBar.add(unsubscribe);

		JButton revise = new JButton("�˻������޸�");
		revise.setBackground(Color.DARK_GRAY);
		revise.setForeground(SystemColor.window);
		revise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerPwdModify spm = new SellerPwdModify(user_name);
				spm.setVisible(true);
				spm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		revise.setOpaque(false);
		toolBar.add(revise);

		JLabel label = new JLabel("���ã�");
		label.setForeground(UIManager.getColor("Button.background"));
		label.setBounds(23, 38, 54, 15);
		contentPane.add(label);

		JLabel label_2 = new JLabel("��ӭ������Ʊ����ϵͳ�������Ϸ��Ĳ˵������в���");
		label_2.setForeground(UIManager.getColor("Button.background"));
		label_2.setBounds(25, 63, 319, 15);
		contentPane.add(label_2);

		JLabel lblNewLabel = new JLabel(user_name);
		lblNewLabel.setForeground(UIManager.getColor("Button.background"));
		lblNewLabel.setBounds(69, 38, 54, 15);
		contentPane.add(lblNewLabel);

		JLabel label_1 = new JLabel("");
		label_1.setForeground(UIManager.getColor("Button.disabledShadow"));
		label_1.setIcon(new ImageIcon("img/seller.jpg"));
		label_1.setBounds(0, 0, 448, 335);
		contentPane.add(label_1);

	}

	// ȡ���ݿ��е�Ӱ�����Ϣ��������
	public static Object[][] getFileStates(List list) {
		String[] columnNames = { "��Ӱ���", "ӰƬ����", "����", "����ʱ��", "��Ӱ����" };
		Object[][] results = new Object[list.size()][columnNames.length];

		for (int i = 0; i < list.size(); i++) {
			Moive moiveinfo = (Moive) list.get(i);
			results[i][0] = new String(moiveinfo.getId() + "");
			results[i][1] = moiveinfo.getName();
			results[i][2] = moiveinfo.getDir();
			results[i][3] = moiveinfo.getDuration();
			results[i][4] = moiveinfo.getDescription();
		}
		return results;
	}

	public static Object[][] getFileStates2(List list) {
		String[] columnNames = { "��ӳ���", "ӰƬ����", "��ӳ��", "�۸�", "��ӳ����" };
		Object[][] results = new Object[list.size()][columnNames.length];

		for (int i = 0; i < list.size(); i++) {
			Schedule playinfo = (Schedule) list.get(i);
			results[i][0] = new String(playinfo.id + "");
			results[i][1] = playinfo.moive_name;
			results[i][2] = playinfo.hall_id;
			results[i][3] = playinfo.price;
			results[i][4] = playinfo.time;
		}
		return results;
	}

}
