package semi.controller.jhsingo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.SingoDao;
import semi.vo.jh.SingoVo;

@WebServlet("/singo.list.jh")
public class SingoListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		SingoDao dao=SingoDao.getSingoDao();
		ArrayList<SingoVo> list=dao.singoAll();
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("admin/adminIndex.jsp?file=allsingo.jsp").forward(req, resp);
	}
}
