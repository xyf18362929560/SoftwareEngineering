package nju.express.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class ConnectionFactory {
	private static String driver;
	private static String dburl;
	private static String user;
	private static String password;

	private static final ConnectionFactory FACTORY = new ConnectionFactory();
	private Connection conn;

	static {
		Properties prop = new Properties();

		try {
			InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("dbconfig.properties");
			prop.load(in);
		} catch (IOException e) {
			System.out.println("≈‰÷√Œƒº˛∂¡»°¥ÌŒÛ");
			e.printStackTrace();
		}

		driver = prop.getProperty("driver");
		dburl = prop.getProperty("dburl") + "?useUnicode=true&characterEncoding=UTF-8";
		user = prop.getProperty("user");
		password = prop.getProperty("password");

	}

	private ConnectionFactory() {

	}

	public static ConnectionFactory getInstance() {
		return FACTORY;
	}

	public Connection makeConnection() {

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dburl, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeRes(Connection conn,PreparedStatement ps){
		try {
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void closeRes(Connection conn,PreparedStatement ps,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}