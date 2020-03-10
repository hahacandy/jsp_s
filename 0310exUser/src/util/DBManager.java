package util;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	private DBManager() {}
	private static DBManager instance = new DBManager();
	
	public static DBManager getInstance() {
		return instance;
	}
	
	
	protected Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context env = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)env.lookup("jdbc/myoracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/*
	public Connection getConnection() {
		String myDriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user= "jsl40";
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
	*/
}
