package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.util.DBManager;

public class BoardDAO {
	DBManager manager = DBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 게시글 전체 카운트 메소드
	public int getAllBoardCnt() {
		String query = "SELECT COUNT(idx) AS cnt FROM tbl_board";
		int cnt = 0;
		try {
			conn = manager.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next())
				cnt = rs.getInt("cnt");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return cnt;
	}
	
	// 게시글 전체 목록 메소드
		public List<BoardVO> getAllBoardPost() {
			String query = "SELECT * FROM tbl_board ORDER BY idx DESC";
			List<BoardVO> list = new ArrayList<BoardVO>();
			BoardVO boardVO = null;
			
			try {
				conn = manager.getConnection();
				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()){
					boardVO = new BoardVO();
					boardVO.setIdx(rs.getInt("idx"));
					boardVO.setName(rs.getString("name"));
					boardVO.setSubject(rs.getString("subject"));
					boardVO.setRegdate(rs.getString("regdate"));
					boardVO.setReadcnt(rs.getInt("Readcnt"));
					list.add(boardVO);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return list;
		}
		
		// 게시글 등록 메소드
		public int writePost(BoardVO vo) {
			String query = "INSERT INTO tbl_board(idx, name, email, subject, contents, pass) "
					+ "VALUES(tbl_board_seq_idx.NEXTVAL, ?, ?, ?, ?, ?)";
			int row = 0;
			
			try {
				conn = manager.getConnection();
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getEmail());
				pstmt.setString(3, vo.getSubject());
				pstmt.setString(4, vo.getContents());
				pstmt.setString(5, vo.getPass());
				
				row = pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return row;
		}
		
		//한 게시물이 정보 불러오기 메소드
		public BoardVO getOneBoardPost(int idx) {
			String query = "SELECT * FROM tbl_board WHERE idx=?";
			BoardVO boardVO = null;
			
			try {
				conn = manager.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, idx);
				
				rs = pstmt.executeQuery();

				if(rs.next()) {
					boardVO = new BoardVO();
					boardVO.setIdx(rs.getInt("idx"));
					boardVO.setName(rs.getString("name"));
					boardVO.setEmail(rs.getString("email"));
					boardVO.setSubject(rs.getString("subject"));
					boardVO.setContents(rs.getString("contents"));
					boardVO.setRegdate(rs.getString("regdate"));
					boardVO.setReadcnt(rs.getInt("Readcnt"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return boardVO;
		}
		
		//조회수 1증가
		public void upReadCnt(int idx) {
			String query = "UPDATE tbl_board SET readcnt=readcnt+1 WHERE idx=?";
			
			try {
				conn = manager.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, idx);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		//게시물 삭제 메소드
		public int deletePost(int idx, String pass) {
			String query = "DELETE FROM tbl_board WHERE idx=? AND pass=?";
			int row = 0;
			try {
				conn = manager.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, idx);
				pstmt.setString(2, pass);
				row = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return row;
		}
		
		//게시글 수정
		public int modifyPost(BoardVO vo) {
			String query = "UPDATE tbl_board SET email=?, subject=?, contents=? WHERE idx=? AND pass=?";
			int row = 0;
			try {
				conn = manager.getConnection();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, vo.getEmail());
				pstmt.setString(2, vo.getSubject());
				pstmt.setString(3, vo.getContents());
				pstmt.setInt(4, vo.getIdx());
				pstmt.setString(5, vo.getPass());
				row = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			return row;
		}
		
}
