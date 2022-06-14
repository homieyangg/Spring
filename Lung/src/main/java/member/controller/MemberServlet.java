package member.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import member.dao.OutputJson;
import member.model.MemberBean;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

@WebServlet("/MemberSysServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService; 

    public void init() {
    	memberService = new MemberServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		try {
			switch (action) {
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertMember(request, response);
				break;
			case "delete":
				deleteMember(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateMember(request, response);
				break;
			case "output":
				outputJson(request, response);
				break;
			default:
				memberList(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);		
	}
	
	
	
	// 顯示全部會員資料
	private void memberList(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<MemberBean> memberList = memberService.findAll();
		request.setAttribute("memberList", memberList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("member.jsp");
		dispatcher.forward(request, response);

	}
	
	// 顯示空白表單
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("memberForm.jsp");
			dispatcher.forward(request, response);
		}
	
	// 顯示修改頁面(葉面顯示抓出的一筆會員資料) -> 用來修改會員資料
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int mi_no = Integer.parseInt(request.getParameter("mi_no"));   //  ??
		MemberBean existingMemberBean = memberService.findById(mi_no);
		RequestDispatcher dispatcher = request.getRequestDispatcher("memberForm.jsp");
		request.setAttribute("existingMemberBean", existingMemberBean);   // ??
		dispatcher.forward(request, response);
	}
	
	// 新增會員資料 ?
	private void insertMember(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String mi_name = request.getParameter("mi_name");
		String mi_id = request.getParameter("mi_id");
		//Date mi_birth = new Date();  //初始化&new物件	
		java.sql.Date mi_birth = null;
		try {
			java.util.Date d= new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("mi_birth"));
			mi_birth = new java.sql.Date(d.getTime()); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String mi_phone = request.getParameter("mi_phone") ;
		String mi_email = request.getParameter("mi_email");
		String mi_address = request.getParameter("mi_address");
		String mi_account = request.getParameter("mi_account").toUpperCase();  //把帳號都轉成大寫再新增
		String mi_password = request.getParameter("mi_password");
		MemberBean beanNewMember = new MemberBean(mi_name, mi_id, mi_birth, mi_phone, mi_email, mi_address, mi_account, mi_password);
		memberService.insertMember(beanNewMember);  //8個欄位
		response.sendRedirect("./MemberSysServlet?action");
	}
	
	private void updateMember(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int mi_no = Integer.parseInt(request.getParameter("mi_no"));
		String mi_name = request.getParameter("mi_name");
		String mi_id = request.getParameter("mi_id");
		
		java.sql.Date mi_birth = null;
		try {
			java.util.Date d= new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("mi_birth"));
			mi_birth = new java.sql.Date(d.getTime()); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		Date mi_birth = new Date();  //初始化&new物件
//		try {
//			mi_birth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("mi_birth"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		String mi_phone = request.getParameter("mi_phone");
		String mi_email = request.getParameter("mi_email");
		String mi_address = request.getParameter("mi_address");
		String mi_account = request.getParameter("mi_account");
		String mi_password = request.getParameter("mi_password");

		MemberBean beanUpdateMember = new MemberBean(mi_no, mi_name, mi_id, mi_birth, mi_phone, mi_email, mi_address, mi_account, mi_password);
		memberService.updateMember(beanUpdateMember);  //9個欄位的bean，但實際上show出update頁面時，會設定會員編號為readonly
		response.sendRedirect("./MemberSysServlet?action");
	}
	
	private void deleteMember(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int mi_no = Integer.parseInt(request.getParameter("mi_no"));
		memberService.deleteMember(mi_no);
		response.sendRedirect("./MemberSysServlet?action");
	}
	
	private void outputJson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		OutputJson outputJson = new OutputJson();
		outputJson.outputJson("D:\\outputMembers.json");
//		outputJson.outputJson("C:\\Users\\Sorat\\Documents\\team2\\outputMembers.json");
		RequestDispatcher dispatcher = request.getRequestDispatcher("memberOutput.jsp");  //重導到查詢頁面
		dispatcher.forward(request, response);
	}
	
	

}
