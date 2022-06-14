package Animal.service;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Animal.bean.*;
import Animal.dao.*;
import Animal.service.*;
import Animal.controller.*;
import Animal.Daoimpl.*;
import utils.HibernateUtils;



public class AbDogServiceimpl  implements AbDogService{
	private static final String AbDogBean = null;
	AnimalDao dao;
	SessionFactory factory;
	public AbDogServiceimpl() {
		dao = new AnimalDaoimpl();
		factory = HibernateUtils.getSessionFactory();
	}



	@Override
	public Object createabdog(AbDogBean abdogbean) {
		Object obj = dao;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		
		
		try {
			tx = session.beginTransaction();
			obj = dao.createabdog(abdogbean);
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
	public void deleteabdog(Integer id) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.deleteabdog(id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateabdog(AbDogBean abdogbean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.updateabdog(abdogbean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
	}


	@Override
	public AbDogBean findById(Integer id) {
		AbDogBean abdogbean = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			abdogbean = dao.findById(id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return abdogbean;
	}

	@Override
	public List<AbDogBean> findAll() {
		List<AbDogBean> allabdog = new ArrayList<AbDogBean>();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			allabdog = dao.findAll();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return allabdog;
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		dao.close();

	}

	
	
}
