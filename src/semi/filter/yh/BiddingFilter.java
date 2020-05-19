package semi.filter.yh;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.dao.yh.MembersDao;

@WebFilter("/Bidding/*")
public class BiddingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean InsertBidding = false;
		HttpServletRequest hp = (HttpServletRequest)request;
		HttpSession session = hp.getSession();
/*		int m_num = (int)session.getAttribute("m_num");
		MembersDao mdao = MembersDao.getInstance();
		int trust = mdao.ShowTrust(m_num);
		if(trust >= 6) {
			InsertBidding = true;
		}
		if(InsertBidding) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse hr = (HttpServletResponse)response;
			hr.setContentType("text/html; charset=utf-8");
			PrintWriter pw = hr.getWriter();
			//pw.println("<script>alert('입찰이 불가능합니다.'); location.href='${cp}/main.do'</script>");
			pw.println("<script>alert('입찰이 불가능합니다.'); history.go(-1); </script>");
			pw.flush();
		}
*/	}
	
	@Override
	public void destroy() {}

	

}
