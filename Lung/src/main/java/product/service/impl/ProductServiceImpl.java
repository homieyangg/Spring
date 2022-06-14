package product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import product.dao.impl.ProductDao;
import product.dao.impl.ProductImplements;
import product.model.Productbean;
import product.service.ProductService;
import utils.HibernateUtils;

public class ProductServiceImpl implements ProductService {

	ProductDao dao;
	SessionFactory factory;
	public ProductServiceImpl() {
		dao = new ProductImplements();
		factory = HibernateUtils.getSessionFactory();
	}
	@Override
	public Object createProduct(Productbean productbean) {
		Object obj = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String pd_items = productbean.getpd_items();
			String pd_product_name = productbean.getpd_product_name();
			String pd_content = productbean.getpd_content();
			String pd_specification = productbean.getpd_specification();
			Integer pd_quantity = productbean.getpd_quantity();
			Integer pd_amount = productbean.getpd_amount();
			Productbean newProduct = new Productbean(pd_items, pd_product_name, pd_content, pd_specification, pd_quantity, pd_amount);
			obj = dao.createProduct(newProduct);
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
	public void deleteProduct(Integer pd_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.deleteProduct(pd_id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateProduct(Productbean Productbean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.updateProduct(Productbean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public Productbean findProduct(Integer pd_id) {
		Productbean ob = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ob = dao.findProduct(pd_id);
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
	public List<Productbean> showProductByTable() {
		List<Productbean> allProduct = new ArrayList<Productbean>();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			allProduct = dao.showProductByTable();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return allProduct;
	}

	@Override
	public void close() {
		dao.close();
		
	}

}
