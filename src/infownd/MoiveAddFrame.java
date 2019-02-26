package infownd;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.Dao;

public class MoiveAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField moiveName;
	private JTextField moiveDirector;
	private JTextField moiveDuration;
	private JTextField movieDescription;

	/**
	 * Create the frame.
	 */
	public MoiveAddFrame() {
		setTitle("µçÓ°ÐÅÏ¢Ìí¼Ó");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Ó°Æ¬Ãû£º");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label.setForeground(SystemColor.window);
		label.setBounds(62, 46, 54, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("µ¼  ÑÝ£º");
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_1.setForeground(SystemColor.window);
		label_1.setBounds(62, 88, 54, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\u6301\u7EED\u65F6\u95F4\uFF1A");
		label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_2.setForeground(SystemColor.window);
		label_2.setBounds(62, 129, 75, 15);
		contentPane.add(label_2);

		JLabel label_4 = new JLabel("\u7535\u5F71\u7B80\u4ECB\uFF1A");
		label_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_4.setForeground(SystemColor.window);
		label_4.setBounds(62, 168, 75, 15);
		contentPane.add(label_4);

		moiveName = new JTextField();
		moiveName.setBounds(140, 43, 126, 21);
		contentPane.add(moiveName);
		moiveName.setColumns(10);

		moiveDirector = new JTextField();
		moiveDirector.setBounds(140, 88, 126, 21);
		contentPane.add(moiveDirector);
		moiveDirector.setColumns(10);

		moiveDuration = new JTextField();
		moiveDuration.setBounds(140, 126, 126, 21);
		contentPane.add(moiveDuration);
		moiveDuration.setColumns(10);

		movieDescription = new JTextField();
		movieDescription.setBounds(140, 168, 126, 60);
		contentPane.add(movieDescription);
		movieDescription.setColumns(10);

		JButton add = new JButton("Ìí  ¼Ó");
		add.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (moiveName.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Ó°Æ¬Ãû²»ÄÜÎª¿Õ£¡");
					return;
				}
				String name = moiveName.getText();
				String dir = moiveDirector.getText();
				String duration = moiveDuration.getText();
				String description = movieDescription.getText();
				int i = Dao.InsertMoive(name, dir, duration, description);
				if (i == 1) {
					JOptionPane.showMessageDialog(null, "Ìí¼Ó³É¹¦£¡");

					MoiveManage.table.removeAll();
					Object[][] results = MoiveManage.getFileStates(Dao.selectMoiveInfo());
					String[] columnNames = { "µçÓ°±àºÅ", "Ó°Æ¬Ãû³Æ", "µ¼ÑÝ", "³ÖÐøÊ±¼ä", "µçÓ°¼òÊö" };
					MoiveManage.table = new JTable(results, columnNames);
					MoiveManage.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					MoiveManage.table.getColumnModel().getColumn(4).setPreferredWidth(170);
					MoiveManage.scrollPane.setViewportView(MoiveManage.table);

					dispose();
				}
			}
		});
		add.setBounds(107, 247, 93, 23);
		contentPane.add(add);

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("img/sea.jpg"));
		label_3.setBounds(0, 0, 305, 292);
		contentPane.add(label_3);

	}
}
