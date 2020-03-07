package model.board;

import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class BoardDAO extends util.SQLManager{
	
	//--LIST START--//
	
	//가장 최근의 게시물 count만큼 불러옴
	public List<BoardVO> getPost(int count) {

		List<BoardVO> list = new ArrayList<BoardVO>();
		String query = "SELECT * FROM (SELECT idx, subject, contents, name, regdate, readcnt FROM tbl_board WHERE ROWNUM <= "+count+" ORDER BY idx DESC)";

		list = selectQuery(query, null);
		
		return list;
	}
	
	//페이지에 맞는 게시글을 count 만큼 불러옴
	public List<BoardVO> getPost(int currentPage, int count) {
		int startPost = (currentPage*count)-(count-1);
		int endPost = (currentPage*count);
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		String query = "SELECT * FROM (SELECT ROWNUM rw, idx, subject, contents, name, regdate, readcnt FROM tbl_board WHERE ROWNUM <= "+endPost+" ORDER BY idx DESC) WHERE rw >="+startPost;

		list = selectQuery(query, null);
		
		return list;
	}
	//검색하여 나오는 페이지에 맞는 게시글을 count 만큼 불러옴
	public List<BoardVO> getPost(int currentPage, int count, String search, String key) {
		int startPost = (currentPage*count)-(count-1);
		int endPost = (currentPage*count);
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		String query = "SELECT * FROM (SELECT ROWNUM rw, idx, subject, contents, name, regdate, readcnt FROM tbl_board WHERE "+search+" LIKE '%"+key+"%' AND ROWNUM <= "+endPost+" ORDER BY idx DESC) WHERE rw >="+startPost;

		list = selectQuery(query, null);
		
		return list;
	}
	//전체 게시글 갯수
	public int getPostCount() {
		int cnt = 0;
		String query = "SELECT COUNT(idx) AS cnt FROM tbl_board";

		cnt = cntQuery(query, null);
		
		return cnt;
	}
	//검색 게시글 갯수
	public int getPostCount(String search, String key) {
		int cnt = 0;
		String query = "SELECT COUNT(idx) AS cnt FROM tbl_board WHERE "+search+" LIKE '%"+key+"%'";

		cnt = cntQuery(query, null);
		
		return cnt;
	}
	//--LIST END--//
	
	//--WRITE START--//
	
	//게시글 작성
	public boolean writePost(List<Object> infos) {
		int result = 0;
		String query = "INSERT INTO tbl_board(idx, pass, name, email, subject, contents, ip) VALUES(tbl_board_seq_idx.NEXTVAL,'user',?,?,?,?,?)";
		result = updateQuery(query, infos);
		return result>0;
	}
	//--WRITE END--//
	
	//--VIEW START--//
	
	//하나의 게시물 정보를 읽어옴
	public BoardVO getOnePost(int idx) {
		BoardVO vo = new BoardVO();
		String query = "SELECT * FROM tbl_board WHERE idx="+idx;
		
		//혹시나해서 try걸어줌
		try {
			vo = selectQuery(query, null).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	//해당 게시물 조회수 1증가
	public void increasePostReadCnt(int idx) {
		String query = "UPDATE tbl_board SET readcnt=readcnt+1 WHERE idx="+idx;
		updateQuery(query, null);
	}
	
	
	//--VIEW END--//
	
	
	//--MODIFY START--//
	//게시글 수정
	public boolean modifyPost(List<Object> infos) {
		int row = 0;
		String query = "UPDATE tbl_board SET subject=?, contents=? WHERE idx=? AND ip=?";
		row = updateQuery(query, infos);
		return row>0;
	}
	
	//--MODIFY END--//
	
	//--DELETE START--//
	//게시글 삭제
	public boolean deletePost(int idx, String userid) {
		int row = 0;
		String query ="DELETE FROM tbl_board WHERE idx="+idx+" AND ip='"+userid+"'";
		row = updateQuery(query, null);
		return row>0;
	}
	//--DELETE END--//
	
	
	
	
	
	// 셀렉트 쿼리
	protected List<BoardVO> selectQuery(String query, List<Object> options) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo = null;
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

			while(rs.next()) {
				vo = new BoardVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setSubject(rs.getString("subject"));
				vo.setContents(rs.getString("contents"));
				vo.setName(rs.getString("name"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setReadcnt(rs.getInt("readcnt"));
				
				try {
					vo.setEmail(rs.getString("email"));
				} catch (Exception e) {}
				try {
					vo.setIp(rs.getString("ip"));
				} catch (Exception e) {}
				
				
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
	
	

}
