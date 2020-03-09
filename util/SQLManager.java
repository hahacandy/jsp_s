package util;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SQLManager {
	protected Connection conn = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	

	//톰캣 server.xml 디비 풀 설정하고 디비 커넥션 가져오는 경우 
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
	
	//톰캣없이 자바에서 db
	public Connection getConnection() {
		String myDriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user= "jsl40";
		String password = "1234";
		
		Connection conn = null;
		
		try {
			Class.forName(myDriver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	// 카운트 세는 쿼리
	protected int cntQuery(String query, List<Object> options) {
		int cnt = -1;
		String[] option = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);

			if (options != null) {
				List<String> options2 = inputListInfos(options);
				for (int i = 1; i <= options2.size(); i++) {
					option = options2.get(i - 1).split(",");
					if (option[0].equalsIgnoreCase("str")) {
//						System.out.println(option[1]);
						pstmt.setString(i, option[1]);
					}

					else if (option[0].equalsIgnoreCase("int"))
						pstmt.setInt(i, Integer.valueOf(option[1]));
					else
						System.out.println("카운트 쿼리 오류:" + i + " 번째 옵션");
				}
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				cnt = rs.getInt("cnt");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	// 일반 쿼리
	protected int updateQuery(String query, List<Object> options) {
		int row = 0;
		String[] option = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);

			if (options != null) {
				List<String> options2 = inputListInfos(options);
				for (int i = 1; i <= options2.size(); i++) {
					option = options2.get(i - 1).split(",");
					if (option[0].equalsIgnoreCase("str")) {
//						System.out.println(option[1]);
						pstmt.setString(i, option[1]);
					}

					else if (option[0].equalsIgnoreCase("int"))
						pstmt.setInt(i, Integer.valueOf(option[1]));
					else
						System.out.println("업데이트 쿼리 오류:" + i + " 번째 옵션");
				}
			}
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}

	protected void closeDB() throws Exception {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	// db 쿼리에 필요한 리스트에 값 입력기
	protected List<String> inputListInfos(List<Object> IInfos) {
		List<String> infos = new ArrayList<String>();
		for (Object info : IInfos) {
			if (info instanceof String) {
				infos.add("str," + info);
			} else if (info instanceof Integer) {
				infos.add("int," + String.valueOf(info));
			} else {
				System.out.println("리스트 값 입력 오류!");
			}
		}
		return infos;
	}
	
}
