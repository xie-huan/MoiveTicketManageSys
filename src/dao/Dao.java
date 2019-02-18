package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data.Moive;
import data.Schedule;
import data.Seller;

public class Dao {

	protected static String dbClassName = "com.mysql.cj.jdbc.Driver";
	protected static String dbUrl = "jdbc:mysql://localhost:3306/moivetheater?useSSL=FALSE&serverTimezone=UTC&characterEncoding=utf-8";
	protected static String dbUser = "root";
	protected static String dbPwd = "password";
	private static Connection conn = null;

	// 连接数据库
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

	// 关闭连接
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}

	// 查询数据库
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

	// 更新
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

	// 管理员登录验证
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

	// 插入影片信息
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

	// 查询影片信息
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

	// 删除影片信息
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

	// 更新影片信息
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

	// 售票员注册
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

	// 售票员登录验证
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

	// 所有售票员查询
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

	// 管理员删除售票员
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

	// 管理员更新售票员信息
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

	public static int UpdateSellerByName(String s_username, String s_pwd) {
		int i = 0;
		try {
			String sql = "update seller set Seller_Pwd='" + s_pwd + "'where Seller_Name='" + s_username + "'";
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// 放映信息查询
	public static List selectPlayInfo() {
		List list = new ArrayList();
		String sql = "select * from Schedule natural join Moive";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Schedule playinfo = new Schedule();
				playinfo.id = rs.getInt("Schedule_ID");
				playinfo.moive_name = rs.getString("Moive_Name");
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

	// 删除影片放映信息
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

	// 添加影片放映信息
	public static int insertPlayInfo(int moive_id, int hall_id, float price, String time) {
		int i = 0;
		try {
			// java.sql.Date date=java.sql.Date.valueOf(time);
			String sql = "insert into Schedule(Moive_ID,Hall_ID,Schedule_Price,Schedule_BeginTime) values('" + moive_id
					+ "','" + hall_id + "','" + price + "','" + time + "')";
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// 影片名获取
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

	// 放映厅编号获取
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

	// 根据影片名获取影片编号
	public static int selectMoiveIDFromMoiveName(String moive_name) {
		int id = 0;

		String sql = "select * from Moive where Moive_Name='" + moive_name + "'";
		// System.out.println(sql);
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

	public static List SelectScheduleByID(int id) {
		List list = new ArrayList();
		String sql = "select * from Schedule natural join Moive where Schedule_ID=" + id;
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Schedule playinfo = new Schedule();
				playinfo.id = rs.getInt("Schedule_ID");
				playinfo.moive_name = rs.getString("Moive_Name");
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

	// 更新放映表信息中的价格（暂时只支持价格更改）
	public static int UpdateSchedule(int id, float price) {
		int i = 0;
		try {
			String sql = "update Schedule set Schedule_Price=" + price + " where Schedule_ID=" + id;
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	public static int selectScheduleIDByInfo(int moive_id, int hall_id, float set_price, String time) {
		int schedule_id = 0;

		String sql = "select * from Schedule where Moive_ID='" + moive_id + "'" + " and Hall_ID='" + hall_id
				+ "' and Schedule_Price = '" + set_price + "' and Schedule_BeginTime = '" + time + "'";
		System.out.println(sql);
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				schedule_id = rs.getInt("Schedule_ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return schedule_id;
	}

	public static int creatTableSeatForSchedule(int schedule_id, int hall_id) {
		int i = 0;
		try {
			String sql = "create table seat_" + schedule_id + " select * from Seat where Hall_ID = " + hall_id;
			System.out.println(sql);
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	public static int selectCountOfSeat(int hall_id) {
		String sql = "select count(Seat_ID) as num from Seat where Hall_ID = " + hall_id;
		ResultSet rs = executeQuery(sql);
		int i = 0;
		try {
			rs.next();
			i = rs.getInt("num");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int[][] setSeatSold(int schedule_id, int[][] sold) {
		String sql = "select Seat_IsActive from seat_" + schedule_id;
		System.out.println(sql);
		ResultSet rs = executeQuery(sql);

		int i = 1;
		int j = 1;
		try {
			while (rs.next()) {
				sold[i][j] = rs.getInt("Seat_IsActive");
				j++;
				if (j % 5 == 0) {
					j = 1;
					i++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sold;
	}
}
