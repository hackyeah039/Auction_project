package semi.controller.jhsingo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.SingoDao;

@WebServlet("/singo/return.jh")
public class SingoReturnController extends HttpServlet{ //신고반려
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int singo_num=Integer.parseInt(req.getParameter("singo_num"));
		SingoDao dao=SingoDao.getSingoDao();		
		int n=dao.trustStay(singo_num);
		String msg=null;
		if(n>0) {
			msg="ok";
		}else {
			msg="error";
		}
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/admin/adminIndex.jsp?file=singoOk.jsp").forward(req, resp);
	}
}
