package activity.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import activity.dao.ActivityDao;
import activity.model.ActivityBean;
import utils.HibernateUtils;

public class ActivityDaoImpl implements ActivityDao{
	
	SessionFactory factory;
	
	public ActivityDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Object createActivity(ActivityBean activityBean) {
		Session session = factory.getCurrentSession();
		Object key = session.save(activityBean);
		return key;
	}

	@Override
	public void deleteActivity(Integer ac_id) {
		Session session = factory.getCurrentSession();
		ActivityBean aBean = new ActivityBean(ac_id);
		session.delete(aBean);
	}

	@Override
	public void updateActivity(ActivityBean activityBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(activityBean);
	}

	@Override
	public ActivityBean findById(Integer ac_id) {
		Session session = factory.getCurrentSession();
		ActivityBean aBean = session.get(ActivityBean.class, ac_id);
		return aBean;
	}

	@Override
	public List<ActivityBean> findAll() {
		Session session = factory.getCurrentSession();
		List<ActivityBean> alList = session.createQuery("FROM ActivityBean", ActivityBean.class).getResultList();
		return alList;
	}

	@Override
	public void close() {
		factory.close();
	}

	@Override
	public void persist(ActivityBean activityBean) {
		Session session = factory.getCurrentSession();
		session.persist(activityBean);
	}

}
