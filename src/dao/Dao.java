package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import data.Moive;
import data.Schedule;
import data.Seller;

public class Dao {

	protected static String dbClassName = "com.mysql.cj.jdbc.Driver";
	protected static String dbUrl = "jdbc:mysql://localhost:3306/moivetheater?useSSL=FALSE&serverTimezone=UTC&characterEncoding=utf-8";
	protected static String dbUser = "root";
	protected static String dbPwd = "password";
	private static Connection conn = null;

	// �������ݿ�
	private Dao() {
		try {
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			} else
				return;
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	// �ر�����
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}

	// ��ѯ���ݿ�
	private static ResultSet executeQuery(String sql) {
		try {
			if (conn == null)
				new Dao();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}

	// ����
	private static int executeUpdate(String sql) {
		try {
			if (conn == null)
				new Dao();
			return conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
		}
	}

	// ����Ա��¼��֤
	@SuppressWarnings("finally")
	public static boolean checkLogin(String user, String pwd) {
		String userCheckSQL = "select * from Manager where Manager_Name='" + user + "' and Manager_Pwd='" + pwd + "'";
		System.out.println(userCheckSQL);
		ResultSet rs = executeQuery(userCheckSQL);
		boolean flag = false;
		try {
			if (rs == null)
				flag = false;
			else {
				rs.last();
				if (rs.getRow() < 1) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
			return flag;
		}
	}

	// ����ӰƬ��Ϣ
	public static int InsertMoive(String moiveName, String moiveDirector, String duration, String description) {
		int i = 0;
		try {
			String sql = "insert into Moive(Moive_Name,Moive_Director,Moive_Duration,Moive_Description) values('"
					+ moiveName + "','" + moiveDirector + "','" + duration + "','" + description + "')";
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// ��ѯӰƬ��Ϣ
	public static List selectMoiveInfo() {
		List list = new ArrayList();
		String sql = "select * from Moive";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Moive moiveinfo = new Moive();
				moiveinfo.setId(rs.getInt("Moive_ID"));
				moiveinfo.setName(rs.getString("Moive_Name"));
				moiveinfo.setDir(rs.getString("Moive_Director"));
				moiveinfo.setDuration(rs.getString("Moive_Duration"));
				moiveinfo.setDescription(rs.getString("Moive_Description"));
				list.add(moiveinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// ɾ��ӰƬ��Ϣ
	public static int DeleteMoive(int id) {
		int i = 0;
		try {
			String sql = "delete from Moive where Moive_ID=" + id;
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// ����ӰƬ��Ϣ
	public static int UpdateMoive(int id, String name, String dir, String duration, String description) {
		int i = 0;
		try {
			String sql = "update Moive set Moive_Name='" + name + "',Moive_Director='" + dir + "',Moive_Duration='"
					+ duration + "',Moive_Description='" + description + "' where moive_id='" + id + "'";
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// ��ƱԱע��
	public static int InsertSeller(String user_name, String user_pwd) {
		int i = 0;
		try {
			String sql = "insert into Seller (Seller_Name,Seller_Pwd) values('" + user_name + "','" + user_pwd + "')";
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;

	}

	// ��ƱԱ��¼��֤
	public static boolean checkUserLogin(String user, String pwd) {
		String userCheckSQL = "select * from Seller where Seller_Name='" + user + "' and Seller_Pwd='" + pwd + "'";
		System.out.println(userCheckSQL);
		ResultSet rs = executeQuery(userCheckSQL);
		boolean flag = false;
		try {
			if (rs == null)
				flag = false;
			else {
				rs.last();
				if (rs.getRow() < 1) {
					flag = false;
				} else {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
			return flag;
		}
	}

	// ������ƱԱ��ѯ
	public static List selectSellerInfo() {
		List list = new ArrayList();
		String sql = "select * from Seller";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Seller seller = new Seller();
				seller.s_id = rs.getInt("Seller_ID");
				seller.s_username = rs.getString("Seller_Name");
				seller.s_pwd = rs.getString("Seller_Pwd");
				list.add(seller);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// ����Աɾ����ƱԱ
	public static int DeleteSeller(int id) {
		int i = 0;
		try {
			String sql = "delete from Seller where Seller_ID=" + id;
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// ����Ա������ƱԱ��Ϣ
	public static int UpdateSeller(int id, String s_username, String s_pwd) {
		int i = 0;
		try {
			String sql = "update Seller set Seller_Pwd='" + s_pwd + "',Seller_Name='" + s_username + "' "
					+ "where Seller_ID=" + id;
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// ��ӳ��Ϣ��ѯ
	public static List selectPlayInfo() {
		List list = new ArrayList();
		String sql = "select * from Schedule";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Schedule playinfo = new Schedule();
				playinfo.id = rs.getInt("Schedule_ID");

				int moive_id = rs.getInt("Moive_ID");
				ResultSet getMoiveName = Dao.executeQuery("select * from Moive where Moive_ID=" + moive_id);
				System.out.println("select * from Moive where Moive_ID=" + moive_id);
				getMoiveName.next();
				playinfo.moive_name = getMoiveName.getString("Moive_Name");

				playinfo.hall_id = rs.getInt("Hall_ID");
				playinfo.price = rs.getFloat("Schedule_Price");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				playinfo.time = sdf.format(new Date(rs.getTimestamp(5).getTime()));
				list.add(playinfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// ɾ��ӰƬ��ӳ��Ϣ
	public static int DeletePlayInfo(int id) {
		int i = 0;
		try {
			String sql = "delete from Schedule where Schedule_ID=" + id;
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	//���ӰƬ��ӳ��Ϣ
	public static int insertPlayInfo(int moive_id,int hall_id,float price,String time) {
		int i = 0;
		try {
			//java.sql.Date date=java.sql.Date.valueOf(time);
			String sql = "insert into Schedule(Moive_ID,Hall_ID,Schedule_Price,Schedule_BeginTime) values('"
					+ moive_id + "','" + hall_id + "','" + price + "','" + time + "')";
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	// ӰƬ����ȡ
	public static List selectMoiveName() {
		List list = new ArrayList();
		String sql = "select * from Moive";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				String name = "";
				name = rs.getString("Moive_Name");
				list.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// ��ӳ����Ż�ȡ
	public static List selectHallID() {
		List list = new ArrayList();
		String sql = "select * from Hall";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				String name = "";
				name = rs.getString("Hall_ID");
				list.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	//����ӰƬ����ȡӰƬ���
	public static int selectMoiveIDFromMoiveName(String moive_name) {
		int id = 0;
		
		String sql = "select * from Moive where Moive_Name='"+moive_name+"'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				id = rs.getInt("Moive_ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return id;
	}
	
	//���·�ӳ����Ϣ�еļ۸���ʱֻ֧�ּ۸���ģ�
	public static int UpdateSchedule(int id,float price) {
		int i = 0;
		try {
			String sql = "update Schedule set Schedule_Price=" + price + " where Schedule_ID="+id;
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
}
