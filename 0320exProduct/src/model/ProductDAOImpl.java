package model;

import java.util.List;

public interface ProductDAOImpl {
	public boolean insertProduct(List<Object> infos);
	public List<ProductVO> getAllProduct();
	public ProductVO getOneProduct(String code);
	public boolean modifyProduct(List<Object> infos);
	public boolean deleteProduct(String code, String pname);
	public List<ProductVO> getGroupProduct();
}
