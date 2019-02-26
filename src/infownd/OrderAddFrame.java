package infownd;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.Dao;
import data.Schedule;

public class OrderAddFrame extends JFrame {

	private JPanel contentPane;
	public static JTextField row;
	public static JTextField column;

	/**
	 * Create the frame.
	 */
	public OrderAddFrame(List list) {

		setTitle("\u8BA2\u7968\u9875\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 308, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		 * Label label = new JLabel("\u8BF7\u9009\u62E9\u7535\u5F71\u540D\u79F0\uFF1A");
		 * label.setBounds(10, 35, 103, 15); contentPane.add(label);
		 * 
		 * selectMoive.setBounds(123, 32, 122, 21); List name = Dao.selectMoiveName();
		 * for(int i = 0; i <name.size();i++) { selectMoive.addItem(name.get(i)); }
		 * selectMoive.addItemListener(new ItemListener() {
		 * 
		 * @Override public void itemStateChanged(ItemEvent e) { if (e.getStateChange()
		 * == ItemEvent.SELECTED) { String text=(String)selectMoive.getSelectedItem();
		 * //System.out.println(text); } } }); contentPane.add(selectMoive);
		 */

		JLabel lblNewLabel = new JLabel("\u60A8\u9009\u62E9\u7684\u573A\u6B21\u662F\uFF1A");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lblNewLabel.setBounds(28, 30, 130, 15);
		contentPane.add(lblNewLabel);

		Schedule info = (Schedule) list.get(0);
		String schedule_name = "Æ¬Ãû£º" + info.moive_name;
		String hall_id = "·ÅÓ³Ìü£º" + info.hall_id;
		String price = "Æ±¼Û£º" + info.price;
		String time = "·ÅÓ³Ê±¼ä£º" + info.time;

		JLabel moive_name = new JLabel("");
		moive_name.setBounds(39, 55, 160, 15);
		moive_name.setText(schedule_name);
		contentPane.add(moive_name);

		JLabel hall = new JLabel("");
		hall.setBounds(38, 80, 161, 15);
		hall.setText(hall_id);
		contentPane.add(hall);

		JLabel order_price = new JLabel("");
		order_price.setBounds(39, 105, 160, 15);
		order_price.setText(price);
		contentPane.add(order_price);

		JLabel begin_time = new JLabel("");
		begin_time.setBounds(38, 128, 234, 15);
		begin_time.setText(time);
		contentPane.add(begin_time);

		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u9009\u62E9\u5EA7\u4F4D\uFF1A");
		lblNewLabel_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(28, 153, 92, 15);
		contentPane.add(lblNewLabel_1);

		JButton chooseSeat = new JButton("Ñ¡Ôñ");
		chooseSeat.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		chooseSeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeatChooseFrame scf = new SeatChooseFrame(info.id, info.hall_id, 1);
				scf.setVisible(true);
				scf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		chooseSeat.setBounds(130, 149, 93, 23);
		contentPane.add(chooseSeat);

		JLabel label = new JLabel("\u884C\uFF1A");
		label.setBounds(39, 182, 54, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u5217\uFF1A");
		label_1.setBounds(140, 182, 54, 15);
		contentPane.add(label_1);

		row = new JTextField();
		row.setBounds(65, 178, 66, 21);
		contentPane.add(row);
		row.setColumns(10);

		column = new JTextField();
		column.setBounds(171, 179, 66, 21);
		contentPane.add(column);
		column.setColumns(10);

		JButton confirm = new JButton("È·ÈÏ¹ºÆ±");
		confirm.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				int i = 0;
				try {
					i = Dao.createOrder(info.id, info.price, df.format(new Date()), Integer.parseInt(row.getText()),
							Integer.parseInt(column.getText()), info.hall_id);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "ÇëÏÈÑ¡Ôñ×ùÎ»");
				}
				if (i == 1) {
					JOptionPane.showMessageDialog(null, "¶©Æ±³É¹¦");
					dispose();
				}
			}
		});
		confirm.setBounds(90, 226, 93, 23);
		contentPane.add(confirm);
	}
}
