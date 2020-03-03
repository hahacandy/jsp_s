package util;

import java.sql.*;

public class DBManager {
	private DBManager() {
	}

	private static DBManager instance = new DBManager();

	public static DBManager getInstance() {
		return instance;
	}

	public Connection getConnection() {
		String myDriver = "oracle.jdbc.driver.OracleDriver";

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "jsl40";
		String password = "1234";

		Connection conn = null;

		try {
			Class.forName(myDriver);
			conn = DriverManager.getConnection(url, user, password);
//			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

}
