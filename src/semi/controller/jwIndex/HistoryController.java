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
//		System.out.println(req.getParameter("check"));
		req.setCharacterEncoding("utf-8");
		int pageNum=1; // pageNum==현재 내가보는 페이지
		String spageNum =req.getParameter("pageNum");
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int a_num=Integer.parseInt(req.getParameter("a_num")); 
		int field =10;
		
		int startRow=(pageNum-1)*10 +1;
		int endRow =(startRow-1)+9; // 페이지 '내' 시작,끝 줄
		
		int startPage =(pageNum-1)/5*5+1; 
		int endPage =startPage+4; // 페이징처리된 시작,끝 페이지
		
		BidDao dao= BidDao.getInstance(); //싱글톤dao
		
		ArrayList<BidVo> list2 =dao.postlist(startRow, endRow, a_num);
		int paging =(int)Math.ceil(dao.getCount(field,a_num));
		if(endPage>paging) {endPage = paging;}
		
		
		JSONArray jarr=new JSONArray();
	    for(BidVo vo:list2){
	    	JSONObject json=new JSONObject();
	    	System.out.println(vo.getBid_date());
	    	json.put("date",vo.getBid_date());
	    	System.out.println(vo.getBid_price());
	    	json.put("price",vo.getBid_price());
	    	System.out.println(vo.getM_num());
	    	json.put("paging",paging);
	    	System.out.println(paging);
	    	json.put("startRow",startRow);
	    	System.out.println(startRow);
	    	json.put("endRow",endRow);
	    	System.out.println(endRow);
	    	json.put("field",field);
	    	System.out.println(field);
	    	json.put("startPage",startPage);
	    	System.out.println(startPage);
	    	json.put("endPage",endPage);
	    	System.out.println(endPage);
	    	json.put("list", list2);
	    	System.out.println(list2);
	    	jarr.put(json);
	    }
	    resp.setContentType("text/plain;charset=utf-8");
	    PrintWriter pw=resp.getWriter();
	    pw.print(jarr);
		
		req.setAttribute("list", list2);
		req.setAttribute("allpages", paging);
		req.setAttribute("startrow", startRow);
		req.setAttribute("endRow", endRow);
		req.setAttribute("field", field);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("a_num", 18);
		req.getRequestDispatcher("/board/detailview.jsp").forward(req , resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int field=Integer.parseInt(req.getParameter("field"));
		String spageNum = req.getParameter("pageNum");
		int pageNum = 1;
		if(spageNum != null) {
			pageNum =Integer.parseInt(spageNum); //넘어옴			
		}
		
		int a_num=Integer.parseInt(req.getParameter("a_num")); 
		System.out.println(field +"필드값입니다," +pageNum +"현재 보고있는 페이지숫자입니다." + 18 +"경매물품번호입니다.");
		int startRow=(pageNum-1)*field +1;
		int endRow =(startRow-1)+field;
		int startPage =(pageNum-1)/5*5+1; 
		int endPage =startPage+4;
		
		BidDao dao= BidDao.getInstance();
		int paging =(int)Math.ceil(dao.getCount(field,a_num));
		if(endPage>paging) {endPage = paging;}

		ArrayList<BidVo> list =dao.postlist(startRow, endRow, a_num);
		System.out.println("paging값입니다. " +paging +",startrow " +startRow +",endRow " +endRow +",field " +field +",startPage " +startPage +",endPage " +endPage);
		
//		req.setAttribute("paging", paging);
//		req.setAttribute("startrow", startRow);
//		req.setAttribute("endRow", endRow);
//		req.setAttribute("field", field);
//		req.setAttribute("startPage", startPage);
//		req.setAttribute("endPage", endPage);
//		req.setAttribute("list", list);
//		req.setAttribute("pageNum", pageNum);
		
	    JSONArray jarr=new JSONArray();
	    
	    for(BidVo vo:list){
	    	JSONObject json=new JSONObject();
//	    	System.out.println(vo.getBid_date() +"vo.getBid_date()");
	    	json.put("date",vo.getBid_date());
//	    	System.out.println(vo.getBid_price() +"vo.getBid_price()");
	    	json.put("price",vo.getBid_price());
//	    	System.out.println(vo.getM_num()+" vo.getM_num()");
	    	json.put("mnum", vo.getM_num());
	    	json.put("paging", paging);
	    	json.put("startRow", startRow);
	    	json.put("endRow", endRow);
	    	json.put("field", field);
	    	json.put("startPage", startPage);
	    	json.put("endPage", endPage);
	    	json.put("startPage", startPage);
	    	json.put("pageNum", pageNum);
	    	jarr.put(json);
	    }
	    PrintWriter pr = resp.getWriter();
	    pr.print(jarr);
	}
	
}
