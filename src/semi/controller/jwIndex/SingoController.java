package semi.controller.jwIndex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jw.MainDao;
@WebServlet("/singo.do")
public class SingoController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("신고 컨트롤러입니다. 안녕하세요");
		int anum=Integer.parseInt(req.getParameter("a_num"));//경매번호
		String mnum=req.getParameter("m_num");//회원번호
		String textarea=req.getParameter("textareas");
		int mnum2 = 1;
		
		MainDao dao = MainDao.getInstance();
		String errMsg=null;
		if(mnum!=null && !mnum.equals("") ) {//찜 실행
			mnum2=Integer.parseInt(req.getParameter("m_num"));
			dao.singo(anum, mnum2,textarea);
			errMsg ="신고하기가 완료되었습니다.";
		}else { //회원아닌경우
			errMsg="회원만 가능한 기능입니다.";
		}
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		pw.print("<errMsg>"+errMsg+"</errMsg>");
		pw.print("</result>");
	}
}
