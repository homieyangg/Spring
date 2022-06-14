package Animal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Animal.service.*;
import Animal.bean.*;

@WebServlet("/AnimalServlet")
public class AnimalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AbDogService abDogService;

	public void init() {
		abDogService = new AbDogServiceimpl();
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
				insertabdog(request, response);
				break;
			case "delete":
				deleteabdog(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateabdog(request, response);
				break;

			default:
				listabdog(request, response);
				break;

			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private void listabdog(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<AbDogBean> listabDog = abDogService.findAll();
		// for (AbDogBean gg:listabDog )
		// System.out.print(gg.getAbname()+gg.getAbphone());

		request.setAttribute("listabDog", listabDog);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AbDog.jsp");
		dispatcher.forward(request, response);

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("AbDogForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		AbDogBean existingAbDogBean = abDogService.findById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AbDogForm.jsp");
		request.setAttribute("abdog", existingAbDogBean);
		dispatcher.forward(request, response);

	}

	private void insertabdog(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String abid = request.getParameter("abid");
		String abname = request.getParameter("abname");
		String abphone = request.getParameter("abphone");
		String abemail = request.getParameter("abemail");

		String abdogname = request.getParameter("abdogname");
		String abaddress = request.getParameter("abaddress");
		String abtype = request.getParameter("abtype");

		int abage = Integer.parseInt(request.getParameter("abage"));
		String abdate = request.getParameter("abdate");
		AbDogBean newabdog = new AbDogBean(abid, abname, abphone, abemail, abdogname, abaddress, abtype, abage, abdate);
		// abDogService.createabdog(newabdog);
		abDogService.createabdog(newabdog);
		response.sendRedirect("./AnimalServlet?action");
	}

	private void updateabdog(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));

		String abid = request.getParameter("abid");
		String abname = request.getParameter("abname");
		String abphone = request.getParameter("abphone");
		String abemail = request.getParameter("abemail");

		String abdogname = request.getParameter("abdogname");
		String abaddress = request.getParameter("abaddress");
		String abtype = request.getParameter("abtype");

		int abage = Integer.parseInt(request.getParameter("abage"));
		String abdate = request.getParameter("abdate");
		AbDogBean abdog = new AbDogBean(id,abid, abname, abphone, abemail, abdogname, abaddress, abtype, abage, abdate);

		abDogService.updateabdog(abdog);
		response.sendRedirect("./AnimalServlet?action");
	}

	private void deleteabdog(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		abDogService.deleteabdog(id);
		response.sendRedirect("./AnimalServlet?action");
	}

}
