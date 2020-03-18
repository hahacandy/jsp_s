package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	
	private MemberDAO() {}
	
	private static MemberDAO instance = new MemberDAO();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	
	private Connection getCon(){

		try{
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "jsl40", "1234");
			if(conn != null){
				//System.out.println("db연결 성공");
			}
				
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public List<MemberVO> getAllMember(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo = null;
		
		String query = "SELECT * FROM member_tbl ORDER BY custno";
		System.out.println(query);
		
		try{
			conn = getCon();
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new MemberVO();
				vo.setCustno(rs.getInt("custno"));
				vo.setCustname(rs.getString("custname"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setJoindate(rs.getString("joindate"));
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
				vo.setCity(rs.getString("city"));
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return list;
	}
	
	public boolean insertMember(int bun, String name, String tel, String addr, String date, String grade, String code){
		int result = 0;
		
		String query = "INSERT INTO member_tbl VALUES(?, ?, ?, ?, TO_DATE(?, 'YYYYMMDD'), ?, ?)";
		System.out.println(query);
		
		try{
			conn = getCon();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bun);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, date);
			pstmt.setString(6, grade);
			pstmt.setString(7, code);
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return result>0;
	}
	
	public boolean modifyMember(int bun, String name, String tel, String addr, String date, String grade, String code){
		int result = 0;
		
		String query = "UPDATE member_tbl SET custname=?, phone=?, address=?, joindate=TO_DATE(?, 'YYYYMMDD'), grade=?, city=? WHERE custno=?";
		System.out.println(query);
		
		try{
			conn = getCon();
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			pstmt.setString(3, addr);
			pstmt.setString(4, date);
			pstmt.setString(5, grade);
			pstmt.setString(6, code);
			pstmt.setInt(7, bun);

			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return result>0;
	}
	
	
	
	public int getCustNo(){
		int custno = -1;
		String query = "SELECT custno FROM member_tbl WHERE ROWNUM=1 ORDER BY custno DESC";
		System.out.println(query);
		try{
			conn = getCon();
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				custno = rs.getInt("custno");
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		if(custno == -1)
			custno = 1;
		else{
			custno += 1;
		}
		
		return custno;
	}
	
	public List<String> getMember(int custno){
		List<String> infos = null;
		String query = "SELECT * FROM member_tbl WHERE custno = "+custno;
		System.out.println(query);
		try{
			conn = getCon();
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				infos = new ArrayList<String>();
				infos.add(rs.getNString("custname"));
				infos.add(rs.getNString("phone"));
				infos.add(rs.getNString("address"));
				infos.add(rs.getNString("joindate"));
				infos.add(rs.getNString("grade"));
				infos.add(rs.getNString("city"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return infos;
	}
	
	private void closeDB(){
		try{
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return;
	}
	
}
