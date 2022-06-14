package member.dao;

import java.util.List;
import java.util.Map;

import member.model.MemberBean;

public interface MemberDao {
	
	// Insert member
	public Object insertMember(MemberBean memberBean);

	// Update member
	public void updateMember(MemberBean memberBean);

	// Delete member
	public void deleteMember(Integer mi_no);
		
	// Select a member by no
	public MemberBean findById(Integer mi_no);
		
	// Select all members
	public List<MemberBean> findAll();

	//Select all members in List<Map<>> type
	public List<Map<String,String>> listMapMembers();
	
	public boolean checkLogin(MemberBean memberBean);
	
	void close();
	
	void persist(MemberBean memberBean);
	
}
