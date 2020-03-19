package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {
	
	private SalesDAO() {}
	
	private static SalesDAO instance = new SalesDAO();
	
	public static SalesDAO getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Connection GetCon(){
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
	
	public boolean modifySales(int custno, int saleno, int pcost, int amount, int price, String pcode, String sdate){
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
	
	public SalesVO getOneSales(int saleno){
		SalesVO vo = null;
		String query = "SELECT * FROM money_tbl WHERE saleno="+saleno;
		System.out.println(query);
		try{
			conn = GetCon();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				vo = new SalesVO();
				
				vo.setCustno(Integer.valueOf(rs.getString("custno")));
				vo.setSaleno(Integer.valueOf(rs.getString("saleno")));
				vo.setPcost(Integer.valueOf(rs.getString("pcost")));
				vo.setAmount(Integer.valueOf(rs.getString("amount")));
				vo.setPrice(Integer.valueOf(rs.getString("price")));
				vo.setPcode(rs.getString("pcode"));
				String sdate = rs.getString("sdate").substring(0,10).replace("-", "");
				vo.setSdate(sdate);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		return vo;
	}
	
	public List<SalesVO> getAllSales(){
		List<SalesVO> list = new ArrayList<SalesVO>();
		SalesVO vo = null;
		
		String query = "SELECT * FROM money_tbl ORDER BY custno";
		System.out.println(query);
		
		try{
			conn = GetCon();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new SalesVO();
				
				vo.setCustno(Integer.valueOf(rs.getString("custno")));
				vo.setSaleno(Integer.valueOf(rs.getString("saleno")));
				vo.setPcost(Integer.valueOf(rs.getString("pcost")));
				vo.setAmount(Integer.valueOf(rs.getString("amount")));
				vo.setPrice(Integer.valueOf(rs.getString("price")));
				vo.setPcode(rs.getString("pcode"));
				vo.setSdate(rs.getString("sdate"));
				
				list.add(vo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return list;
	}
	
	public List<List> getGroupSales(){
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
	
	public boolean insertSales(int custno, int saleno, int pcost, int amount, int price, String pcode, String sdate){
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
	}
}
