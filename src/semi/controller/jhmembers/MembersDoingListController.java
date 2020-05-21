package semi.controller.jhmembers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.MembersDao;
import semi.vo.jh.MembersVo;
@WebServlet("/members/doing.jh")
public class MembersDoingListController extends HttpServlet{
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
		
		MembersDao dao=MembersDao.getMembersDao();
		ArrayList<MembersVo> list=dao.allMembersList(startRow, endRow, field, keyword, type);
		//수정중
		int pageCount=(int)Math.ceil(dao.getCount(field, keyword,type)/5.0);
		int startPage=(pageNum-1)/3*3+1;
		int endPage=startPage+2;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startRow", startRow);
		req.setAttribute("endRow", endRow);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("field", field);
		req.setAttribute("keyword", keyword);
		req.setAttribute("type", type);
		
		req.getRequestDispatcher("/admin/adminIndex.jsp?file=doingMembersList.jsp").forward(req, resp);
	
	}
}
