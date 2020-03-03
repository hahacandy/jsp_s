package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;
import util.DBSQLManager;

public class MemberDAO extends DBSQLManager{
	
	public List<ZIPcodeVO> getSearchZip(String addr){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ZIPcodeVO> list = new ArrayList<ZIPcodeVO>();
		ZIPcodeVO vo = null;
		
		String query = "SELECT * FROM zipcode WHERE dong LIKE '%"+addr+"%'";
		
		try {
			conn = DBManager.getInstance().getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new ZIPcodeVO();
				
				vo.setZipcode(rs.getString("zipcode"));
				vo.setSido(rs.getString("sido"));
				vo.setGugun(rs.getString("gugun"));
				vo.setDong(rs.getString("dong"));
				vo.setBunji(rs.getString("bunji"));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	//아이디 비번을 가지고 로그인 체크
	public int checkLogin(String userid, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = -1; //-1이면 아이디가 없음, 0이면 비밀번호가 틀림, 1이면 로그인 성공
		
		String query = "SELECT passwd FROM tbl_member WHERE userid=?";
		
		try {
			conn = DBManager.getInstance().getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String passwdSQL = rs.getString("passwd");
				if(passwd.equals(passwdSQL)) {
					result = 1;
					
					//마지막 접속시간 저장
					pstmt.close();
					query = "UPDATE tbl_member SET last_time=SYSDATE WHERE userid=?";
					pstmt= conn.prepareStatement(query);
					pstmt.setString(1, userid);
					pstmt.executeUpdate();
				}else {
					result = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	//아이디로 검색한 회원정보 불러오기
	public MemberVO getSearchUserid(String userid){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVO memberVO = new MemberVO();
		
		String query = "SELECT * FROM tbl_member WHERE userid=?";
		
		try {
			conn = DBManager.getInstance().getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberVO.setName(rs.getString("name"));
				memberVO.setUserid(rs.getString("userid"));
				memberVO.setPasswd(rs.getString("passwd"));
				memberVO.setGubun(rs.getString("gubun"));
				memberVO.setZipcode(rs.getString("zipcode"));
				memberVO.setAddr1(rs.getString("addr1"));
				memberVO.setAddr2(rs.getString("addr2"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setTel(rs.getString("tel"));
				memberVO.setJob(rs.getString("job"));
				memberVO.setIntro(rs.getString("Intro"));
				memberVO.setFavorite(rs.getString("favorite"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return memberVO;
	}
	
	
	//이름으로 검색한 회원정보 불러오기
	public List<MemberVO> getSearchMember(String name, String order){
		
		List<MemberVO> memberVOs = null;
		
		String query = "SELECT idx, userid, name, tel, first_time, NVL(last_time, TO_DATE('10000101', 'YYYYMMDD')) last_time "
				+ "FROM tbl_member WHERE name='"+name+"' ORDER BY " + order;
		
		memberVOs = selectQuery(query, null);
		
		return memberVOs;
	}
	
	//회원정보 전체 불러오기
	public List<MemberVO> getAllMember(String order){
		
		List<MemberVO> memberVOs = null;
		
		String query = "SELECT idx, userid, name, tel, first_time, NVL(last_time, TO_DATE('10000101', 'YYYYMMDD')) last_time "
				+ "FROM tbl_member ORDER BY " + order;
		
		memberVOs = selectQuery(query, null);
		
		return memberVOs;
	}
	
	//회원정보 수정 메소드
	public int modifyMember(List<Object> infos) {
		boolean result = false;
		
		int row = 0;
		
		String query = "UPDATE tbl_member SET passwd=?, gubun=?, zipcode=?, addr1=?, addr2=?, tel=?, email=?, favorite=?, job=?, intro=? WHERE userid=?";
		row = query(query, infos);
		
		return row;
	}
	
	//회원 정보 등록 메소드
	public boolean memberInsert(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO tbl_member(idx, name, userid, passwd, gubun, zipcode, addr1, addr2, tel, email, job, intro, favorite) "
				+ "VALUES(tbl_member_seq_idx.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean flag=false;
		
		try {
			conn = DBManager.getInstance().getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getPasswd());
			pstmt.setString(4, vo.getGubun());
			pstmt.setString(5, vo.getZipcode());
			pstmt.setString(6, vo.getAddr1());
			pstmt.setString(7, vo.getAddr2());
			pstmt.setString(8, vo.getTel());
			pstmt.setString(9, vo.getEmail());
			pstmt.setString(10, vo.getJob());
			pstmt.setString(11, vo.getIntro());
			pstmt.setString(12, vo.getFavorite());
			
			flag = pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return flag;
	}
	
	//id중복 검사 메소드
	public int checkMemberID(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(userid) AS cnt FROM tbl_member WHERE userid=?";
		int cnt=0;
		
		try {
			conn = DBManager.getInstance().getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next())
				cnt = rs.getInt("cnt");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	//우편번호 검색 메소드
	
	//회원 전체 목록 검색 메소드
	
	//로그인(id, pass) 체크 메소드
	
	//id또는 기본키에 해당하는 회원 정보 검색 메소드
	
	//회원정보 수정 메소드
	
}
