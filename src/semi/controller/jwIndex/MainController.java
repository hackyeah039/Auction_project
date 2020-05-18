package semi.controller.jwIndex;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		
		req.getServletContext().setAttribute("cp", req.getContextPath());
		req.getRequestDispatcher("/mainview.jsp").forward(req, resp);
	}
}
