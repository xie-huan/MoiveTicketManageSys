package mtmsys;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class Sellersys extends JFrame {
	private JPanel contentPane;
	private String user_name;
	private JTable table;

	public void setUserName(String s)
	{
		user_name = s;
	}
	
	public String getUserName()
	{
		return user_name;
	}
	/**
	 * Create the frame.
	 */
	public Sellersys(String user_name) {
		this.user_name = user_name;
		setTitle("��Ʊ����ϵͳ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 374);
		contentPane = new JPanel();
		contentPane.setToolTipText("��Ӱ��Ϣ��ѯ");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

}
