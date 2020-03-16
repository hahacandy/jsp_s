package sample.model.board;

import java.util.ArrayList;
import java.util.List;

import sample.model.guest.GuestVO;

public class BoardDAO extends sample.util.ThubanSQLManager{
	private BoardDAO() {}
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	//해당 페이지에 대한 게시물 가져오기
	public List<BoardVO> getAllBoard(int startPost, int endPost){
		List<BoardVO> list = null;
		
		String query = "SELECT * FROM (SELECT  ROWNUM rw, idx, name, subject, regdate, readcnt FROM tbl_board WHERE ROWNUM <= "+endPost+" ORDER BY idx DESC) WHERE rw >= "+startPost+"";
		list = selectQuery(query, null);
		
		return list;
	}
	//해당 페이지에 대한 검색 게시물 가져오기
	public List<BoardVO> getSearchBoard(String search, String key, int startPost, int endPost){
		List<BoardVO> list = null;
		
		String query = "SELECT * FROM (SELECT  ROWNUM rw, idx, name, subject, regdate, readcnt FROM tbl_board WHERE "+search+" LIKE '%"+key+"%' AND ROWNUM <= "+endPost+" ORDER BY idx DESC) WHERE rw >= "+startPost+"";
		
		
		list = selectQuery(query, null);
		
		return list;
	}
	//전체 게시물 카운트
	public int getAllBoardCnt() {
		int cnt = 0;
		String query = "SELECT COUNT(idx) cnt FROM tbl_board";
		cnt = cntQuery(query, null);
		return cnt;
	}
	//검색 게시물 카운트
	public int getSearchBoardCnt(String search, String key) {
		int cnt = 0;
		String query = "SELECT COUNT(idx) cnt FROM tbl_board WHERE "+search+" LIKE '%"+key+"%'";
		cnt = cntQuery(query, null);
		return cnt;
	}
	
	//한개의 게시물 가져오기
	public BoardVO getOneBoardForView(int idx){
		BoardVO vo = null;
		
		String query = "SELECT subject, contents, name, regdate, readcnt FROM tbl_board WHERE idx="+idx;

		vo = selectQuery(query, null).get(0);
		
		return vo;
	}
	
	//한개의 게시물 가져오기
	public BoardVO getOneBoardForModify(int idx){
		BoardVO vo = null;
		
		String query = "SELECT name, email, subject, contents FROM tbl_board WHERE idx="+idx;

		vo = selectQuery(query, null).get(0);
		
		return vo;
	}
	//게시물 조회수 1증가
	public void increaseReadcnt(int idx) {
		String query= "UPDATE tbl_board SET readcnt=readcnt+1 WHERE idx="+idx;
		updateQuery(query, null);
		return;
	}
	
	
	//게시물 쓰기
	public boolean writeBoard(List<Object> infos) {
		int result = 0;
		String query = "INSERT INTO tbl_board(idx, name, email, subject, contents, pass, ip) VALUES(tbl_board_seq_idx.NEXTVAL,?,?,?,?,?,?)";
		result = updateQuery(query, infos);
		
		return result>0;
	}
	
	//게시물 수정
	public boolean modifyBoard(List<Object> infos) {
		int result = 0;
		String query = "UPDATE tbl_board SET email=?, subject=?, contents=? WHERE idx=? AND pass=?";
		result = updateQuery(query, infos);
		
		return result>0;
	}
	
	
	
	
	
	//셀렉트 쿼리
		private List<BoardVO> selectQuery(String query, List<Object> options) {
			List<BoardVO> list = new ArrayList<BoardVO>();
			BoardVO vo = new BoardVO();
			String[] option = null;
			
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(query);
				
				if (options != null) {
					List<String> options2 = inputListInfos(options);
					for (int i = 1; i <= options2.size(); i++) {
						option = options2.get(i - 1).split(",");
						if (option[0].equalsIgnoreCase("str")) {
//							System.out.println(option[1]);
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
					vo = new BoardVO();
					
					try {
						vo.setIdx(rs.getInt("idx"));
					} catch (Exception e) {}
					try {
						vo.setEmail(rs.getString("email"));
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
					try {
						vo.setPass(rs.getString("pass"));
					} catch (Exception e) {}
					try {
						vo.setIp(rs.getString("ip"));
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
