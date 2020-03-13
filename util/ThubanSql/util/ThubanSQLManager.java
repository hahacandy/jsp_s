package util;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ThubanSQLManager {
	protected Connection conn = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	

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

	protected void closeDB(){
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
	
	//셀렉트 쿼리
	/*
		protected List<ProductVO> selectQuery(String query, List<Object> options) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO vo = new ProductVO();
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
						System.out.println("셀렉트 쿼리 오류:" + i + " 번째 옵션");
				}
			}

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new ProductVO();
				
				vo.setCode(rs.getString("code").trim());
				vo.setPname(rs.getString("pname").trim());
				vo.setCost(Integer.valueOf(rs.getString("cost")));
				vo.setPnum(Integer.valueOf(rs.getString("pnum")));
				vo.setJnum(Integer.valueOf(rs.getString("jnum")));
				vo.setSale(Integer.valueOf(rs.getString("sale")));
				vo.setGcode(rs.getString("gcode").trim());

				list.add(vo);
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
		
		return list;
	}
	*/

}
