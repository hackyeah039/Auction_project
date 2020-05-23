<%@page import="java.io.Console"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="semi.controller.shvo.SHAuctionVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="semi.controller.shdao.MainListDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String field=request.getParameter("field");
	String keyword=request.getParameter("keyword");
    String spageNum=request.getParameter("pageNum");
    
		int pageNum = 1;
		int price=0;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int endrow=pageNum*12;
		int startrow=endrow-11;
		
		MainListDao mdao = new MainListDao();
		ArrayList<SHAuctionVo> list = mdao.SearchList(startrow, endrow, field, keyword);
		
		int pageCnt=(int)Math.ceil(mdao.getSerchCnt(keyword, field)/12.0);
		System.out.print(pageCnt);
		int startPageNum=((pageNum-1)/5)*5+1;
		int endPageNum=startPageNum+4;
		if(pageCnt<endPageNum) {
			endPageNum=pageCnt;
		}
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<result>");
		for(SHAuctionVo vo:list) {
			pw.println("<data>");
			if(mdao.getPrice(vo.getA_num())==0) {
				price=mdao.getStartBid(vo.getA_num());
			}else {
				price=mdao.getPrice(vo.getA_num());
			}
			pw.println("<price>" + price + "</price>");
			pw.println("<pageNum>" + pageNum + "</pageNum>");
			pw.println("<pageCnt>" + pageCnt + "</pageCnt>");
			pw.println("<i_path>"+mdao.getImgPath(vo.getA_num())+"</i_path>");
			pw.println("<startPageNum>" + startPageNum + "</startPageNum>");
			pw.println("<endPageNum>" + endPageNum + "</endPageNum>");
			pw.println("<a_num>" + vo.getA_num() + "</a_num>");
			pw.println("<price>"+mdao.getPrice(vo.getA_num())+"</price>");
			pw.println("<id>"+mdao.getId(vo.getSel_number())+"</id>");
			pw.println("<bidcnt>"+mdao.getBidCnt(vo.getA_num())+"</bidcnt>");
			pw.println("<a_title>" + vo.getA_title() + "</a_title>");
			pw.println("<a_content>" + vo.getA_content() + "</a_content>");
			pw.println("<a_condition>" + vo.getA_condition() + "</a_condition>");
			
			pw.println("<a_regdate>" + vo.getA_regdate() + "</a_regdate>");
			pw.println("<a_startdate>" + vo.getA_startdate() + "</a_startdate>");
			pw.println("<a_enddate>" + mdao.getTime(vo.getA_num()) + "</a_enddate>");
			pw.println("<a_check>" + vo.getA_check() + "</a_check>");
			pw.println("<c_num>" + vo.getC_num() + "</c_num>");
			pw.println("<a_jjim>" + vo.getA_jjim() + "</a_jjim>");
			pw.println("<sel_number>" + vo.getSel_number() + "</sel_number>");
			pw.println("<bidstatus>" + vo.getBidstatus() + "</bidstatus>");
			pw.println("<a_startbid>" + vo.getA_startbid() + "</a_startbid>");
			pw.println("<a_bidunit>" + vo.getA_bidunit() + "</a_bidunit>");
			pw.println("</data>");
		}
		pw.println("</result>");
		
 %>