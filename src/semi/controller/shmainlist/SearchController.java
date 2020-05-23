package semi.controller.shmainlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.controller.shdao.MainListDao;
import semi.controller.shvo.SHAuctionVo;
@WebServlet("/sh/search.do")
public class SearchController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/plain;charset=utf-8");
		
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		req.setAttribute("file", "/main_sh/getSearch.jsp");
		req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
	}
}
