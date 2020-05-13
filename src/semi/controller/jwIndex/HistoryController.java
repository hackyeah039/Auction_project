package semi.controller.jwIndex;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jw.AuctionDao;
import semi.dao.jw.BidDao;
import semi.vo.jw.BidVo;
@WebServlet("/history.do")
//doGet으로 받고->deatilview.jsp() -> selectBox  
public class HistoryController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//여기서 BID 테이블에 있는 정보를 detailview에 AllList()메소드로 뿌리기
		//근데 selectBox는 처음에 10개가 고정이니깐 10개 만 뿌리는 페이징 처리 ㄱㄱ
		req.setCharacterEncoding("utf-8");
		String spageNum =req.getParameter("pageNum");
		int a_num=Integer.parseInt(req.getParameter("a_num"));
		String sfield=req.getParameter("numbers");
		int field =10;
		if(sfield!=null) {
			field=Integer.parseInt(sfield);
		}
		int pageNum=1;
		if(spageNum!=null) {pageNum=Integer.parseInt(spageNum);}
		int startRow=(pageNum-1)*field +1;
		int endRow =(startRow-1)+field;
		int startPage =(pageNum-1)/5*5+1;
		int endPage =startPage+4;
		BidDao dao= BidDao.getInstance();
		ArrayList<BidVo> list = dao.list(startRow, endRow, field, a_num);
		int paging =(int)Math.ceil(dao.getCount(field));
		
		if(endPage>paging) {endPage = paging;}
		System.out.println("페이지:"+paging+"endPage:"+endPage);		
		req.setAttribute("list", list);
		req.setAttribute("paging", paging);
		req.setAttribute("startrow", startRow);
		req.setAttribute("endRow", endRow);
		req.setAttribute("field", field);
		req.setAttribute("pageNum", spageNum);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		
		req.getRequestDispatcher("/detailview.jsp").forward(req , resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//selectBox의 option value를 얻어와서 얻은걸 int로 형변환 &&
		//그걸 페이징처리하고 다시 AllList()메소드 써서 페이징처리 ㄱㄱ
		//Ajax?
	}
}
