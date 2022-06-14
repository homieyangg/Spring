package order.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import order.dao.OrderDao;
import order.model.OrderBean;
import utils.HibernateUtils;

public class OrderDaoImpl implements OrderDao {
	
	SessionFactory factory;
	
	public OrderDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Object createOrder(OrderBean orderBean) {
		Session session = factory.getCurrentSession();
		Object key = session.save(orderBean);
		return key;
	}

	@Override
	public void deleteOrder(Integer od_id) {
		Session session = factory.getCurrentSession();
		OrderBean oBean = new OrderBean(od_id);
		session.delete(oBean);
	}

	@Override
	public void updateOrder(OrderBean orderBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(orderBean);
	}

	@Override
	public OrderBean findOrderByid(Integer od_id) {
		Session session = factory.getCurrentSession();
		OrderBean oBean = (OrderBean) session.get(OrderBean.class, od_id);
		return oBean;
	}

	@Override
	public List<OrderBean> showOrderByTable() {
		Session session = factory.getCurrentSession();
		List<OrderBean> alList = session.createQuery("FROM OrderBean", OrderBean.class).getResultList();
		return alList;
	}

	@Override
	public void close() {
		factory.close();
	}

	@Override
	public void persist(OrderBean orderBean) {
		Session session = factory.getCurrentSession();
		session.persist(orderBean);
	}

}
