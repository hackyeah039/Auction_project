package semi.controller.jwIndex;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

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
		int pageNum=1; // pageNum==현재 내가보는 페이지
		String spageNum =req.getParameter("pageNum");
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int a_num=Integer.parseInt(req.getParameter("a_num")); // 경매물품번호
		
		int field =10;
		
		int startRow=(pageNum-1)*field +1;
		int endRow =(startRow-1)+field; // 페이지 '내' 시작,끝 줄
		
		int startPage =(pageNum-1)/5*5+1; 
		int endPage =startPage+4; // 페이징처리된 시작,끝 페이지
		
		BidDao dao= BidDao.getInstance(); //싱글톤dao
		
		ArrayList<BidVo> list = dao.list(a_num); //
		int paging =(int)Math.ceil(dao.getCount(field));
		
		System.out.println(dao.getCount(field));
		
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
		req.setCharacterEncoding("utf-8");
		int field=Integer.parseInt(req.getParameter("field"));
		int pageNum =Integer.parseInt(req.getParameter("pageNum")); //넘어옴
//		int a_num=Integer.parseInt(req.getParameter("a_num"));
		System.out.println(field +"필드값입니다," +pageNum +"현재 보고있는 페이지숫자입니다." + 18 +"경매물품번호입니다.");
		int startRow=(pageNum-1)*field +1;
		int endRow =(startRow-1)+field;
		BidDao dao = BidDao.getInstance();
		ArrayList<BidVo> list =dao.postlist(startRow, endRow, 18);
	    JSONArray jarr=new JSONArray();
	    
	    for(BidVo vo:list){
	    	JSONObject json=new JSONObject();
//	    	System.out.println(vo.getBid_date());
	    	json.put("date",vo.getBid_date());
//	    	System.out.println(vo.getBid_price());
	    	json.put("price",vo.getBid_price());
//	    	System.out.println(vo.getM_num());
	    	json.put("mnum",vo.getM_num());
	    	jarr.put(json);
	    }
	    resp.setContentType("text/plain;charset=utf-8");
	    	PrintWriter pw=resp.getWriter();
	    	pw.print(jarr);
	}
}
