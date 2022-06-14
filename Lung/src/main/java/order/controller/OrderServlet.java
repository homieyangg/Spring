package order.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.model.OrderBean;
import order.service.OrderService;
import order.service.impl.OrderServiceImpl;

@WebServlet(
		urlPatterns = {
				"/order/",
				"/order/orderNew",
				"/order/updateOrder",
				"/order/DeleteOrder",
		},
		initParams = {
				@WebInitParam(name = "orderIndexPath", value = "/JSP/order/order.jsp"),
				@WebInitParam(name = "orderNew", value = "/JSP/order/orderNewForm.jsp"),
				@WebInitParam(name = "orderEdit", value = "/JSP/order/orderEditForm.jsp")
		})
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String orderIndexPath;
	private String orderNew;
	private String orderEdit;
	
	private OrderService orderService;
	
	public void init() {
		orderIndexPath = getInitParameter("orderIndexPath");
		orderNew = getInitParameter("orderNew");
		orderEdit = getInitParameter("orderEdit");
		orderService = new OrderServiceImpl();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(request.getServletPath().equals("/order/orderNew")) {
			try {
				insertOrder(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
			}
		}else if(request.getServletPath().equals("/order/updateOrder")) {
			try {
				updateOrder(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
			}
		}else if(request.getServletPath().equals("/order/DeleteOrder")) {
			try {
				deleteOrder(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
			}
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getServletPath().equals("/order/")) {
			try {
				listOrder(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher(orderIndexPath).forward(request, response);
		}else if (request.getServletPath().equals("/order/orderNew")) {
			request.getRequestDispatcher(orderNew).forward(request, response);
		}else if (request.getServletPath().equals("/order/updateOrder")) {
			try {
				showEditForm(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher(orderEdit).forward(request, response);
		}else if (request.getServletPath().equals("/order/DeleteOrder")) {
			try {
				deleteOrder(request, response);
			} catch (SQLException | IOException | ServletException e) {
				e.printStackTrace();
			}
		}
	}

	private void listOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<OrderBean> listOrder = orderService.showOrderByTable();
		request.setAttribute("listOrder", listOrder);

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("od_id"));
		OrderBean existingOrderBean = orderService.findOrderByid(id);
		request.setAttribute("order", existingOrderBean);
	}

	private void insertOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String od_name = request.getParameter("od_name");
		String od_catalogue = request.getParameter("od_catalogue");
		String od_content = request.getParameter("od_content");
		String od_amount = request.getParameter("od_amount");
		int od_number = Integer.parseInt(request.getParameter("od_number"));
		int od_money = Integer.parseInt(request.getParameter("od_money"));
		OrderBean newOrder = new OrderBean(od_name, od_catalogue, od_content, od_amount, od_number, od_money);
		orderService.createOrder(newOrder);
		response.sendRedirect("./");
	}

	private void updateOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int od_id = Integer.parseInt(request.getParameter("od_id"));
		String od_name = request.getParameter("od_name");
		String od_catalogue = request.getParameter("od_catalogue");
		String od_content = request.getParameter("od_content");
		String od_amount = request.getParameter("od_amount");
		int od_number = Integer.parseInt(request.getParameter("od_number"));
		int od_money = Integer.parseInt(request.getParameter("od_money"));

		OrderBean update = new OrderBean(od_id, od_name, od_catalogue, od_content, od_amount, od_number, od_money);
		orderService.updateOrder(update);
		response.sendRedirect("./");
	}

	private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("od_id"));
		orderService.deleteOrder(id);
		response.sendRedirect("./");
	}

}
