package model;

import java.util.List;

public class UserDAO extends util.SQLManager{
	private UserDAO() {};
	private static UserDAO instance = new UserDAO();
	
	public static UserDAO getInstance() {
		return instance;
	}
	
	
	//전체 회수 수 카운트 메소드 구현
	public int getUserCount() {
		String query = "SELECT COUNT(*) cnt FROM usertbl";
		int cnt = cntQuery(query, null);
		return cnt;
	}
	
	public boolean regiUser(List<Object> infos) {
		
		String query = "INSERT INTO usertbl(idx, name, userid, passwd, tel, email) VALUES(usertbl_seq_idx.NEXTVAL, ?,?,?,?,?)";
		int result = updateQuery(query, infos);
		
		return result>0;
	
	}
	
	public boolean login(String userid, String passwd) {
		
		String query = "SELECT COUNT(*) cnt FROM usertbl WHERE userid='"+userid+"' AND passwd='"+passwd+"'";
		System.out.println(query);
		int cnt = cntQuery(query, null);
		
		return cnt>0;
	}
}
