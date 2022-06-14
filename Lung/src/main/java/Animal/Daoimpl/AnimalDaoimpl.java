package Animal.Daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Animal.dao.AnimalDao;
import Animal.bean.*;
import utils.HibernateUtils;

public class AnimalDaoimpl implements AnimalDao {
	
	SessionFactory factory;
	public AnimalDaoimpl() {
		factory = HibernateUtils.getSessionFactory();
	}
	@Override
	public Object createabdog(AbDogBean abdogbean) {
		Session session = factory.getCurrentSession();
		Object key = session.save(abdogbean);
		return key;
	}


	@Override
	public void deleteabdog(Integer id) {		
	Session session = factory.getCurrentSession();
	AbDogBean abdogbean = new AbDogBean(id);
	session.delete(abdogbean);
		
	}
	@Override
	public void updateabdog(AbDogBean abdogbean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(abdogbean);//更正或新增指令
	}
	@Override
	public AbDogBean findById(Integer id) {
		Session session = factory.getCurrentSession();
		AbDogBean abdogbean = session.get(AbDogBean.class, id);
		//get為讀取 bean 裡面的id 條件式 where
		return abdogbean;
	}
	@Override
	public List<AbDogBean> findAll() {
		Session session = factory.getCurrentSession(); //建立查詢表單 真正的bean 
		 List <AbDogBean > alList = null;
		 alList = session.createQuery("from AbDogBean").list();//  
         
         
          
		//List<AbDogBean> alList = session.createQuery("FROM AbDogBean", AbDogBean.class).getResultList();
		return alList;
	}
	@Override
	public void close() {
		factory.close();
		
	}
	@Override
	public void persist(AbDogBean abdogbean) {
		Session session = factory.getCurrentSession();
		session.persist(abdogbean);//真正的寫進bean
//真正的執行
		
	}
	}
