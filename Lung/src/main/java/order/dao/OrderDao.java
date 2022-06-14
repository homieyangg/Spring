package order.dao;

import java.util.List;

import order.model.OrderBean;

public interface OrderDao {
	// 新增訂單
	Object createOrder(OrderBean orderBean);

	// 刪除訂單
	void deleteOrder(Integer od_id);

	// 修改訂單
	void updateOrder(OrderBean orderBean);

	// 查詢訂單
	OrderBean findOrderByid(Integer od_id);

	// Table顯示訂單
	List<OrderBean> showOrderByTable();
	
	void close();
	
	void persist(OrderBean orderBean);
}
