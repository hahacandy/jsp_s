package model;

import java.util.ArrayList;
import java.util.List;

import util.ThubanSQLManager;

public class MemberDAO extends ThubanSQLManager{
	
	private MemberDAO() {}
	
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}
	

	public List<MemberVO> getAllMember(){
		List<MemberVO> list = new ArrayList<MemberVO>();

		String query = "SELECT * FROM member_tbl ORDER BY custno";
		System.out.println(query);
		
		list = selectQuery(query, null);
		
		return list;
	}
	
	public boolean insertMember(List<Object> infos){
		int result = 0;
		
		String query = "INSERT INTO member_tbl VALUES(?, ?, ?, ?, TO_DATE(?, 'YYYYMMDD'), ?, ?)";
		System.out.println(query);
		
		result = updateQuery(query, infos);
		
		return result>0;
	}
	
	public boolean modifyMember(List<Object> infos){
		int result = 0;
		
		String query = "UPDATE member_tbl SET custname=?, phone=?, address=?, joindate=TO_DATE(?, 'YYYYMMDD'), grade=?, city=? WHERE custno=?";
		System.out.println(query);
		
		result = updateQuery(query, infos);

		return result>0;
	}
	
	
	
	public int getNextCustNo(){
		int custno = 0;
		String query = "SELECT custno FROM member_tbl WHERE ROWNUM=1 ORDER BY custno DESC";
		System.out.println(query);
		
		try {
			custno = selectQuery(query, null).get(0).getCustno();
		} catch (Exception e) {
			System.out.println("MemberDAO -> Exception of getNextCustNo()");
		}
		
		custno += 1;

		return custno;
	}
	
	public MemberVO getMember(int custno){
		MemberVO vo = null;
		String query = "SELECT * FROM member_tbl WHERE custno = "+custno;
		System.out.println(query);
		
		try {
			vo = selectQuery(query, null).get(0);
		} catch (Exception e) {
			System.out.println("MemberDAO -> Exception of getMember()");
		}
		
		return vo;
	}
	
	private List<MemberVO> selectQuery(String query, List<Object> options) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo = new MemberVO();
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
				vo = new MemberVO();
				
				try {
					vo.setCustno(rs.getInt("custno"));
				} catch (Exception e) {}
				try {
					vo.setCustname(rs.getString("custname"));
				} catch (Exception e) {}
				try {
					vo.setPhone(rs.getString("phone"));
				} catch (Exception e) {}
				try {
					vo.setAddress(rs.getString("address"));
				} catch (Exception e) {}
				try {
					vo.setJoindate(rs.getString("joindate"));
				} catch (Exception e) {}
				try {
					String grade = rs.getString("grade");
					if(grade.equalsIgnoreCase("A"))
						grade  = "VIP";
					else if(grade.equalsIgnoreCase("B"))
						grade  = "일반";
					else if(grade.equalsIgnoreCase("C"))
						grade  = "직원";
					else
						grade = "?";
					vo.setGrade(grade);
				} catch (Exception e) {}
				try {
					vo.setCity(rs.getString("city"));
				} catch (Exception e) {}
				
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
	
}
