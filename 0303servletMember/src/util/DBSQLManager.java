package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.MemberVO;

public class DBSQLManager {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 셀렉트 쿼리
	protected List<MemberVO> selectQuery(String query, List<Object> options) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		String[] option = null;

		try {
			conn = DBManager.getInstance().getConnection();
			pstmt = conn.prepareStatement(query);

			if (options != null) {
				List<String> options2 = inputListInfos(options);
				
				for (int i = 1; i <= options2.size(); i++) {
					option = options2.get(i - 1).split(",");
					if (option[0].equalsIgnoreCase("str"))
						pstmt.setString(i, option[1]);
					else if (option[0].equalsIgnoreCase("int"))
						pstmt.setInt(i, Integer.valueOf(option[1]));
					else
						System.out.println("쿼리 셀렉트 오류:" + i + " 번째 옵션");
				}
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberVO vo = new MemberVO();
				try {
					vo = new MemberVO();
					vo.setIdx(rs.getInt("idx"));
					vo.setName(rs.getString("name"));
					vo.setUserid(rs.getString("userid"));
					vo.setTel(rs.getString("tel"));
					vo.setFirst_time(rs.getString("first_time"));
					String last_time = rs.getString("last_time").substring(0,10);
					if(!last_time.equals("1000-01-01"))
						vo.setLast_time(rs.getString("last_time"));
					else
						vo.setLast_time("정보 없음");

				} catch (Exception e) {
					e.printStackTrace();
				}

				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeDB();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return list;
	}

	// 일반 쿼리
	protected int query(String query, List<Object> options) {
		int row = 0;
		String[] option = null;

		try {
			conn = DBManager.getInstance().getConnection();
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
						System.out.println("일반 쿼리 오류:" + i + " 번째 옵션");
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

	private void closeDB() throws Exception {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}

	// db 쿼리에 필요한 리스트에 값 입력기
	public static List<String> inputListInfos(List<Object> IInfos) {
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
