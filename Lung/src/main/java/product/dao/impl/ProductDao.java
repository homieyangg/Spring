package product.dao.impl;

import java.util.List;

import product.model.Productbean;

public interface ProductDao {

	// 新增訂單
	Object createProduct(Productbean productbean);

	// 刪除訂單
	void deleteProduct(Integer pd_id);

	// 修改訂單
	void updateProduct(Productbean Productbean);

	// 查詢訂單
    Productbean findProduct(Integer pd_id);

	// Table顯示訂單
	 List<Productbean> showProductByTable();

	void close();

	void persist(Productbean productbean);
}
