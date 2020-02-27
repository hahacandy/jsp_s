package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GuestDAOManager {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private Connection getConnection() {
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
	
	//셀렉트 쿼리
	protected List<GuestVO> selectQuery(String query, List<String> options) {
		List<GuestVO> guestVOs = new ArrayList<GuestVO>();
		String[] option = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
			
			if(options != null) {
				for(int i=1; i<=options.size(); i++) {
					option = options.get(i-1).split(",");
					if(option[0].equalsIgnoreCase("str"))
						pstmt.setString(i, option[1]);
					else if(option[0].equalsIgnoreCase("int"))
						pstmt.setInt(i, Integer.valueOf(option[1]));
					else 
						System.out.println("쿼리 셀렉트 오류:" + i + " 번째 옵션");
				}
			}
			

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GuestVO guestVO = new GuestVO();
				try {
					guestVO.setIdx(rs.getInt("idx"));
					guestVO.setName(rs.getString("name"));
					guestVO.setEmail(rs.getString("email"));
					guestVO.setRegdate(rs.getString("regdate"));
					guestVO.setSubject(rs.getString("subject"));
					guestVO.setContents(rs.getString("contents"));
					guestVO.setReadcnt(rs.getInt("readcnt"));
					guestVO.setIp(rs.getString("ip"));

				}catch (Exception e) {}

				guestVOs.add(guestVO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeDB();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return guestVOs;
	}
	
	//일반 쿼리
	protected int query(String query, List<String> options) {
		int row = 0;
		String[] option = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
			if(options != null) {
				for(int i=1; i<=options.size(); i++) {
					option = options.get(i-1).split(",");
					if(option[0].equalsIgnoreCase("str")) {
//						System.out.println(option[1]);
						pstmt.setString(i, option[1]);
					}
						
					else if(option[0].equalsIgnoreCase("int"))
						pstmt.setInt(i, Integer.valueOf(option[1]));
					else 
						System.out.println("일반 쿼리 오류:" + i + " 번째 옵션");
				}
			}
			row = pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeDB();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	private void closeDB() throws Exception{
		if(rs != null)
			rs.close();
		if(pstmt != null)
			pstmt.close();
		if(conn != null)
			conn.close();
	}
	
	//db 쿼리에 필요한  리스트에 값 입력기
	public static List<String> inputListInfos(List<Object> IInfos) {
		List<String> infos = new ArrayList<String>();
		for(Object info : IInfos) {
			if(info instanceof String) {
				infos.add("str," + info);
			}else if(info instanceof Integer) {
				infos.add("int," + String.valueOf(info));
			}else {
				System.out.println("리스트 값 입력 오류!");
			}
		}
		return infos;
	}
}
