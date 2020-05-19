package semi.controller.jwIndex;

import java.io.IOException; 
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jw.AuctionDao;
import semi.dao.jw.MainDao;
import semi.vo.jw.MainVo;
@WebServlet("/main.do")
public class MainController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//회원번호, 경매번호 받아오기
//		int a_num=Integer.parseInt(req.getParameter("a_num"));
		int a_num=18;
//		int m_num=Integer.parseInt(req.getParameter("m_num"));
		req.setAttribute("a_num", 18);
		MainDao dao = MainDao.getInstance();
		
		
//		ArrayList<MainVo> ipath=dao.ipath(a_num); //이미지
		ArrayList<String> ipath=dao.ipath2(a_num);
		req.setAttribute("ipath", ipath);
		
		
		String cate = dao.cate(a_num); //카테고리
		req.setAttribute("cate", cate);
		
		
		MainVo info =dao.info(a_num);//경매정보들
		req.setAttribute("info", info);
		
		
		MainVo ship=dao.ship(a_num);//배송방법
		req.setAttribute("ship", ship);
		
		
		int seller = dao.seller(a_num);//판매자번호
		req.setAttribute("seller",seller);
		
		
		int bidnum = dao.bidnum(a_num);//입찰수
		req.setAttribute("bidnum", bidnum);
		
		
		AuctionDao dao2 = AuctionDao.getInstance();//현재시간
		Date enddate=dao2.enddate(18);
		String years=enddate.toString().substring(0,4);
		String months=enddate.toString().substring(5,7);
		String day=enddate.toString().substring(8,10);
		req.setAttribute("years", years);
		req.setAttribute("months", months);
		req.setAttribute("day", day);
		
		
		
		String keyword=req.getParameter("keyword");//페이징처리
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {pageNum=Integer.parseInt(spageNum);}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		ArrayList<MainVo> list =dao.list(startRow,endRow,keyword,a_num);//문의게시판정보
		int pageCount=(int)Math.ceil(dao.getCount(a_num)/5.0);	
		int startPage=(pageNum-1)/4*4+1;
		int endPage=startPage+3;
		if(pageCount<endPage) endPage=pageCount;
		req.setAttribute("list", list);
		req.setAttribute("startRow", startRow);
		req.setAttribute("endRow", endRow);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		
		
		
		req.getServletContext().setAttribute("cp", req.getContextPath());
		req.getRequestDispatcher("/mainview.jsp").forward(req, resp);
	}
}
