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
//		int anum=Integer.parseInt(req.getParameter("a_num"));//경매번호
		String mnum = (String)req.getParameter("m_num");//회원번
		int selnumber = Integer.parseInt(req.getParameter("sel_number"));//회원번
		String textarea=req.getParameter("textareas");
		
		MainDao dao = MainDao.getInstance();
		String Msg=null;
		if(mnum!=null || mnum.equals("")) {//찜 실행
			int mnum2=Integer.parseInt(mnum);
			int n=dao.singo(selnumber, mnum2,textarea);
			if(n>0) {//찜 성공한 케이스
				Msg ="신고하기가 완료되었습니다.";
			}else {//찜 실패한 케이스
				Msg="실패.";				
			}
		}else { //회원아닌경우
			Msg="회원만 가능한 기능입니다.";
		}
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		pw.print("<Msg>"+Msg+"</Msg>");
		pw.print("</result>");
	}
}
