package order.service;

import java.util.List;

import order.model.OrderBean;

public interface OrderService {
	
	Object createOrder(OrderBean orderBean);
	
	void deleteOrder(Integer od_id);
	
	void updateOrder(OrderBean orderBean);
	
	OrderBean findOrderByid(Integer od_id);
	
	List<OrderBean> showOrderByTable();
	
	void close();
}
