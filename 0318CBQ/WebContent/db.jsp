<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
	request.setCharacterEncoding("utf-8");
%> 
    
<%!
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	Connection getCon(){

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
	
	List<List> getAllMember(){
		List<List> members = new ArrayList<List>();
		List<String> infos = null;
		
		String query = "SELECT * FROM member_tbl ORDER BY custno";
		System.out.println(query);
		
		try{
			conn = getCon();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				infos = new ArrayList<String>();
				infos.add(rs.getString("custno"));
				infos.add(rs.getString("custname"));
				infos.add(rs.getString("phone"));
				infos.add(rs.getString("address"));
				infos.add(rs.getString("joindate"));
				String grade = rs.getString("grade");
				if(grade.equals("A"))
					grade  = "VIP";
				else if(grade.equals("B"))
					grade  = "일반";
				else if(grade.equals("C"))
					grade  = "직원";
				else
					grade = "?";
				infos.add(grade);
				infos.add(rs.getString("city"));
				members.add(infos);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return members;
	}
	
	boolean insertMember(int bun, String name, String tel, String addr, String date, String grade, String code){
		int result = 0;
		
		String query = "INSERT INTO member_tbl VALUES("+bun+", '"+name+"', '"+tel+"', '"+addr+"' , TO_DATE('"+date+"', 'YYYYMMDD'), '"+grade+"', '"+code+"')";
		System.out.println(query);
		
		try{
			conn = getCon();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return result>0;
	}
	
	boolean modifyMember(int bun, String name, String tel, String addr, String date, String grade, String code){
		int result = 0;
		
		String query = "UPDATE member_tbl SET custname='"+name+"', phone='"+tel+"', address='"+addr+"' , joindate=TO_DATE('"+date+"', 'YYYYMMDD'), grade='"+grade+"', city='"+code+"' WHERE custno="+bun;
		System.out.println(query);
		
		try{
			conn = getCon();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return result>0;
	}
	
	
	
	int getCustNo(){
		int custno = -1;
		String query = "SELECT custno FROM member_tbl WHERE ROWNUM=1 ORDER BY custno DESC";
		System.out.println(query);
		try{
			conn = getCon();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
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
	
	List<String> getMember(int custno){
		List<String> infos = null;
		String query = "SELECT * FROM member_tbl WHERE custno = "+custno;
		System.out.println(query);
		try{
			conn = getCon();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
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
	
	void closeDB(){
		try{
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

	

%>


