package activity.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import activity.model.ActivityBean;
import activity.service.ActivityService;
import activity.service.impl.ActivityServiceImpl;
@MultipartConfig
@WebServlet("/ActivityServlet")
public class ActivityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActivityService activityService;
	
	public void init() {
		activityService = new ActivityServiceImpl();
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
				insertActivity(request, response);
				break;
			case "newig":
				insertIg(request, response);
				break;
			case "delete":
				deleteActivity(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "client":
				showClientForm(request, response);
				break;
			case "update":
				updateActivity(request, response);
				break;
			default:
				listActivity(request, response);
				break;
			}
		} catch (SQLException | ParseException ex) {
			throw new ServletException(ex);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private void listActivity(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ActivityBean> listActivity = activityService.findAll();
		request.setAttribute("listActi", listActivity);
		RequestDispatcher dispatcher = request.getRequestDispatcher("acti.jsp");
		dispatcher.forward(request, response);

	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("actiForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("ac_id"));
		ActivityBean existingActivityBean = activityService.findById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("actiForm.jsp");
		request.setAttribute("activity", existingActivityBean);
		dispatcher.forward(request, response);
	}

	private void insertActivity(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ParseException {
		System.out.println("aaa");
		String ac_name = request.getParameter("ac_name");
		System.out.println(ac_name);
		System.out.println("In do post method of Add Image servlet");
		Part file=request.getPart("ac_image");
		System.out.println("bbb");
		String ac_image = System.currentTimeMillis()+"_"+file.getSubmittedFileName();//get selected image file name
//		String ac_image = "sss";//get selected image file name
		//upload path where we have to upload our actual image
		System.out.println("Select Image File Name : "+ac_image);
		//Uploading our selected image into images folder
		String uploadPath=getServletContext().getRealPath("/images/")+ac_image;
		System.out.println(uploadPath);
//		String uploadPath=ac_image;
		System.out.println("Upload Path :"+uploadPath);
		try {
			FileOutputStream fos = new FileOutputStream(uploadPath);
			InputStream is = file.getInputStream();
			
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("qqq");
		//**********************
		String date=request.getParameter("ac_date");
		System.out.println(date);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date Date = df.parse(date);
				java.sql.Date ac_date= new java.sql.Date(Date.getTime());
				System.out.println("SQL type date: "+ac_date);
		
		
//		java.sql.Date ac_date = null;
//		try {
//			java.util.Date d= new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
//			ac_date = new java.sql.Date(d.getTime()); 
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		System.out.println("ccc");
//		Date ac_date = new Date();
//		try {
//			ac_date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ac_date"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		String ac_participant = request.getParameter("ac_participant");
		String ac_venue = request.getParameter("ac_venue");
		int ac_quota = Integer.parseInt(request.getParameter("ac_quota"));
		int ac_waitlist_quota = Integer.parseInt(request.getParameter("ac_waitlist_quota"));
		int ac_fee = Integer.parseInt(request.getParameter("ac_fee"));
		String ac_organizer = request.getParameter("ac_organizer");
		ActivityBean newActivity = new ActivityBean(ac_name, ac_image, ac_date, ac_participant, ac_venue, ac_quota, ac_waitlist_quota, ac_fee, ac_organizer);
		activityService.createActivity(newActivity);
		request.getRequestDispatcher("ActivityServlet?action").forward(request, response);
	}
	
	private void insertIg(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException{
		String ac_name = "IG人氣UP!豪華露營浪浪回家志工團";
		String ac_image = "下載.jfif";
		java.sql.Date ac_date = null;
		try {
			java.util.Date d= new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-02");
			ac_date = new java.sql.Date(d.getTime()); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		Date ac_date = new Date();
//		try {
//			ac_date = new SimpleDateFormat("yyyy-MM-dd").parse("2022/1/1");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		String ac_participant = "一般志工";
		String ac_venue = "宜蘭縣三星鄉張美阿嬤農場";
		int ac_quota = 50;
		int ac_waitlist_quota = 0;
		int ac_fee = 1000;
		String ac_organizer = "現代行銷工作室";
		ActivityBean newActivity = new ActivityBean(ac_name, ac_image, ac_date, ac_participant, ac_venue, ac_quota, ac_waitlist_quota, ac_fee, ac_organizer);
		activityService.createActivity(newActivity);
		request.getRequestDispatcher("ActivityServlet?action").forward(request, response);
	}
	private void showClientForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("ac_id"));
		ActivityBean existingActivityBean = activityService.findById(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("actiClient.jsp");
		request.setAttribute("activity", existingActivityBean);
		dispatcher.forward(request, response);
	}

	private void updateActivity(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int ac_id = Integer.parseInt(request.getParameter("ac_id"));
		System.out.println(ac_id);
		String ac_name = request.getParameter("ac_name");
		System.out.println(ac_name);
		System.out.println("In do post method of Add Image servlet");
		Part file=request.getPart("ac_image");
		String ac_image = System.currentTimeMillis()+"_"+file.getSubmittedFileName();//get selected image file name
		//upload path where we have to upload our actual image
		System.out.println("Select Image File Name : "+ac_image);
		//Uploading our selected image into images folder
		String filePath = new File("").getAbsolutePath();
		filePath.concat("path to the property file");
		String uploadPath=getServletContext().getRealPath("/images/")+ac_image;
		System.out.println("Upload Path :"+uploadPath);
		try {
			FileOutputStream fos = new FileOutputStream(uploadPath);
			InputStream is = file.getInputStream();
			
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//**********************
		java.sql.Date ac_date = null;
		try {
			java.util.Date d= new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ac_date"));
			ac_date = new java.sql.Date(d.getTime()); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		Date ac_date = new Date();
//		try {
//			ac_date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("ac_date"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		String ac_participant = request.getParameter("ac_participant");
		String ac_venue = request.getParameter("ac_venue");
		int ac_quota = Integer.parseInt(request.getParameter("ac_quota"));
		int ac_waitlist_quota = Integer.parseInt(request.getParameter("ac_waitlist_quota"));
		int ac_fee = Integer.parseInt(request.getParameter("ac_fee"));
		String ac_organizer = request.getParameter("ac_organizer");
		
		ActivityBean update = new ActivityBean(ac_id, ac_name, ac_image, ac_date, ac_participant, ac_venue, ac_quota, ac_waitlist_quota, ac_fee, ac_organizer);
		activityService.updateActivity(update);
		request.getRequestDispatcher("ActivityServlet?action").forward(request, response);
	}

	private void deleteActivity(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("ac_id"));
		activityService.deleteActivity(id);
		response.sendRedirect("ActivityServlet?action");
	}

}
