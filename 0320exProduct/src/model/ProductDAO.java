package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DAOBase implements ProductDAOImpl{
	
	 private ProductDAO() {}
	 private static ProductDAO instance = new ProductDAO();
	 public static ProductDAO getInstance() {
		 return instance;
	 }
	
	@Override
	public List<ProductVO> getGroupProduct() {
		String query = "SELECT a.gcode, b.gname, a.jnum FROM (SELECT gcode, SUM(jnum) jnum FROM product GROUP BY gcode) a, groupcode b WHERE a.gcode=b.gcode";
		List<ProductVO> list = selectQuery(query, null);
		return list;
	}
	 
	@Override
	public boolean deleteProduct(String code, String pname) {
		int result = 0;
		String query = "DELETE FROM product WHERE code='"+code+"' AND pname='"+pname+"'";
		//System.out.println(query);
		result = updateQuery(query, null);
		return result>0;
	}
	 
	@Override
	public boolean modifyProduct(List<Object> infos) {
		int result = 0;
		String query = "UPDATE product SET cost=?, pnum=?, jnum=?, sale=?, gcode=? WHERE code=? AND pname=?";
		result = updateQuery(query, infos);
		return result>0;
	}
	 
	@Override
	public boolean insertProduct(List<Object> infos) {
		int result = 0;
		String query = "INSERT INTO product VALUES(?,?,?,?,?,?,?)";
		result = updateQuery(query, infos);
		return result>0;
	}
	
	@Override
	public List<ProductVO> getAllProduct() {
		String query = "SELECT A.code, A.pname, a.cost, a.pnum, a.jnum, a.sale, a.gcode, b.gname FROM product a, groupcode b WHERE a.GCODE = b.gcode ORDER BY code";
		List<ProductVO> list = selectQuery(query, null);
		return list;
	}
	
	@Override
	public ProductVO getOneProduct(String code) {
		ProductVO vo = null;
		String query = "SELECT * FROM product WHERE code = '"+code+"'";
		try {
			vo = selectQuery(query, null).get(0);
		} catch (Exception e) {
			System.out.println("ERROR: Product DAO -> getOneProduct()");
		}
		return vo;
	}
	
	
	private List<ProductVO> selectQuery(String query, List<Object> options) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO vo = new ProductVO();
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
				vo = new ProductVO();
				
				try {
					vo.setCode(rs.getString("code"));
				} catch (Exception e) {}
				try {
					vo.setPname(rs.getString("pname"));
				} catch (Exception e) {}
				try {
					vo.setCost(rs.getInt("cost"));
				} catch (Exception e) {}
				try {
					vo.setPnum(rs.getInt("pnum"));
				} catch (Exception e) {}
				try {
					vo.setJnum(rs.getInt("jnum"));
				} catch (Exception e) {}
				try {
					vo.setSale(rs.getInt("sale"));
				} catch (Exception e) {}
				try {
					vo.setGcode(rs.getString("gcode"));
				} catch (Exception e) {}
				try {
					vo.setGname(rs.getString("gname"));
				} catch (Exception e) {}
				
				
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				closeDB(rs, pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	
}
