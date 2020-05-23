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
@WebFilter("/Bidding.do")
public class BiddingFilter implements Filter{

	@Override
	public void destroy() {}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		입찰 가능 여부 확인 위한 boolean 변수 
		boolean InsertBidding = false;

//		ServletRequest가 상위이므로 HttpServletRequest로 강제 형변환 하여 session 값을 받아옴
		HttpServletRequest hp = (HttpServletRequest)request;
		HttpSession session = hp.getSession();

		int m_num = 0;

/*		세션에 값이 담겨있는지 먼저 if문으로 체크(예외처리)
		if(session != null) {
			if(session.getAttribute("m_num") != null) {
				m_num = (int)session.getAttribute("m_num");
			}
		}
*/		
		m_num = 2; //테스트 
		//신뢰도 구하기
		MembersDao mdao = MembersDao.getInstance();
		int trust = mdao.ShowTrust(m_num);
		
		// 신뢰도가 3이상인경우만 입찰 가능 
		if(trust >= 3) {
			InsertBidding = true;
		}
		
		// 입찰 가능여부가 true일때 실행
		if(InsertBidding) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse hr = (HttpServletResponse)response;
			hr.setContentType("text/html; charset=utf-8");
			PrintWriter pw = hr.getWriter();
			pw.println("<script>alert('입찰이 불가능합니다.'); window.open('about:blank', '_self').close(); </script>");
		}
	}
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	

}
