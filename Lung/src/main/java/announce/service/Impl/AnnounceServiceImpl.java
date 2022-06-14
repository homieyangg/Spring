package announce.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import announce.dao.AnnounceDAO;
import announce.dao.Impl.AnnounceDAOimpl;
import announce.model.AnnounceBean;
import announce.service.AnnounceService;
import utils.HibernateUtils;

public class AnnounceServiceImpl implements AnnounceService {
	
	AnnounceDAO announceDao;
	SessionFactory factory;
	
	public AnnounceServiceImpl() {
		announceDao = new AnnounceDAOimpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void createAnnounce(AnnounceBean announceBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			announceDao.createAnnounce(announceBean);
			tx.commit();
		} catch (Exception e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAnnounce(Integer anNo) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			announceDao.deleteAnnounce(anNo);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void updateAnnounce(AnnounceBean announceBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			announceDao.updateAnnounce(announceBean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public AnnounceBean findAnnounceByNo(Integer anNo) {
		AnnounceBean announceBean = new AnnounceBean();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			announceBean = announceDao.findAnnounceByNo(anNo);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return announceBean;
	}

	@Override
	public List<AnnounceBean> findAllAnnounce() {
		List<AnnounceBean> announceList = new ArrayList<AnnounceBean>();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			announceList = announceDao.findAllAnnounce();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return announceList;
	}
	
}
