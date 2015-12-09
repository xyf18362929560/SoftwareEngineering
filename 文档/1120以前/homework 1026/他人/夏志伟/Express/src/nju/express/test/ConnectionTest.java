package nju.express.test;

import java.sql.Connection;
import java.sql.SQLException;

import nju.express.util.ConnectionFactory;

public class ConnectionTest {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory cf = ConnectionFactory.getInstance();
		Connection conn = cf.makeConnection();
		System.out.println(conn.getAutoCommit());
	}

}
