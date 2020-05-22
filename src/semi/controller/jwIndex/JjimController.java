package semi.controller.jwIndex;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jw.MainDao;
@WebServlet("/jjim.do")
public class JjimController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int anum=Integer.parseInt(req.getParameter("a_num"));//경매번호
		String mnum=req.getParameter("m_num");//회원번호
		
		int mnum2 = 0;
		MainDao dao = MainDao.getInstance();
		
		if(mnum!=null && !mnum.equals("") ) {//찜 실행
			mnum2=Integer.parseInt(req.getParameter("m_num"));
			dao.jjim(anum, mnum2);
			String errMsg ="찜하기가 완료되었습니다.";
			req.setAttribute("errMsg", errMsg);
		}else { //회원아닌경우
			String errMsg="회원만 가능한 기능입니다.";
			req.setAttribute("errMsg", errMsg);
		}
		req.getRequestDispatcher("/board/jjim.jsp").forward(req, resp);
	}
}
