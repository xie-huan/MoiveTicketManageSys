package infownd;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;


import dao.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayInfoAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField price;

	/**
	 * Create the frame.
	 */
	public PlayInfoAddFrame() {
		setTitle("\u6DFB\u52A0\u653E\u6620\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u9009\u62E9\u7535\u5F71\uFF1A");
		label.setBounds(42, 56, 81, 15);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u9009\u62E9\u653E\u6620\u5385\uFF1A");
		lblNewLabel.setBounds(42, 97, 96, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("\u8BF7\u8BBE\u5B9A\u4EF7\u683C\uFF1A");
		label_1.setBounds(42, 139, 81, 15);
		contentPane.add(label_1);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8BBE\u5B9A\u5F00\u573A\u65F6\u95F4\uFF1A");
		lblNewLabel_1.setBounds(42, 178, 108, 15);
		contentPane.add(lblNewLabel_1);
		
		//��ӳ��Ӱ��ѡ��
		JComboBox selectMoive = new JComboBox();
		selectMoive.setBounds(160, 53, 139, 21);
		List name = Dao.selectMoiveName();
		for(int i = 0; i <name.size();i++) {
			selectMoive.addItem(name.get(i));
		}
		contentPane.add(selectMoive);
		
		//��ӳ��Ӱ�ķ�ӳ��ѡ��
		JComboBox selectHall = new JComboBox();
		selectHall.setBounds(160, 94, 139, 21);
		List id = Dao.selectHallID();
		for(int i = 0; i <id.size();i++) {
			selectHall.addItem(id.get(i));
		}
		contentPane.add(selectHall);
		
		//Ʊ�۵���д
		price = new JTextField();
		price.setBounds(160, 136, 139, 21);
		contentPane.add(price);
		price.setColumns(10);
		
		//ѡ��ʼʱ��İ�ť
		JButton start_time = new DateChooserJButton(new SimpleDateFormat("yyyy/MM/dd HH"),"2018/01/01 22");
		start_time.setBounds(160, 174, 141, 23);
		contentPane.add(start_time);
		
		//ȷ����Ӱ�ť
		JButton confirm = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String moive_name = selectMoive.getSelectedItem().toString();
				int moive_id = Dao.selectMoiveIDFromMoiveName(moive_name);
				int hall_id = Integer.parseInt(selectHall.getSelectedItem().toString());
				float set_price = (float)Double.parseDouble(price.getText());
				String time = start_time.getText();
				//System.out.println(moive_name + hall+set_price+time+moive_id);
				int i = Dao.insertPlayInfo(moive_id, hall_id, set_price, time);
				if(i == 1) {
					JOptionPane.showMessageDialog(null, "�ɹ���ӣ�");
					//��ӳɹ���Ϊ�˳��ν���һ����λ��
					int schedule_id = Dao.selectScheduleIDByInfo(moive_id,
																hall_id,
																set_price,
																time);
					int j = Dao.creatTableSeatForSchedule(schedule_id,hall_id);
					
					PlayInfoManage.table.removeAll();
					Object[][] results = PlayInfoManage.getFileStates(Dao.selectPlayInfo());
					String[] columnNames = {"��ӳ���",  "ӰƬ����", "��ӳ��", "�۸�", "��ӳ����"};
					PlayInfoManage.table = new JTable(results,columnNames);
					PlayInfoManage.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					PlayInfoManage.table.getColumnModel().getColumn(4).setPreferredWidth(193);
					PlayInfoManage.scrollPane.setViewportView(PlayInfoManage.table);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
				}
			}
		});
		confirm.setBounds(123, 219, 93, 23);
		contentPane.add(confirm);
	}
}
