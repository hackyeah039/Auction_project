package semi.controller.jhadminqna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.ServerEndpoint;

import semi.dao.jh.BoardDao;
import semi.vo.jh.BoardVo;

@WebServlet("/board/qnadoing.jh")
public class AdminQnaDoingList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int type=Integer.parseInt(req.getParameter("type"));
		String spageNum=req.getParameter("pageNum");
		String field=req.getParameter("field");
		String keyword=req.getParameter("keyword");
		
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=((pageNum-1)*5)+1;
		int endRow=startRow+4;
		
		BoardDao dao=BoardDao.getBoardDao();
		ArrayList<BoardVo> list=null;
		int pageCount=0;
		
		if(field==null || keyword==null || field=="" || keyword=="") {
			list=dao.allBoard(startRow, endRow, type);
			pageCount=(int)Math.ceil(dao.getCount(type)/5.0);
		}else {
			list=dao.allBoard(startRow, endRow, type, field, keyword);
			pageCount=(int)Math.ceil(dao.getCount(type, field, keyword)/5.0);
		}
		int startPage=(pageNum-1)/3*3+1;
		int endPage=startPage+2;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("startRow", startRow);
		req.setAttribute("endRow", endRow);
		req.setAttribute("type", type);
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		req.getRequestDispatcher("/admin/adminIndex.jsp?file=doingqna.jsp").forward(req, resp);
	}
}
