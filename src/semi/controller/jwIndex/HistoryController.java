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
		System.out.println("doGet");
//		System.out.println(req.getParameter("check"));
//		System.out.println(req.getParameter("a_num"));
		req.setCharacterEncoding("utf-8");
		int pageNum=1; // pageNum==현재 내가보는 페이지
		String spageNum =req.getParameter("pageNum");
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int a_num=Integer.parseInt(req.getParameter("a_num")); // 경매물품번호
//		System.out.println(a_num +"a_num 값 입니다.");
		int field =10;
		
		int startRow=(pageNum-1)*field +1;
		int endRow =(startRow-1)+field; // 페이지 '내' 시작,끝 줄
		
		int startPage =(pageNum-1)/5*5+1; 
		int endPage =startPage+4; // 페이징처리된 시작,끝 페이지
		
		BidDao dao= BidDao.getInstance(); //싱글톤dao
		
		ArrayList<BidVo> list2 =dao.postlist(startRow, endRow, 18);
		int paging =(int)Math.ceil(dao.getCount(field));
		JSONArray jarr=new JSONArray();
	    
	    for(BidVo vo:list2){
	    	JSONObject json=new JSONObject();
//	    	System.out.println(vo.getBid_date());
	    	json.put("date",vo.getBid_date());
//	    	System.out.println(vo.getBid_price());
	    	json.put("price",vo.getBid_price());
//	    	System.out.println(vo.getM_num());
	    	json.put("paging",paging);
//	    	System.out.println(paging);
	    	json.put("startRow",startRow);
//	    	System.out.println(startRow);
	    	json.put("endRow",endRow);
//	    	System.out.println(endRow);
	    	json.put("field",field);
//	    	System.out.println(field);
	    	json.put("startPage",startPage);
//	    	System.out.println(startPage);
	    	json.put("endPage",endPage);
//	    	System.out.println(endPage);
	    	json.put("list", list2);
//	    	System.out.println(list);
	    	jarr.put(json);
	    }
	    resp.setContentType("text/plain;charset=utf-8");
	    	PrintWriter pw=resp.getWriter();
	    	pw.print(jarr);
		
		
		
		ArrayList<BidVo> list = dao.list(a_num,startRow,endRow); // 경매번호임
		int allpages =(int)Math.ceil(dao.getCount(field));
		
		System.out.println(dao.getCount(field));
		
		if(endPage>allpages) {endPage = allpages;}
		req.setAttribute("list", list);
		req.setAttribute("allpages", allpages);
		req.setAttribute("startrow", startRow);
		req.setAttribute("endRow", endRow);
		req.setAttribute("field", field);
		req.setAttribute("pageNum", spageNum);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("a_num", a_num);
		req.getRequestDispatcher("/detailview.jsp").forward(req , resp);
	}
	/*
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
		int startPage =(pageNum-1)/5*5+1; 
		int endPage =startPage+4;
		
		
		BidDao dao= BidDao.getInstance();
		int paging =(int)Math.ceil(dao.getCount(field));
		if(endPage>paging) {endPage = paging;}

		System.out.println("paging값입니다. " +paging +",startrow " +startRow +",endRow " +endRow +",field " +field +",startPage " +startPage +",endPage " +endPage);
		ArrayList<BidVo> list =dao.postlist(startRow, endRow, 18);
		
		
		req.setAttribute("paging", paging);
		req.setAttribute("startrow", startRow);
		req.setAttribute("endRow", endRow);
		req.setAttribute("field", field);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("list", list);
		
	    JSONArray jarr=new JSONArray();
	    
	    for(BidVo vo:list){
	    	JSONObject json=new JSONObject();
//	    	System.out.println(vo.getBid_date());
	    	json.put("date",vo.getBid_date());
//	    	System.out.println(vo.getBid_price());
	    	json.put("price",vo.getBid_price());
//	    	System.out.println(vo.getM_num());
	    	json.put("paging",paging);
//	    	System.out.println(paging);
	    	json.put("startRow",startRow);
//	    	System.out.println(startRow);
	    	json.put("endRow",endRow);
//	    	System.out.println(endRow);
	    	json.put("field",field);
//	    	System.out.println(field);
	    	json.put("startPage",startPage);
//	    	System.out.println(startPage);
	    	json.put("endPage",endPage);
//	    	System.out.println(endPage);
	    	json.put("list", list);
//	    	System.out.println(list);
	    	jarr.put(json);
	    }
	    resp.setContentType("text/plain;charset=utf-8");
	    	PrintWriter pw=resp.getWriter();
	    	pw.print(jarr);
	}
	*/
}
