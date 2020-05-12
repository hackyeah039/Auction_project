package semi.controller.jhjoin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import semi.dao.jh.MembersDao;

@WebServlet("/join/idck.jh")
public class IdckController extends HttpServlet{//아이디 중복검사 컨트롤러
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String email=req.getParameter("email");
		MembersDao dao=new MembersDao();
		resp.setContentType("text/plain;charset=utf-8");
		JSONObject json=new JSONObject();
		int n=dao.idCk(id);
		int j=dao.emailCk(email);
		if(n>0 || id.equals("admin") || j>0) {
			json.put("msg", "error");
		}else {
			json.put("msg", "ok");
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
	
}
