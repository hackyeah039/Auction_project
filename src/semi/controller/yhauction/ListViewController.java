package semi.controller.yhauction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/list.do")
public class ListViewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//받아오는 값 인코딩 타입 지정
		req.setCharacterEncoding("utf-8");
		//파라미터로 넘어온 값 변수에 저장 
		String spageNum = req.getParameter("pageNum");
		//파라미터로 넘어온 검색 조건 변수에 저장
		String field = req.getParameter("field");
		String keyword = req.getParameter("keyword");
		
		//기본 값 
		int pageNum = 1;
		if(spageNum != null) {
			pageNum = Integer.parseInt(spageNum);
		}
		//한페이지에 보일 글 갯수
		int startrow = (pageNum-1)*12+1;
		int endrow = startrow + 11;
		// 
		
	}
}
