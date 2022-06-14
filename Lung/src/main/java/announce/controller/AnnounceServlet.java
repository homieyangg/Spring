package announce.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import announce.model.AnnounceBean;
import announce.service.AnnounceService;
import announce.service.Impl.AnnounceServiceImpl;


/**
 * Servlet implementation class orderServlet
 */
@WebServlet("/announceServlet")
public class AnnounceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnnounceService announceService;

	public void init() {
		announceService = new AnnounceServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
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
				insertAnnounce(request, response);
				break;
			case "delete":
				deleteAnnounce(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateAnnounce(request, response);
				break;
			default:
				listAnnounce(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	private void listAnnounce(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<AnnounceBean> announceList = announceService.findAllAnnounce();
		request.setAttribute("listAnnounce", announceList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("announcement.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("announcementForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int anNo = Integer.parseInt(request.getParameter("anNo"));
		AnnounceBean existingAnnounceBean = announceService.findAnnounceByNo(anNo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("announcementForm.jsp");
		request.setAttribute("announce", existingAnnounceBean);
		dispatcher.forward(request, response);
	}

	private void insertAnnounce(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String title = request.getParameter("anTitle");
		String content = request.getParameter("anContent");
		String type = request.getParameter("anType");
		String editor = request.getParameter("anEditor");
		Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("anDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		AnnounceBean newAnnounce = new AnnounceBean(title, content, type, editor, date);
		announceService.createAnnounce(newAnnounce);
		listAnnounce(request, response);
	}

	private void updateAnnounce(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int anNo = Integer.parseInt(request.getParameter("anNo"));
		String title = request.getParameter("anTitle");
		String content = request.getParameter("anContent");
		String type = request.getParameter("anType");
		String editor = request.getParameter("anEditor");
		Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("anDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		AnnounceBean update = new AnnounceBean(anNo, title, content, type, editor, date);
		announceService.updateAnnounce(update);
		listAnnounce(request, response);
	}

	private void deleteAnnounce(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int anNo = Integer.parseInt(request.getParameter("anNo"));
		announceService.deleteAnnounce(anNo);
		listAnnounce(request, response);
	}

}
