package model;

import java.util.List;

public class GuestDAO extends GuestDAOManager{
	
	private GuestDAO() {}
	private static GuestDAO instance = new GuestDAO();//객체 생성 싱글톤
	
	public static GuestDAO getInstance() {
		return instance;
	}
	
	//모든 게시글 다 불러오기
	public List<GuestVO> getAllPost(){
		String query = "SELECT * FROM tbl_board ORDER BY idx DESC";
		List<GuestVO> list = selectQuery(query, null);
		return list;
	}
	
	//하나의 게시글 불러오기
	public GuestVO getOnePost(int idx){
		String query = "SELECT * FROM tbl_board WHERE idx=" + idx;
		GuestVO vo = null;
		try {
			vo = selectQuery(query, null).get(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	//조회수 1추가하기
	public int plusReadCnt(int idx){
		int row = 0;
		String query = "UPDATE tbl_board SET readcnt=readcnt+1 WHERE idx=" + idx;
		row = query(query, null);
		return row;
	}
	
	//게시글 저장하기
	public int writePost(List<String> infos){
		int row = 0;
		String query = "INSERT INTO tbl_board(idx, name, email, subject, contents, pass, ip) VALUES(tbl_board_seq_idx.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		row = query(query, infos);
		return row;
	}
	
	//게시글 수정하기
	public int modifyPost(List<String> infos){
		int row = 0;
		String query = "UPDATE tbl_board SET email=?, subject=?, contents=? WHERE idx=? AND pass=?";
		row = query(query, infos);
		return row;
	}
	
	//게시글 삭제하기
	public int deletePost(int idx, String pass) {
		int row = 0;
		String query = "DELETE FROM tbl_board WHERE idx=" + idx + " AND pass='" + pass + "'";
		row = query(query, null);
		return row;
	}
}
