package semi.controller.jhadminqna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.BoardDao;
import semi.vo.jh.BoardVo;
@WebServlet("/board/qnalist.jh")
public class AdminBoradController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String spageNum=req.getParameter("pageNum");
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		
		BoardDao dao=BoardDao.getBoardDao();
		int pageCount=0;
		ArrayList<BoardVo> list=null;
		if(field==null || keyword ==null || field =="" || keyword =="") {
			list=dao.allBoard(startRow, endRow);
			pageCount=(int)Math.ceil(dao.getCount()/5.0);
		}else {
			list=dao.allBoard(startRow, endRow,field,keyword);
			pageCount=(int)Math.ceil(dao.getCount(field,keyword)/5.0);
		}
		
		int startPage=(pageNum-1)/3*3+1;
		int endPage=startPage+2;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		
		req.setAttribute("list", list);
		req.setAttribute("startRow", startRow);
		req.setAttribute("endRow", endRow);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		
		req.getRequestDispatcher("/admin/adminIndex.jsp?file=adminQnaList.jsp").forward(req, resp);
		
	}
}
