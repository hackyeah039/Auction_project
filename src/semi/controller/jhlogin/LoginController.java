package semi.controller.jhlogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.JSONObject;

import semi.dao.jh.LoginDao;
import semi.vo.jh.MembersVo;
@WebServlet("/login.jh")
public class LoginController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		System.out.println("아이디:"+id);
		System.out.println("비번:"+pwd);
		LoginDao dao=LoginDao.getLoginDao();
		ArrayList<MembersVo> list=dao.loginCk(id, pwd);
		JSONObject json=new JSONObject();
		resp.setContentType("text/plain;charset=utf-8");
		if(list.isEmpty()) {
			json.put("msg", "not");
			PrintWriter pw=resp.getWriter();
			pw.print(json);
		}else {
			System.out.println("리스트사이즈"+list.size());
			System.out.println("리스트 담긴거"+list.get(0));
			MembersVo vo=list.get(0);
			System.out.println("꺼내온 아이디"+vo.getM_id());
			System.out.println("꺼내온 회원번호"+vo.getM_num());
			HttpSession session=req.getSession();
			session.setAttribute("id", vo.getM_id());
			session.setAttribute("m_num", vo.getM_num());
			req.getRequestDispatcher("/indextest.jsp").forward(req, resp);
		}
		
	}
}
