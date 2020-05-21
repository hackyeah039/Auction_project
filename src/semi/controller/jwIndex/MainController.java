package semi.controller.jwIndex;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import semi.dao.jw.AuctionDao;
import semi.dao.jw.MainDao;
import semi.vo.jw.MainVo;
@WebServlet("/main.do")
public class MainController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		req.setAttribute("startRow", startRow);
		req.setAttribute("endRow", endRow);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("list", list);//문의게시판정보 저장
		
	
		req.getServletContext().setAttribute("cp", req.getContextPath());
		req.getRequestDispatcher("/board/mainview.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost");
		MainDao dao = MainDao.getInstance();
//		int a_num=Integer.parseInt(req.getParameter("a_num"));
		int a_num=18;
		
		String keyword=req.getParameter("keyword");//페이징처리
		System.out.println("받아온 keyword입니다" +keyword);
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {pageNum=Integer.parseInt(spageNum);}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		ArrayList<MainVo> list =dao.list(startRow,endRow,keyword,a_num);//문의게시판정보
		System.out.println(list +"리스트입니디ㅏ");
		int pageCount=(int)Math.ceil(dao.getCount(a_num)/5.0);	
		int startPage=(pageNum-1)/4*4+1;
		int endPage=startPage+3;
		if(pageCount<endPage) endPage=pageCount;
		req.setAttribute("startRow", startRow);
		req.setAttribute("endRow", endRow);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		
		JSONArray jarr = new JSONArray();//JSONArray로 보내기
		for(MainVo vo : list) {
			JSONObject json = new JSONObject();
			json.put("que_num", vo.getQue_num());
			json.put("que_title", vo.getQue_title());
			json.put("que_content", vo.getQue_content());
			json.put("m_num",vo.getM_num());
			json.put("que_status", vo.getQue_status());
			json.put("que_regdate", vo.getQue_regdate());
			json.put("b_content", vo.getB_content());
			json.put("rnum", vo.getRnum());
			System.out.println("이건 VO 입니다!" + vo);
			jarr.put(json);
		}
		System.out.println(jarr +"배열입니다.");
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw =resp.getWriter();
		pw.print(jarr);
	}
}
