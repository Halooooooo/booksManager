package Controller.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.DB.DB;
import Bean.DB.check;
import Bean.entity.ManagerHomeInfo;

public class loginServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		String status =request.getParameter("status");
		String name =request.getParameter("user_name");
		String password =request.getParameter("password");
		Boolean isLogin = false;
		HttpSession session = request.getSession();
		List<String> loginInfo = new ArrayList<String>();
		String action = request.getServletPath();
		if("1".equals(status)){
			isLogin = new check().chechAdmin(name,password);
			if(isLogin){
				loginInfo.add(name);
				session.setMaxInactiveInterval(10*60);
				session.setAttribute("loginInfo", loginInfo);
				try {
					ManagerHomeInfo mhi = DB.findHomeInfo();
					request.setAttribute("homeInfo", mhi);
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.getRequestDispatcher("WEB-INF/jsp/home/AdminHome.jsp").forward(request, response);
			}else{
				response.sendRedirect("/booksManager/loginUI.jsp");
			}
		}else if("2".equals(status)){
			isLogin = new check().checkUser(name,password);
			if(isLogin){
				loginInfo.add(name);
				// add userType 
				session.setMaxInactiveInterval(10*60);
				session.setAttribute("loginInfo", loginInfo);
				request.getRequestDispatcher("WEB-INF/jsp/home/home.jsp").forward(request, response);
			}else{
				response.sendRedirect("/booksManager/loginUI.jsp");
			}
		}else{
			response.sendRedirect("/booksManager/loginUI.jsp");
		}
	}

}
