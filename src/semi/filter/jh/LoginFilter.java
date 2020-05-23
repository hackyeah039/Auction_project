package semi.filter.jh;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/mypage/*")
public class LoginFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean login=false;
		HttpServletRequest req=(HttpServletRequest)request;//자식에 있는 기능을 사용하기 위해 형변환
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		
		if(id!=null) {
			login=true;
		}
		if(login) {
			chain.doFilter(request, response);
		}else {
			HttpServletResponse rp=(HttpServletResponse)response;
			rp.sendRedirect(req.getContextPath()+"/login/login.jsp");
		}
		
	}
	@Override
	public void destroy() {
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
}
