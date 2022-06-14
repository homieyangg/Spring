package announce.dao.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import announce.dao.AnnounceDAO;
import announce.model.AnnounceBean;
import utils.HibernateUtils;

public class AnnounceDAOimpl implements AnnounceDAO {
	
	SessionFactory factory;
	
	public AnnounceDAOimpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void createAnnounce(AnnounceBean announceBean) {
		Session session = factory.getCurrentSession();
		session.save(announceBean);
	}

	@Override
	public void deleteAnnounce(Integer anNo) {
		Session session = factory.getCurrentSession();
		AnnounceBean announceBean = findAnnounceByNo(anNo);
		session.delete(announceBean);
	}

	@Override
	public void updateAnnounce(AnnounceBean announceBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(announceBean);
	}

	@Override
	public AnnounceBean findAnnounceByNo(Integer anNo) {
		Session session = factory.getCurrentSession();
		AnnounceBean announceBean = session.get(AnnounceBean.class, anNo);
		return announceBean;
	}

	@Override
	public List<AnnounceBean> findAllAnnounce() {
		Session session = factory.getCurrentSession();
		List<AnnounceBean> announceList = session.createQuery("FROM AnnounceBean", AnnounceBean.class).getResultList();
		return announceList;
	}

}
