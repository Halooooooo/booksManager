package Controller.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.DB.DB;
import Bean.entity.Users;
import Bean.entity.book;
import Bean.entity.bookType;
import Utils.DBUtil;
import Utils.PageBean;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getServletPath();
		if(action.equals("/borrow.user")){
			try {
				PageBean<book> pb = new PageBean<book>();
				String currPage = request.getParameter("currentPage");
				String searchbook = request.getParameter("search");
				if(searchbook==null){
					searchbook="";
				}
				if(currPage == null||"".equals(currPage)){
					currPage="1";
				}
				int currentPage = Integer.parseInt(currPage);
				pb.setCurrentPage(currentPage);
				DBUtil.getListByBook(pb,searchbook);
				request.setAttribute("pageBean", pb);
				Map<Integer,bookType> btmap = DB.getBookTypeMap();
				request.setAttribute("btMap", btmap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("WEB-INF/jsp/user/list.jsp").forward(request, response);
		}else if(action.equals("/approveborrow.user")){
			try{
				HttpSession session = request.getSession(false);
				List<String> list  = (List<String>) session.getAttribute("loginInfo");
				Users user = DB.getUserByName(list.get(0));
				String bookid = request.getParameter("book_id");
				String Name = request.getParameter("book_name"); //error
				String bookName = new String(Name.getBytes("iso-8859-1"),"utf-8");
				if(user!=null&&user.getBorrow()>=1&&user.getMoney()>0){
					DB.setApprove(user.getU_id(),user.getU_name(),bookid,bookName);
					request.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(request, response);
				}else{
					response.sendRedirect("/error.jsp"); // 待开发
				}
				System.out.println(user.toString());
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("/error.jsp"); // 待开发
			}
		}else if("/home.user".equals(action)){
			request.getRequestDispatcher("WEB-INF/jsp/home/home.jsp").forward(request, response);
		}else if("/VIP.user".equals(action)){
			String username = request.getParameter("username");
			Users user = DB.getUserByName(username);
			if(user.getMoney()>20){
				user.setMoney(user.getMoney()-20);
				user.setBorrow(user.getBorrow()+6);
				DB.recharge(user.getU_name(), 3, 20);
				user.setU_type(1);
				DB.makeVIP(user);
				request.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(request, response);
			}else{
				request.setAttribute("msg","账户余额不足");
				request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
			}
		}else if("/expressbook.user".equals(action)){
			String bookid = request.getParameter("book_id");
			String bookname = request.getParameter("book_name");
			request.setAttribute("bookid", bookid);
			request.setAttribute("bookname", bookname);
			request.getRequestDispatcher("WEB-INF/jsp/user/approve.jsp").forward(request, response);
		}else if("/borrowExpress.user".equals(action)){
			HttpSession session = request.getSession(false);
			List<String> list  = (List<String>) session.getAttribute("loginInfo");
			Users user = DB.getUserByName(list.get(0));
			String bookid = request.getParameter("bookid");
			String Name = request.getParameter("bookname"); //error
			String bookName = new String(Name.getBytes("iso-8859-1"),"utf-8");
			int spot = Integer.parseInt(request.getParameter("spot"));
			String spotInfo = request.getParameter("spotInfo");
			int phone = Integer.parseInt(request.getParameter("phone"));
			double money = spot==1?0:10;
			if(user!=null&&user.getBorrow()>=1&&user.getMoney()-money>0){
				DB.setMoney(user.getMoney()-money, user.getU_id());
				DB.setExpress(user.getU_id(),Integer.parseInt(bookid),spot,spotInfo,phone,money);
				DB.express(Integer.parseInt(bookid),user.getU_id());
				request.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(request, response);
			}else{
				request.setAttribute("msg","账户余额不足或已达到最大借书数目");
				request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
			}
		}
		
	}

}
