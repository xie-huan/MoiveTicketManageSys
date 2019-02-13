package infownd;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import data.Schedule;

public class PlayInfoManage extends JFrame {

	private JPanel contentPane;
	public static JTable table;
	public static JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public PlayInfoManage() {
		setTitle("影片放映管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 495, 163);
		scrollPane.setOpaque(false);  
		scrollPane.getViewport().setOpaque(false); 
		contentPane.add(scrollPane);
		
		//table.removeAll();
		Object[][] results = getFileStates(Dao.selectPlayInfo());
		String[] columnNames = {"放映编号",  "影片名称", "放映厅", "价格", "放映日期"};
		table = new JTable(results,columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(4).setPreferredWidth(193);
		scrollPane.setViewportView(table);
		
		JButton add = new JButton("添加放映信息");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PlayInfoAddFrame piaf = new PlayInfoAddFrame();
				piaf.setVisible(true);
				piaf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		add.setBounds(20, 193, 120, 23);
		contentPane.add(add);
		
		JButton del = new JButton("删除放映信息");
		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selrow = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(selrow,0).toString());
				int i = Dao.DeletePlayInfo(id);
				if(i == 1) {
					JOptionPane.showMessageDialog(null,"删除成功！");
				}
				table.removeAll();
				Object[][] results = getFileStates(Dao.selectPlayInfo());
				String[] columnNames = {"放映编号",  "影片名称", "放映厅", "价格", "放映日期"};
				table = new JTable(results,columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(4).setPreferredWidth(193);
				scrollPane.setViewportView(table);
			}
		});
		del.setBounds(150, 193, 121, 23);
		contentPane.add(del);
		
		JButton modify = new JButton("修改放映信息");
		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//修改表格内容
				int selrow = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(selrow, 0).toString());
				float price = (float)Double.parseDouble(table.getValueAt(selrow, 3).toString());
		
				int i = Dao.UpdateSchedule(id,price);
				if(1 == i) {
					JOptionPane.showMessageDialog(null,"修改成功！");
					table.removeAll();
					Object[][] results = getFileStates(Dao.selectPlayInfo());
					String[] columnNames = {"放映编号",  "影片名称", "放映厅", "价格", "放映日期"};
					table = new JTable(results,columnNames);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getColumnModel().getColumn(4).setPreferredWidth(193);
					scrollPane.setViewportView(table);
				}
			}
		});
		modify.setBounds(281, 193, 120, 23);
		contentPane.add(modify);
		
		JButton update = new JButton("刷    新");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.removeAll();
				Object[][] results = getFileStates(Dao.selectPlayInfo());
				String[] columnNames = { "放映编号",  "影片名称", "放映厅", "价格", "放映日期"};
				table = new JTable(results,columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getColumnModel().getColumn(4).setPreferredWidth(193);
				scrollPane.setViewportView(table);
			}
		});
		update.setBounds(411, 193, 103, 23);
		contentPane.add(update);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img/planet.jpg"));
		lblNewLabel.setBounds(0, 0, 538, 246);
		contentPane.add(lblNewLabel);
	}

	public static Object[][] getFileStates(List list){
		String[] columnNames = { "放映编号",  "影片名称", "放映厅", "价格", "放映日期"};
		Object[][] results=new Object[list.size()][columnNames.length];
			
		for(int i=0;i<list.size();i++){
			Schedule playinfo=(Schedule)list.get(i);
			results[i][0]=new String(playinfo.id +"");
			results[i][1]=playinfo.moive_name;
			results[i][2]=playinfo.hall_id;
			results[i][3]=playinfo.price;
			results[i][4]=playinfo.time;
			}
		return results;      		
		}
}
