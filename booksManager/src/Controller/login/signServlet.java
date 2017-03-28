package Controller.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.DB.DB;
import Bean.DB.check;

public class signServlet extends HttpServlet {

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
		String action = request.getServletPath();
		if("/user_verify.sign".equals(action)){
			String userName = request.getParameter("userName");
			String idcard = request.getParameter("idcard");
			boolean hasName = false;
			if(userName!=null&&userName!=""){
				hasName = new check().checkName(userName);
			}
			boolean hasID = false;
			if(idcard!=null&&idcard!=""){
				hasID = new check().checkID(Integer.parseInt(idcard));
			}
			
			String msg = "";
			if(hasName&&hasID){
				msg="true";
			}
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw  = response.getWriter();
			pw.write(msg);
			pw.flush();
			pw.close();
		}else if("/user.sign".equals(action)){
			String u_name = request.getParameter("userName");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String idcard = request.getParameter("idcard");
			DB.SignUser(u_name,password,Integer.parseInt(phone),email,Integer.parseInt(idcard));
			response.sendRedirect("/booksManager/loginUI.jsp");
		}
	}

}
