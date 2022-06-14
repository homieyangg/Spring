package product.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.model.Productbean;
import product.service.ProductService;
import product.service.impl.ProductServiceImpl;


/**
 * Servlet implementation class orderServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;

	public void init() {
		productService = new ProductServiceImpl();
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
				insertProduct(request, response);
				break;
			case "delete":
				deleteProduct(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateProduct(request, response);
				break;
			default:
				listProduct(request, response);
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

	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Productbean> listProduct = productService.showProductByTable();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
		dispatcher.forward(request, response);

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("pd_id"));
		Productbean existingProductbean = productService.findProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("productForm.jsp");
		request.setAttribute("product", existingProductbean);
		dispatcher.forward(request, response);
	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String items = request.getParameter("pd_items");
		String name = request.getParameter("pd_product_name");
		String content = request.getParameter("pd_content");
		String specification = request.getParameter("pd_specification");
		int quantity = Integer.parseInt(request.getParameter("pd_quantity"));
		int amount = Integer.parseInt(request.getParameter("pd_amount"));
		Productbean newpd = new Productbean(items, name, content, specification, quantity, amount);
		productService.createProduct(newpd);
		request.getRequestDispatcher("ProductServlet?action").forward(request, response);
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("pd_id"));
		String items = request.getParameter("pd_items");
		String name = request.getParameter("pd_product_name");
		String content = request.getParameter("pd_content");
		String specification = request.getParameter("pd_specification");
		int quantity = Integer.parseInt(request.getParameter("pd_quantity"));
		int amount = Integer.parseInt(request.getParameter("pd_amount"));

		Productbean update = new Productbean(id, items, name, content, specification, quantity, amount);
		productService.updateProduct(update);
		request.getRequestDispatcher("ProductServlet?action").forward(request, response);
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("pd_id"));
		productService.deleteProduct(id);
		request.getRequestDispatcher("ProductServlet?action").forward(request, response);
	}

}
