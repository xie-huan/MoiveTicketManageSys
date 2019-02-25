package infownd;

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
import javax.swing.border.EmptyBorder;

import dao.Dao;
import data.Seller;

public class SellerManage extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	public static JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public SellerManage() {
		setTitle("��ƱԱ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 265, 198);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		contentPane.add(scrollPane);

		Object[][] results = getFileStates(Dao.selectSellerInfo());
		String[] columnNames = { "��ƱԱ���", "�û���", "����" };
		table = new JTable(results, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);

		JButton button = new JButton("��ѯ��ƱԱ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.removeAll();
				Object[][] results = getFileStates(Dao.selectSellerInfo());
				String[] columnNames = { "��ƱԱ���", "�û���", "����" };
				table = new JTable(results, columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

				scrollPane.setViewportView(table);
			}
		});
		button.setBounds(301, 29, 106, 32);
		contentPane.add(button);

		JButton delete = new JButton("ɾ����ƱԱ");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selrow = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(selrow, 0).toString());
					int i = Dao.DeleteSeller(id);
					if (i == 1) {
						JOptionPane.showMessageDialog(null, "ɾ���ɹ���");

						table.removeAll();
						Object[][] results = getFileStates(Dao.selectSellerInfo());
						String[] columnNames = { "��ƱԱ���", "�û���", "����" };
						table = new JTable(results, columnNames);
						table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

						scrollPane.setViewportView(table);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "����ѡ��Ҫɾ������ƱԱ");
				}
			}
		});
		delete.setBounds(301, 197, 106, 32);
		contentPane.add(delete);

		JButton add = new JButton("������ƱԱ");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerAddFrame saf = new SellerAddFrame();
				saf.setVisible(true);
				saf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		add.setBounds(301, 86, 106, 32);
		contentPane.add(add);

		JButton modify = new JButton("�޸���Ϣ");
		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �޸ı������
				try {
					int selrow = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(selrow, 0).toString());
					String name = table.getValueAt(selrow, 1).toString();
					String pwd = table.getValueAt(selrow, 2).toString();

					int i = Dao.UpdateSeller(id, name, pwd);
					if (1 == i) {
						JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���ƱԱ��Ϣ���޸�");
				}
			}
		});
		modify.setBounds(301, 143, 106, 32);
		contentPane.add(modify);

		JLabel label = new JLabel("");
		label.setBounds(0, 0, 434, 261);
		label.setIcon(new ImageIcon("img/bubble.jpg"));
		contentPane.add(label);
	}

	public static Object[][] getFileStates(List list) {
		String[] columnNames = { "��ƱԱ���", "�û���", "����" };
		Object[][] results = new Object[list.size()][columnNames.length];

		for (int i = 0; i < list.size(); i++) {
			Seller sellerinfo = (Seller) list.get(i);
			results[i][0] = new String(sellerinfo.s_id + "");
			results[i][1] = sellerinfo.s_username;
			results[i][2] = sellerinfo.s_pwd;
		}
		return results;
	}
}
