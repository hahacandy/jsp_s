package model.member;

import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class MemberDAO extends util.SQLManager{
	

	
	
	//로그인 1 (유저의 아이디, 비밀번호를 가지고 맞는지 확인)
	public MemberVO login(String userid, String passwd) {
		MemberVO vo = null;
		int cnt = 0;
		String query = "SELECT COUNT(userid) AS cnt FROM tbl_member WHERE userid='" +userid+ "' AND passwd='"+passwd+"'";
		cnt = cntQuery(query, null);
		if(cnt > 0) {
			vo = getLoginUserInfo(userid);
		}
		return vo;
	}
	//로그인 2 (로그인 성공한 유저의 정보를 가져온다)
	public MemberVO getLoginUserInfo(String userid) {
		MemberVO vo = null;
		String query = "SELECT name, userid, tel, email FROM tbl_member WHERE userid=?";

		try {
			conn = DBManager.getInstance().getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new MemberVO();
				vo.setName(rs.getString("name"));
				vo.setUserid(rs.getString("userid"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	//회원정보 삭제 
	public int deleteUser(String userid, String passwd) {
		int result = 0;
		String query = "DELETE FROM tbl_member WHERE userid='"+userid+"' AND passwd='"+passwd+"'";
		result = updateQuery(query, null);
		
		return result;
	}
	
	
	//회원정보 수정 비밀번호 없이
	public int modifyMember(List<Object> infos) {
		int result = 0;
		String query = "UPDATE tbl_member SET tel=?, email=?";
		
		result = updateQuery(query, infos);

		return result;
	}
	
	//회원정보 수정 비밀번호 포함
	public int modifyMemberP(List<Object> infos) {
		int result = 0;
		String query = "UPDATE tbl_member SET tel=?, email=?, passwd=?";
		
		result = updateQuery(query, infos);

		return result;
	}
	
	//회원가입
	public int signUpMember(List<Object> infos) {
		int result = 0;
		String query = "INSERT INTO tbl_member(idx, name, userid, passwd, tel, email) VALUES(tbl_member_seq_idx.NEXTVAL,?,?,?,?,?)";
		
		result = updateQuery(query, infos);

		return result;
	}
	
	//아이디 중복체크 확인
	public boolean CheckOverLapId(String userid) {
		int cnt = 0;
		String query = "SELECT COUNT(userid) AS cnt FROM tbl_member WHERE userid='"+userid+"'";

		cnt = cntQuery(query, null);
		
		return cnt>0;
	}
	
}
