package util;

import java.sql.*;
import java.util.*;

public class ThubanSQLManager {

	//DBCP POOL 을 사용한 DB접속
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DBConn.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
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

			inputPstmt(pstmt, options);
			
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

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
			inputPstmt(pstmt, options);
			
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
				infos.add("str,!#!," + info);
			} else if (info instanceof Integer) {
				infos.add("int,!#!," + String.valueOf(info));
			} else {
				System.out.println(cnt + "번째 리스트 값 입력 오류: " + String.valueOf(info));
			}
		}
		return infos;
	}
	
	protected void inputPstmt(PreparedStatement pstmt, List<Object> options) throws Exception{
		
		if (options != null) {
			
			String[] option = null;
			
			List<String> options2 = inputListInfos(options);
			for (int i = 1; i <= options2.size(); i++) {
				option = options2.get(i - 1).split(",!#!,");
				if (option[0].equalsIgnoreCase("str")) {
//					System.out.println(option[1]);
					pstmt.setString(i, option[1]);
				}

				else if (option[0].equalsIgnoreCase("int"))
					pstmt.setInt(i, Integer.valueOf(option[1]));
				else
					System.out.println("쿼리 매개변수 오류:" + i + " 번째 옵션");
			}
		}
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
	
	//셀렉트 쿼리
	/*
	private List<GuestVO> selectQuery(String query, List<Object> options) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<GuestVO> list = new ArrayList<GuestVO>();
		GuestVO vo = new GuestVO();
		String[] option = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
			inputPstmt(pstmt, options);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new GuestVO();
				
				try {
					vo.setIdx(rs.getInt("idx"));
				} catch (Exception e) {}
				try {
					vo.setName(rs.getString("name"));
				} catch (Exception e) {}
				try {
					vo.setSubject(rs.getString("subject"));
				} catch (Exception e) {}
				try {
					vo.setContents(rs.getString("contents"));
				} catch (Exception e) {}
				try {
					vo.setRegdate(rs.getString("regdate"));
				} catch (Exception e) {}
				try {
					vo.setReadcnt(rs.getInt("readcnt"));
				} catch (Exception e) {}
				
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeDB(rs, pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	*/

}
