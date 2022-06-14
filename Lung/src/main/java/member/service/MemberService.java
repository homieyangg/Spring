package member.service;

import java.util.List;

import member.model.MemberBean;
import order.model.OrderBean;

public interface MemberService {
	
	Object insertMember(MemberBean memberBean);
	
	void deleteMember(Integer mi_no);
	
	void updateMember(MemberBean memberBean);
	
	MemberBean findById(Integer mi_no);
	
	List<MemberBean> findAll();
	
	public Boolean checkLogin(MemberBean memberBean);
	
	void close();
}
