package order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import order.dao.OrderDao;
import order.dao.impl.OrderDaoImpl;
import order.model.OrderBean;
import order.service.OrderService;
import utils.HibernateUtils;

public class OrderServiceImpl implements OrderService {
	OrderDao dao;
	SessionFactory factory;
	public OrderServiceImpl() {
		dao = new OrderDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Object createOrder(OrderBean orderBean) {
		Object obj = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			obj = dao.createOrder(orderBean);
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public void deleteOrder(Integer od_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.deleteOrder(od_id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateOrder(OrderBean orderBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.updateOrder(orderBean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public OrderBean findOrderByid(Integer od_id) {
		OrderBean ob = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ob = dao.findOrderByid(od_id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return ob;
	}

	@Override
	public List<OrderBean> showOrderByTable() {
		List<OrderBean> allOrder = new ArrayList<OrderBean>();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			allOrder = dao.showOrderByTable();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return allOrder;
	}

	@Override
	public void close() {
		dao.close();
	}

}
