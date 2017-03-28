package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;    
		HttpServletResponse res = (HttpServletResponse) response;
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html;charset=UTF-8");
		
		String requestURI = req.getRequestURI();
		String uri = requestURI.substring(requestURI.lastIndexOf("/")+1,requestURI.length());
		if(uri.contains("login")||uri.contains("sign")){
			chain.doFilter(req, res);
		}else{
			HttpSession session = req.getSession(false);
			if (session != null) {
				Object obj = session.getAttribute("loginInfo");
				//如果获取的内容不为空，说明已经登陆，放行
				if (obj != null) {
					// 放行
					chain.doFilter(request, response);
				} else {
					//如果获取的内容为空，说明没有登陆； 跳转到登陆
					res.sendRedirect("/booksManager/loginUI.jsp");
				}
				
			} else {
				// 肯定没有登陆
				res.sendRedirect("/booksManager/loginUI.jsp");
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
