package semi.controller.jwIndex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jw.MainDao;
@WebServlet("/answer.do")
public class AnswerController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int que_num=Integer.parseInt(req.getParameter("que_num"));
		String textarea=req.getParameter("context");//답변내용
		MainDao dao = MainDao.getInstance();
		dao.answer(que_num,textarea);
		String Msg ="답변이 완료되었습니다.";
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		pw.print("<Msg>"+Msg+"</Msg>");
		pw.print("</result>");
	}
}
