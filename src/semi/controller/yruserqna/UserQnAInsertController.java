package semi.controller.yruserqna;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.dao.yr.BoardDao;
import semi.vo.yr.BoardVo;

@WebServlet("/board/userQnAInsert.do")
public class UserQnAInsertController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// id가져오기
//		String id = "gogo";

		HttpSession session = req.getSession();
		int mnum = (Integer)session.getAttribute("m_num"); 
		String id = (String)session.getAttribute("id"); 

		// test
		req.setAttribute("id", id);

//		req.setAttribute("header", "header.jsp");
		req.setAttribute("file", "/uboard/userQnAInsert.jsp");
		req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
//	    req.getRequestDispatcher("/main_sh/layoutTest.jsp?file=/board/userQnAInsert.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 회원번호 가져오기
//		int mnum = 1;
		req.setCharacterEncoding("utf-8");
		
		HttpSession session = req.getSession();
		int mnum = (Integer)session.getAttribute("m_num"); 
		String b_title = req.getParameter("bTitle");
		String b_content = req.getParameter("bContent");

		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo(0, b_title, b_content, 0, mnum, null, null);
		int n = dao.insertBoard(vo);

		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("utf-8");
		out.println("<script>");
		String cp = req.getContextPath();
		if (n > 0) {
			out.println("alert('등록하였습니다.');");
			out.println("window.location.href ='"+cp+"/board/userQnA.do';");
		} else {
			out.println("alert('다시입력해주세요.');");
			out.println("history.back();");

		}
		out.println("</script>");
		out.flush();
	}
}
