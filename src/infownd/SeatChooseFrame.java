package infownd;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Dao;

public class SeatChooseFrame extends JFrame {

	private JPanel contentPane;
	private static int[][] sold;

	/**
	 * Create the frame.
	 */
	public SeatChooseFrame(int schedule_id, int hall_id) {
		setTitle("\u5EA7\u4F4D\u9009\u62E9\u9875\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		int count = Dao.selectCountOfSeat(hall_id);
		if (count == 16) {
			sold = new int[5][5];
			sold = Dao.setSeatSold(schedule_id, sold);

			JButton r1c1 = new JButton("1,1");
			click(r1c1);
			r1c1.setBounds(25, 29, 51, 37);
			contentPane.add(r1c1);

			JButton r1c2 = new JButton("1,2");
			click(r1c2);
			r1c2.setBounds(98, 29, 51, 37);
			contentPane.add(r1c2);

			JButton r1c3 = new JButton("1,3");
			click(r1c3);
			r1c3.setBounds(170, 29, 51, 37);
			contentPane.add(r1c3);

			JButton r1c4 = new JButton("1,4");
			click(r1c4);
			r1c4.setBounds(245, 29, 51, 37);
			contentPane.add(r1c4);

			JButton r2c1 = new JButton("2,1");
			click(r2c1);
			r2c1.setBounds(25, 76, 51, 37);
			contentPane.add(r2c1);

			JButton r2c2 = new JButton("2,2");
			click(r2c2);
			r2c2.setBounds(98, 76, 51, 37);
			contentPane.add(r2c2);

			JButton r2c3 = new JButton("2,3");
			click(r2c3);
			r2c3.setBounds(170, 76, 51, 37);
			contentPane.add(r2c3);

			JButton r2c4 = new JButton("2,4");
			click(r2c4);
			r2c4.setBounds(245, 76, 51, 37);
			contentPane.add(r2c4);

			JButton r3c1 = new JButton("3,1");
			click(r3c1);
			r3c1.setBounds(25, 123, 51, 37);
			contentPane.add(r3c1);

			JButton r3c2 = new JButton("3,2");
			click(r3c2);
			r3c2.setBounds(98, 123, 51, 37);
			contentPane.add(r3c2);

			JButton r3c3 = new JButton("3,3");
			click(r3c3);
			r3c3.setBounds(170, 123, 51, 37);
			contentPane.add(r3c3);

			JButton r3c4 = new JButton("3,4");
			click(r3c4);
			r3c4.setBounds(245, 123, 51, 37);
			contentPane.add(r3c4);

			JButton r4c1 = new JButton("4,1");
			click(r4c1);
			r4c1.setBounds(25, 170, 51, 37);
			contentPane.add(r4c1);

			JButton r4c2 = new JButton("4,2");
			click(r4c2);
			r4c2.setBounds(98, 170, 51, 37);
			contentPane.add(r4c2);

			JButton r4c3 = new JButton("4,3");
			click(r4c3);
			r4c3.setBounds(170, 170, 51, 37);
			contentPane.add(r4c3);

			JButton r4c4 = new JButton("4,4");
			click(r4c4);
			r4c4.setBounds(245, 170, 51, 37);
			contentPane.add(r4c4);
		}
	}

	private void click(JButton jb) {
		String s = jb.getText();
		int row = Integer.parseInt(s.substring(0, 1));
		int column = Integer.parseInt(s.substring(2, 3));
		if (sold[row][column] == 1) {
			jb.setBackground(Color.RED);
			jb.setText("S");
		}
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderAddFrame.row.setText(s.substring(0, 1));
				OrderAddFrame.column.setText(s.substring(2, 3));
				dispose();
			}
		});
	}
}
