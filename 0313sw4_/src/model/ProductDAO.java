package model;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends util.ThubanSQLManager{
	
	private ProductDAO() {}
	
	private static ProductDAO productDAO = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return productDAO;
	}
		

	public int writeProduct(List<Object> infos) {
		int result = -1;
		
		String query = "INSERT INTO product VALUES(?,?,?,?,?,?,?)";
		result = updateQuery(query, infos);
		System.out.println(query);
		return result;
	}
	
	public boolean modifyProduct(List<Object> infos) {
		int result = -1;
		String query = "UPDATE product SET code=?, pname=?, cost=?, pnum=?, jnum=?, sale=?, gcode=? WHERE code='"+(String)infos.get(0)+"'"; 
		System.out.println(query);
		result = updateQuery(query, infos);
		
		return result>0;
	}
	
	public boolean deleteProduct(String code) {
		int result = 0;
		String query = "DELETE FROM product WHERE code='"+code+"'";
		result = updateQuery(query, null);
		return result>0;
	}
	
	
	public ProductVO getProduct(String code) {
		ProductVO vo = null;
		String query = "SELECT * FROM product WHERE code='"+code+"'";
		System.out.println(query);
		try {
			vo = selectQuery(query, null).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	//우선생산 제품
	public List<ProductVO> getFirstProduct() {
		List<ProductVO> list = null;
		String query = "SELECT * FROM product WHERE jnum/pnum*100<=20";
		System.out.println(query);

		list = selectQuery(query, null);
		
		return list;
	}
	
	//이익순위
	public List<ProductVO> getRankProduct() {
		List<ProductVO> list = null;
		String query = "SELECT pname, (jnum*sale)-(jnum*cost) allsale FROM product ORDER BY allsale DESC";
		System.out.println(query);

		list = selectQuery(query, null);
		
		return list;
	}
	
	//그룹별 재고 수량
	public List<ProductVO> getGroupCntProduct() {
		List<ProductVO> list = null;
		String query = "SELECT gcode, SUM(jnum) jnumsum FROM product GROUP BY gcode ORDER BY gcode";
		System.out.println(query);

		list = selectQuery(query, null);
		
		return list;
	}
	
	protected List<ProductVO> selectQuery(String query, List<Object> options) {
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
					vo.setGcode(rs.getString("gcode").trim());
				} catch (Exception e) {}
				try {
					vo.setAllsale(rs.getInt("allsale"));
				} catch (Exception e) {}
				try {
					vo.setAllsale(rs.getInt("jnumsum"));
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
