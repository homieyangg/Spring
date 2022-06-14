package product.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import product.model.Productbean;
import utils.HibernateUtils;


public class ProductImplements implements ProductDao {

    SessionFactory factory;

	public ProductImplements() {
	   factory = HibernateUtils.getSessionFactory();
	}
	
	@Override
	public Object createProduct(Productbean productbean) {
		Session session = factory.getCurrentSession();
		Object key = session.save(productbean);
		return key;
	}

	@Override
	public void deleteProduct(Integer pd_id){
		Session session = factory.getCurrentSession();
		Productbean productbean = new Productbean(pd_id);
		session.delete(productbean);
	}

	@Override
	public void updateProduct(Productbean productbean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(productbean);
	}

	@Override
	public  Productbean findProduct(Integer pd_id) {
		Session session = factory.getCurrentSession();
		Productbean productbean = (Productbean) session.get(Productbean.class, pd_id);
		return productbean;
	}

	@Override
	public List<Productbean> showProductByTable() {
		Session session = factory.getCurrentSession();
		List<Productbean> alList = session.createQuery("FROM Productbean", Productbean.class).getResultList();
		return alList;
	}


	@Override
	public void close() {
		factory.close();	
	}

	@Override
	public void persist(Productbean productbean) {
		Session session = factory.getCurrentSession();
		session.persist(productbean);
	}


}
