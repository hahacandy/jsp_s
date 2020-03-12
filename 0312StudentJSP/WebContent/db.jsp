<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

private Connection getConnection() throws Exception{
	Class.forName("oracle.jdbc.OracleDriver");
	Connection con = DriverManager.
			getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "jsl40", "1234");
	return con;
}

private boolean writeStudent(int bun, String name, String gender, int kor, int eng, int mat, String regdate){
	int result = 0;
	String query ="INSERT INTO tbl_score_001 VALUES(?,?,?,?,?,?,?)";
	try{
		conn = getConnection();
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, bun);
		pstmt.setString(2, name);
		pstmt.setString(3, gender);
		pstmt.setInt(4, kor);
		pstmt.setInt(5, eng);
		pstmt.setInt(6, mat);
		pstmt.setString(7, regdate);
		result = pstmt.executeUpdate();
		
	}catch (Exception e){
		e.printStackTrace();
	}finally{
		closeDB();
	}
	
	return result>0;
}

private List<List> getStudents(){
	int result = 0;
	String query ="SELECT * FROM tbl_score_001 ORDER BY bun";
	List<List> list = new ArrayList<List>();
	List<String> vo = null;
	try{
		conn = getConnection();
		pstmt = conn.prepareStatement(query);

		rs = pstmt.executeQuery();
		
		while(rs.next()){
			vo = new ArrayList<String>();
			vo.add(rs.getString("bun"));
			vo.add(rs.getString("name"));
			vo.add(rs.getString("gender"));
			vo.add(rs.getString("kor"));
			vo.add(rs.getString("eng"));
			vo.add(rs.getString("mat"));
			int tot = Integer.valueOf(rs.getString("kor"))+Integer.valueOf(rs.getString("eng"))+Integer.valueOf(rs.getString("mat"));
			vo.add(String.valueOf(tot));
			double avg = Math.round(((double)tot/3)*100)/(double)100;
			vo.add(String.valueOf(avg));
			
			String grade = null;
			if(avg >= 90){
				grade = "수";
			}else if(avg >= 80){
				grade = "우";
			}else if(avg >= 70){
				grade = "미";
			}else if(avg >= 60){
				grade = "양";
			}else{
				grade = "가";
			}
			
			
			vo.add(grade);
			
			list.add(vo);
		}
		
	}catch (Exception e){
		e.printStackTrace();
	}finally{
		closeDB();
	}
	
	return list;
}

private void closeDB(){
	try{
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}

%>

<%
	request.setCharacterEncoding("utf-8");
%>

