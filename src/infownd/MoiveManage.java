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
import data.Moive;

public class MoiveManage extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	public static JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public MoiveManage() {
		setTitle("\u5F71\u7247\u4FE1\u606F\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 469, 173);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		contentPane.add(scrollPane);
		Object[][] results = getFileStates(Dao.selectMoiveInfo());
		String[] columnNames = { "电影编号", "影片名称", "导演", "持续时间", "电影简述" };
		table = new JTable(results, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(4).setPreferredWidth(170);
		scrollPane.setViewportView(table);

		JButton add = new JButton("\u6DFB\u52A0");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MoiveAddFrame maf = new MoiveAddFrame();
				maf.setVisible(true);
				maf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

				table.removeAll();
				Object[][] results = getFileStates(Dao.selectMoiveInfo());
				String[] columnNames = { "电影编号", "影片名称", "导演", "持续时间", "电影简述" };
				table = new JTable(results, columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(4).setPreferredWidth(170);
				scrollPane.setViewportView(table);
			}
		});
		add.setBounds(31, 193, 93, 40);
		contentPane.add(add);

		JButton modify = new JButton("\u4FEE\u6539");
		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 修改表格内容
				int selrow = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(selrow, 0).toString());
				String name = table.getValueAt(selrow, 1).toString();
				String dir = table.getValueAt(selrow, 2).toString();
				String duration = table.getValueAt(selrow, 3).toString();
				String description = table.getValueAt(selrow, 4).toString();

				int i = Dao.UpdateMoive(id, name, dir, duration, description);
				if (1 == i) {
					JOptionPane.showMessageDialog(null, "修改成功！");
				}
			}
		});
		modify.setBounds(198, 193, 93, 40);
		contentPane.add(modify);

		JButton delete = new JButton("\u5220\u9664");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selrow = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(selrow, 0).toString());
				int i = Dao.DeleteMoive(id);
				if (i == 1) {
					JOptionPane.showMessageDialog(null, "删除成功！");

					table.removeAll();
					Object[][] results = getFileStates(Dao.selectMoiveInfo());
					String[] columnNames = { "电影编号", "影片名称", "导演", "持续时间", "电影简述" };
					table = new JTable(results, columnNames);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(4).setPreferredWidth(170);
					scrollPane.setViewportView(table);
				}
			}
		});
		delete.setBounds(359, 193, 93, 40);
		contentPane.add(delete);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("img/greatwall.jpg"));
		label.setBounds(0, 0, 489, 243);
		contentPane.add(label);
	}

	// 取数据库中电影相关信息放入表格中
	public static Object[][] getFileStates(List list) {
		String[] columnNames = { "电影编号", "影片名称", "导演", "持续时间", "电影简述" };
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
}
