package Animal.dao;

import java.util.List;

import Animal.bean.*;

public interface AnimalDao {
	// 新增訂單
	Object createabdog(AbDogBean abdogbean);

	// 刪除訂單
	void deleteabdog(Integer id);

	// 修改訂單
	void updateabdog(AbDogBean abdogbean);

	// 查詢訂單
	AbDogBean findById(Integer id);

	// Table顯示訂單
	List<AbDogBean> findAll();
	
	void close();
	
	void persist(AbDogBean abdogbean);
}
