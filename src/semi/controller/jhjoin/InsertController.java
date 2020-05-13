package semi.controller.jhjoin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jh.MembersDao;
import semi.vo.jh.MembersVo;

@WebServlet("/join/insert.jh")
public class InsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String contentPath=req.getContextPath();
		String name=req.getParameter("name");
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		String email=req.getParameter("email");
		String addr=req.getParameter("addr");
		int phone=Integer.parseInt(req.getParameter("phone"));
		MembersVo vo=new MembersVo(0, name, email, phone, addr, id, pwd, 0, 0, null);
		MembersDao dao=MembersDao.getMembersDao();
		int n=dao.insertMembers(vo);
		String code="success";
		if(n<=0) {
			code="fail";
		}
		req.setAttribute("code", code);
		req.getRequestDispatcher("/join/joinOk.jsp").forward(req, resp);
	}
}
