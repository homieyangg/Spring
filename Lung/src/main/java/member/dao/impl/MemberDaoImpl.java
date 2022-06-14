package member.dao.impl;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import member.dao.MemberDao;
import member.model.MemberBean;
import utils.HibernateUtils;

public class MemberDaoImpl implements MemberDao {
	
	SessionFactory factory;

	public MemberDaoImpl() {
		//Constructor內建立session工廠，
		factory = HibernateUtils.getSessionFactory();
	}


	@Override
	public Object insertMember(MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		Object key = session.save(memberBean);
		return key;
		
	}

	@Override
	public void deleteMember(Integer mi_no) {
		Session session = factory.getCurrentSession();
		MemberBean mBean = new MemberBean(mi_no);
		session.delete(mBean);
	}

	@Override
	public void updateMember(MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(memberBean);
	}

	@Override
	public List<MemberBean> findAll() {
		Session session = factory.getCurrentSession();
		List<MemberBean> listAllMembers = session.createQuery("FROM MemberBean", MemberBean.class).getResultList();
		return listAllMembers;  
	}

	@Override
	public MemberBean findById(Integer mi_no) {
		Session session = factory.getCurrentSession();
		MemberBean memberBean = (MemberBean) session.get(MemberBean.class, mi_no);
		return memberBean;
	}

	
	@Override
	//select all members -> put in Map
	public List<Map<String,String>> listMapMembers() {
		//建一個List<Map<E>>
		List<Map<String,String>> listmap = new ArrayList<Map<String,String>>();
		return listmap;
	}

	public boolean checkLogin(MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		String hql = "FROM MemberBean mb WHERE mb.mi_account = :account and mb.mi_password = :password ";
		Query<MemberBean> query = session.createQuery(hql, MemberBean.class);
		query.setParameter("account", memberBean.getMi_account().toUpperCase());  //要怎麼區分大小寫
		query.setParameter("password", memberBean.getMi_password());		
		List<MemberBean> listAccPassw = query.getResultList();
		if(listAccPassw.size() > 0) {
			return true;
		}else {
			return false;
		}
	}
	

	@Override
	public void close() {
		factory.close();
	}

	@Override
	public void persist(MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		session.persist(memberBean);
	}
	
	
	

}
