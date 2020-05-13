package semi.controller.jhsingo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/singo.detail.jh")
public class SingoDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int singoNum=Integer.parseInt(req.getParameter("singo_num"));
		String m_id=req.getParameter("id");
		System.out.println(singoNum);//신고 번호 받기
		System.out.println(m_id);//신고자 아이디 받기
		
	}
}
