<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");
%>

<%!
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	Connection GetCon(){
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "jsl40", "1234");
			if(conn != null){
				//System.out.println("db연결 성공!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	boolean modifySales(int custno, int saleno, int pcost, int amount, int price, String pcode, String sdate){
		int result = 0;
		String query = "UPDATE money_tbl set pcost="+pcost+", amount="+amount+", price="+price+", pcode='"+pcode+"', sdate=TO_DATE('"+sdate+"', 'YYYYMMDD') WHERE custno="+custno+" AND saleno="+saleno;
		System.out.println(query);
		
		try{
			conn = GetCon();
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return result>0;
	}
	
	List<String> getOneSales(int saleno){
		List<String> vo = null;
		String query = "SELECT * FROM money_tbl WHERE saleno="+saleno;
		System.out.println(query);
		try{
			conn = GetCon();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				vo = new ArrayList<String>();
				
				vo.add(rs.getString("custno"));
				vo.add(rs.getString("saleno"));
				vo.add(rs.getString("pcost"));
				vo.add(rs.getString("amount"));
				vo.add(rs.getString("price"));
				vo.add(rs.getString("pcode"));
				String sdate = rs.getString("sdate").substring(0,10).replace("-", "");
				vo.add(sdate);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return vo;
	}
	
	List<List> getAllSales(){
		List<List> list = new ArrayList<List>();
		List<String> vo = null;
		
		String query = "SELECT * FROM money_tbl ORDER BY custno";
		System.out.println(query);
		
		try{
			conn = GetCon();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new ArrayList<String>();
				vo.add(rs.getString("custno"));
				vo.add(rs.getString("saleno"));
				vo.add(rs.getString("pcost"));
				vo.add(rs.getString("amount"));
				vo.add(rs.getString("price"));
				vo.add(rs.getString("pcode"));
				vo.add(rs.getString("sdate"));
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return list;
	}
	
	List<List> getGroupSales(){
		List<List> list = new ArrayList<List>();
		List<String> vo = null;
		
		String query = "SELECT custno, SUM(amount) amounts, SUM(price) prices FROM money_tbl GROUP BY custno ORDER BY custno";
		System.out.println(query);
		
		try{
			conn = GetCon();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new ArrayList<String>();
				vo.add(rs.getString("custno"));
				vo.add(rs.getString("amounts"));
				vo.add(rs.getString("prices"));

				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return list;
	}
	
	boolean insertSales(int custno, int saleno, int pcost, int amount, int price, String pcode, String sdate){
		int result = 0;
		
		String query = "INSERT INTO money_tbl VALUES("+custno+", "+saleno+", "+pcost+", "+amount+", "+price+", '"+pcode+"', TO_DATE('"+sdate+"', 'YYYYMMDD'))"; 
		System.out.print(query);
		
		try{
			conn = GetCon();
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return result>0;
	}
	
	
	void closeDB(){
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
	}
%>
