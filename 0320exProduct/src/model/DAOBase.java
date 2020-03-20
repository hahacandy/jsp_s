package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOBase implements DAO{
	@Override
	public Connection getConnection() throws SQLException {
		
		String jdbc_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:XE";
		try {
			Class.forName(jdbc_driver);
			Connection conn = DriverManager.getConnection(db_url, "jsl40", "1234");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	// 카운트 세는 쿼리
	protected int cntQuery(String query, List<Object> options) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
				closeDB(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	// 일반 쿼리
	protected int updateQuery(String query, List<Object> options) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
				closeDB(rs, pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}


	// db 쿼리에 필요한 리스트에 값 입력기
	protected List<String> inputListInfos(List<Object> IInfos) {
		List<String> infos = new ArrayList<String>();
		int cnt = 0;
		for (Object info : IInfos) {
			cnt++;
			if (info instanceof String) {
				infos.add("str," + info);
			} else if (info instanceof Integer) {
				infos.add("int," + String.valueOf(info));
			} else {
				System.out.println(cnt + "번째 리스트 값 입력 오류: " + String.valueOf(info));
			}
		}
		return infos;
	}
	
	
	protected void closeDB(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		
		try {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
