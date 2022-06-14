package member.service.impl;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.model.MemberBean;
import member.service.MemberService;
import utils.HibernateUtils;

public class MemberServiceImpl implements MemberService {
	MemberDao dao;
	SessionFactory factory;
	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public Object insertMember(MemberBean memberBean) {
		Object obj = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			Integer no = memberBean.getMi_no();
			String name = memberBean.getMi_name();
			String id = memberBean.getMi_id();
			//Date birth = new java.sql.Date(memberBean.getMi_birth().getTime());
			Date birth = memberBean.getMi_birth();
			String phone = memberBean.getMi_phone();
			String email = memberBean.getMi_email();
			String address = memberBean.getMi_address();
			String account = memberBean.getMi_account();
			String password = memberBean.getMi_password();
			MemberBean newMember= new MemberBean(no, name, id, birth,  phone, email, address, account, password);
			obj = dao.insertMember(newMember);
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
	public void deleteMember(Integer mi_no) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.deleteMember(mi_no);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateMember (MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			dao.updateMember(memberBean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		
	}

	@Override
	public MemberBean findById(Integer mi_no) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			mb = dao.findById(mi_no);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return mb;
	}

	@Override
	public List<MemberBean> findAll() {
		List<MemberBean> allMembers = new ArrayList<>();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			allMembers = dao.findAll();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return allMembers;
	}
	
	@Override
	public Boolean checkLogin(MemberBean memberBean) {
		Boolean checkResult =false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			checkResult = dao.checkLogin(memberBean);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		}
		return checkResult;
	}

	@Override
	public void close() {
		dao.close();
	}

}
