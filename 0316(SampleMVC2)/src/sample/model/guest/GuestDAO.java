package sample.model.guest;

import java.util.ArrayList;
import java.util.List;

public class GuestDAO extends sample.util.ThubanSQLManager{
	
	private GuestDAO() {}
	
	private static GuestDAO instance = new GuestDAO();
	
	public static GuestDAO getInstance() {
		return instance;
	}
	
	//게시물 전체 카운트
	public int getAllGuestCNT() {
		int result = 0;
		String query = "SELECT COUNT(idx) cnt FROM tbl_guest";
		result = cntQuery(query, null);
		
		return result;
	}
	
	//게시물 전체 리스트 
	public List<GuestVO> getAllGuest(int startPost, int endPost){
		List<GuestVO> list = null;
		
		String query = "SELECT * FROM (SELECT  ROWNUM rw, idx, name, subject, regdate, readcnt FROM tbl_guest WHERE ROWNUM <= "+endPost+" ORDER BY idx DESC) WHERE rw >= "+startPost+"";
		list = selectQuery(query, null);
		
		return list;
	}
	
	//게시물 한개
	public GuestVO getOneGuest(int idx) {
		GuestVO vo = null;
		
		String query = "SELECT * FROM tbl_guest WHERE idx=" + idx;
		
		try {
			vo = selectQuery(query, null).get(0);
		} catch (Exception e) {}
		
		return vo;
	}
	
	//게시물 조회수 1증가
	public void increaseReadcnt(int idx) {
		String query = "UPDATE tbl_guest SET readcnt=readcnt+1 WHERE idx="+idx;
		updateQuery(query, null);
		return;
	}
	
	//게시물 등록
	public boolean writeGuest(List<Object> infos) {
		int result = 0;
		String query = "INSERT INTO tbl_guest(idx, name, subject, contents) VALUES(tbl_guest_seq_idx.NEXTVAL,?,?,?)";
		
		result = updateQuery(query, infos);
		
		return result>0;
	}
	
	//게시물 수정
	public boolean modifyGuest(List<Object> infos) {
		int result = 0;
		String query = "UPDATE tbl_guest SET subject=?, contents=? WHERE idx=?";
		
		result = updateQuery(query, infos);
		
		return result>0;
	}
	
	//게시물 삭제
	
	public boolean deleteGuest(int idx) {
		int result = 0;
		String query = "DELETE FROM tbl_guest WHERE idx="+idx;
		
		result = updateQuery(query, null);
		
		return result>0;
	}
	
	
	//셀렉트 쿼리
	private List<GuestVO> selectQuery(String query, List<Object> options) {
		List<GuestVO> list = new ArrayList<GuestVO>();
		GuestVO vo = new GuestVO();
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
				vo = new GuestVO();
				
				try {
					vo.setIdx(rs.getInt("idx"));
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
