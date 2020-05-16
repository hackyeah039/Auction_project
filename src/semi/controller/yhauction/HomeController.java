package semi.controller.yhauction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home.do")
public class HomeController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 홈페이지 호출시 기본적으로 보여질 페이지 설정.
		String header = (String)req.getAttribute("header");
		String content = (String)req.getAttribute("content");
		String footer = (String)req.getAttribute("footer");
		// 초기에는 호출된 값들이 없으므로 null 값일때 보여질 주소값 대입
		if(header == null) header = "/header.jsp";
		if(content == null) content = "/main.jsp";
		if(footer == null) footer = "/footer.jsp";

		
		//페이지 이동 전 페이지 주소에 대한 값을 담음
		req.setAttribute("header",  header);
		req.setAttribute("footer",  footer);
		req.setAttribute("content", content);

		// 프로젝트 경로를 얻어와 변수에 담음
		String cp = req.getContextPath();
		req.getServletContext().setAttribute("cp", cp);
		
		// 페이지에 대한 값을 갖고 이동 
		req.getRequestDispatcher("/index.jsp").forward(req, resp);	
	}
}