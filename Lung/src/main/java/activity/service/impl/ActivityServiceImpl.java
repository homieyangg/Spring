package activity.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import activity.dao.ActivityDao;
import activity.dao.impl.ActivityDaoImpl;
import activity.model.ActivityBean;
import activity.service.ActivityService;
import utils.HibernateUtils;

public class ActivityServiceImpl implements ActivityService {
	ActivityDao dao;
	SessionFactory factory;
	public ActivityServiceImpl() {
		dao = new ActivityDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Object createActivity(ActivityBean activityBean) {
		Object obj = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String ac_name = activityBean.getAc_name();
			String ac_image = activityBean.getAc_image();
			Date ac_date = activityBean.getAc_date();
			String ac_participant = activityBean.getAc_participant();
			String ac_venue = activityBean.getAc_venue();
			Integer ac_quota = activityBean.getAc_quota();
			Integer ac_waitlist_quota = activityBean.getAc_waitlist_quota();
			Integer ac_fee = activityBean.getAc_fee();
			String ac_organizer = activityBean.getAc_organizer();
			ActivityBean newActivity = new ActivityBean(ac_name, ac_image, ac_date, ac_participant, ac_venue, ac_quota, ac_waitlist_quota, ac_fee, ac_organizer);
			obj = dao.createActivity(newActivity);
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
	public void deleteActivity(Integer ac_id) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.deleteActivity(ac_id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateActivity(ActivityBean activityBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.updateActivity(activityBean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public ActivityBean findById(Integer ac_id) {
		ActivityBean ob = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ob = dao.findById(ac_id);
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
	public List<ActivityBean> findAll() {
		List<ActivityBean> allActivity = new ArrayList<ActivityBean>();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			allActivity = dao.findAll();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return allActivity;
	}

	@Override
	public void close() {
		dao.close();
	}

}
