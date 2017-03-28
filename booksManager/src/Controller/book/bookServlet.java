package Controller.book;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.DB.DB;
import Bean.entity.Approve;
import Bean.entity.Bill;
import Bean.entity.ManagerHomeInfo;
import Bean.entity.Users;
import Bean.entity.book;
import Bean.entity.bookType;
import Utils.DBUtil;
import Utils.PageBean;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class bookServlet extends HttpServlet {


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		String action = request.getServletPath();
		if(action.equals("/list.book")){
			String searchbook = request.getParameter("search");
			if(searchbook==null){
				searchbook="";
			}
			try {
				PageBean<book> pb = new PageBean<book>();
				String currPage = request.getParameter("currentPage");
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
			};
			
			request.getRequestDispatcher("WEB-INF/jsp/book/list.jsp").forward(request,response); 
		}else if(action.equals("/addUI.book")){
			List<bookType> list = DB.getBookType();
			request.setAttribute("booktype", list);
			request.getRequestDispatcher("WEB-INF/jsp/book/add.jsp").forward(request,response);
		}else if(action.equals("/add.book")){
			String type = request.getParameter("n_bt"); 
			String bookName = request.getParameter("bookName");
			String author = request.getParameter("author");
			String price = request.getParameter("price");
			String totalNumber = request.getParameter("totalNumber");
			String nowNumber = request.getParameter("nowNumber");
			double money = Integer.parseInt(totalNumber)*Double.parseDouble(price);
			DB.recharge("admin", 1, money);
			DB.addBook(bookName,author,type,price,totalNumber,nowNumber);
			response.sendRedirect("/booksManager/list.book");
		}else if(action.equals("/delete.book")){
			int id = Integer.parseInt(request.getParameter("bookid"));
			DB.delect(id);
			response.sendRedirect("/booksManager/list.book");
		}else if(action.equals("/editUI.book")){
			int id = Integer.parseInt(request.getParameter("bookid"));
			book b = DB.findById(id);
			List<bookType> list = DB.getBookType();
			request.setAttribute("booktype", list);
			request.setAttribute("book", b);
			request.getRequestDispatcher("WEB-INF/jsp/book/edit.jsp").forward(request, response);
		}else if(action.equals("/edit.book")){
			int id = Integer.parseInt(request.getParameter("bookid"));
			String type = request.getParameter("n_bt"); 
			String bookName = request.getParameter("bookName");
			String author = request.getParameter("author");
			String price = request.getParameter("price");
			String totalNumber = request.getParameter("totalNumber");
			String nowNumber = request.getParameter("nowNumber");
			DB.updateBook(id,type,bookName,author,price,totalNumber,nowNumber);
			response.sendRedirect("/booksManager/list.book");
		}else if(action.equals("/book_exportPDF.book")){
			try {  
				List<book> list = DB.getBooksList();
				Map<Integer,bookType> btmap = DB.getBookTypeMap();
				request.setCharacterEncoding("utf-8");
	             response.setCharacterEncoding("utf-8");
	             OutputStream os = response.getOutputStream();  
//	                FileOutputStream out = new FileOutputStream("//Users//donghao//Desktop//");  
	             Document document = new Document(PageSize.A4, 50, 50, 50, 50); 
	             response.setContentType("application/pdf");  
	             response.setHeader("Content-Disposition","attachment;filename="+new String("书籍列表list.pdf".getBytes("utf-8"),"gbk"));
	            //  response.setHeader("Content-disposition",  
	            //          "attachment;filename=" + fname + ".pdf");  
	                ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	                PdfWriter.getInstance(document, os);  
	                document.open();  
	                int cols = list.size();  
	                // 创建PDF表格  
	                PdfPTable table = new PdfPTable(5);  
	                // 设置pdf表格的宽度  
	                table.setTotalWidth(500);  
	                // 设置是否要固定其宽度  
	                table.setLockedWidth(true);  
	                // 表头字体  
	                Font thfont = new Font();  
	                // 设置表头字体的大小  
	                thfont.setSize(7);  
	                // 设置表头字体的样式  
	                thfont.setStyle(Font.BOLD);  
	                Font tdfont = new Font();  
	                tdfont.setSize(10);  
	                tdfont.setStyle(Font.NORMAL);  
	                // 设置水平对齐方式  
	                table.setHorizontalAlignment(Element.ALIGN_MIDDLE);  
	                // 设置table的header  
	                table.addCell(new Paragraph(new String("bookName".getBytes(),"gbk"), thfont));  
	                table.addCell(new Paragraph("bookType", thfont));  
	                table.addCell(new Paragraph("Author", thfont));  
	                table.addCell(new Paragraph("Pirce", thfont));  
	                table.addCell(new Paragraph(new String("Number".getBytes(),"utf-8"), thfont));  
	                // 循环设置table的每一行  
	                for (int i = 0; i < list.size(); i++) {  
	                    book b = (book) list.get(i);  
	                    table.addCell(new Paragraph(b.getBookName(), tdfont));  
	                    table.addCell(new Paragraph(btmap.get(b.getBooktype()).getBooktype(), tdfont));  
	                    table.addCell(new Paragraph(b.getAuthor(), tdfont));  
	                    table.addCell(new Paragraph(String.valueOf(b.getPrice()), tdfont));  
	                    table.addCell(new Paragraph(String.valueOf(b.getNowNumber()), tdfont));  
	                }  
	                document.add(table);  
	                document.close();  
	                baos.writeTo(response.getOutputStream());  
	                baos.close();  
//	                response.getOutputStream().close();
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
		}else if("/checkapproveUI.book".equals(action)){
			List<Approve> list = DB.getApproveList();
			request.setAttribute("approveList", list);
			request.getRequestDispatcher("WEB-INF/jsp/Info/list.jsp").forward(request, response);
		}else if("/borrowBook.book".equals(action)){
			//借书 操作书本 可用－1 操作个人 可借－1
			String approveid = request.getParameter("approveid");
			String bookid = request.getParameter("bookid");
			String userid = request.getParameter("userid");
			String username = request.getParameter("name");
			if(DB.findById(Integer.parseInt(bookid)).getNowNumber()>=1){
				if(DB.getUserByName(username).getBorrow()>=1){
					DB.borrowBook(Integer.parseInt(bookid), Integer.parseInt(userid), Integer.parseInt(approveid));
					response.sendRedirect("/booksManager/checkapproveUI.book");
				}else{
					request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response); // 借书已达最大额度
				}
			}else{
				request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response); //setattribute ( 书本已经没有了
			}
		}else if("/returnUI.book".equals(action)){
			try{
				String bookid = request.getParameter("bookid");
				List<Users> userList = DB.ReturnBookUsers(Integer.parseInt(bookid));
				book b = DB.findById(Integer.parseInt(bookid));
				request.setAttribute("book", b);
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("WEB-INF/jsp/book/returnbook.jsp").forward(request, response);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/return.book".equals(action)){
			try{
				int bookid = Integer.parseInt(request.getParameter("bookid"));
				int userid = Integer.parseInt(request.getParameter("userid"));
				long oldTime = DB.getBorrowTime(bookid,userid);
				if(oldTime!=0){
					long nowTime = Calendar.getInstance().getTime().getTime();
					long day = (nowTime-oldTime)/(1000*60*60*24);
					Users u = DB.findUserById(userid);
					double money = u.getMoney();
					if(day<=14&&day>7){
						DB.recharge(u.getU_name(), 2, 50);
						money-=50;
						DB.setMoney(money,userid);
						DB.ReturnBook(bookid,userid);
						response.sendRedirect("list.book");
					}else if(day>14){
						double daymoney = 5*(day-14);
						DB.recharge(u.getU_name(), 2, daymoney+50);
						money= money-daymoney-50;
						DB.setMoney(money,userid);
						DB.ReturnBook(bookid,userid);
						response.sendRedirect("list.book");
					}else{
						DB.ReturnBook(bookid,userid);
						response.sendRedirect("list.book");
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/success.book".equals(action)){
			request.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(request, response);
		}else if("/reportborrowbook.book".equals(action))
		{
			List<book> bookList = DB.getBorrowBook();
			request.setAttribute("bookList", bookList);
			request.getRequestDispatcher("WEB-INF/jsp/report/reportborrowbooklist.jsp").forward(request, response);
		}else if("/user.book".equals(action)){
			String searchuser = request.getParameter("search");
			if(searchuser==null){
				searchuser="";
			}
			PageBean<Users> pb = new PageBean<Users>();
			String currPage = request.getParameter("currentPage");
			if(currPage == null||"".equals(currPage)){
				currPage="1";
			}
			int currentPage = Integer.parseInt(currPage);
			pb.setCurrentPage(currentPage);
			DBUtil.getListByUser(pb,searchuser);
			request.setAttribute("pageBean", pb);
			request.setAttribute("userType", Users.USER_TYPE_MAP);
			request.getRequestDispatcher("WEB-INF/jsp/book/listusers.jsp").forward(request, response);
		}else if("/usereditUI.book".equals(action)){
			Users u = DB.findUserById(Integer.parseInt(request.getParameter("userid")));
			request.setAttribute("user", u);
			request.setAttribute("userType", Users.USER_TYPE_MAP);
			request.getRequestDispatcher("WEB-INF/jsp/book/edituser.jsp").forward(request, response);
		}else if("/edituser.book".equals(action)){
			String u_id = request.getParameter("u_id");
			String borrow = request.getParameter("borrow");
			String idcard = request.getParameter("idcard");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String u_name = request.getParameter("u_name");
			DB.setUser(u_id,u_name,idcard,email,phone,borrow);
		}else if("/searchuser.book".equals(action)){
			String searchuser = request.getParameter("searchName");
			PageBean<Users> pb = new PageBean<Users>();
			String currPage = request.getParameter("currentPage");
			if(currPage == null||"".equals(currPage)){
				currPage="1";
			}
			int currentPage = Integer.parseInt(currPage);
			pb.setCurrentPage(currentPage);
			request.getRequestDispatcher("WEB-INF/jsp/book/listusers.jsp").forward(request, response);
		}else if("/home.book".equals(action)){
			try {
				ManagerHomeInfo mhi = DB.findHomeInfo();
				request.setAttribute("homeInfo", mhi);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("WEB-INF/jsp/home/AdminHome.jsp").forward(request, response);
		}else if("/userAddMoneyUI.book".equals(action)){
			String userid = request.getParameter("userid");
			Users user = DB.findUserById(Integer.parseInt(userid));
			request.setAttribute("user", user);
			request.getRequestDispatcher("WEB-INF/jsp/bill/recharge.jsp").forward(request, response);
		}else if("/recharge.book".equals(action)){
			double money = Double.parseDouble(request.getParameter("money"));
			int userid = Integer.parseInt(request.getParameter("userid"));
			Users user = DB.findUserById(userid);
			DB.recharge(user.getU_name(),0,money);
			money +=user.getMoney();
			DB.setMoney(money, userid);
			request.getRequestDispatcher("WEB-INF/jsp/home/AdminHome.jsp").forward(request, response);
		}else if("/QueryBill.book".equals(action)){
			String BillType = request.getParameter("billType");
			int type =-1;
			if(BillType==null||"".equals(BillType)){
			}else if("-1".equals(BillType)){}
			else{
				type = Integer.parseInt(BillType);
			}
			PageBean<Bill> pb = new PageBean<Bill>();
			String currPage = request.getParameter("currentPage");
			if(currPage == null||"".equals(currPage)){
				currPage="1";
			}
			int currentPage = Integer.parseInt(currPage);
			pb.setCurrentPage(currentPage);	
			DBUtil.getListByBill(pb,type);
			request.setAttribute("BillPB", pb);
			request.setAttribute("billType", Bill.TYPE_MAP);
			request.setAttribute("defaultType", type);
			request.getRequestDispatcher("WEB-INF/jsp/bill/billlist.jsp").forward(request, response);
		}else if("/exit.book".equals(action)){
			request.getSession().removeAttribute("loginInfo");
			response.sendRedirect("/booksManager/loginUI.jsp");
		}else if("/userdelete.book".equals(action)){
			int id = Integer.parseInt(request.getParameter("userid"));
			DB.deleteuser(id);
			response.sendRedirect("user.book");
		}
		
	}
	

}
