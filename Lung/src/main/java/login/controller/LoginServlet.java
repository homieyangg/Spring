package login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.model.MemberBean;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDao memberDao; 
	private MemberService memberService;

    public void init() {
    	memberDao = new MemberDaoImpl();
    	memberService = new MemberServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("UTF-8");	
		String loginAccount = request.getParameter("loginAccount");
		String loginPassword = request.getParameter("loginPassword");
		try {
			MemberBean accPassBean = new MemberBean(loginAccount, loginPassword);
			boolean CheckLogBoolean = memberService.checkLogin(accPassBean);
			
			if(CheckLogBoolean) {   //如果帳號密碼驗證為真，就取得session
				HttpSession session = request.getSession();
				session.setAttribute("login", loginAccount);  //login token，傳到其他頁面，用以標示這個帳戶已登入
				System.out.println("目前登入的Session ID為" + session.getId());
				request.getRequestDispatcher("index.jsp").forward(request, response);		
			}else {   //如果帳號密碼驗證為假
				request.setAttribute("error", "帳號或密碼錯誤，請重新輸入");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	
	

}
