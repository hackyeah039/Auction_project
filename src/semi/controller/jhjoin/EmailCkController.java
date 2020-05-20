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
@WebServlet("/join/emailck.jh")
public class EmailCkController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String email=req.getParameter("email");
		MembersDao dao=MembersDao.getMembersDao();
		resp.setContentType("text/plain;charset=utf-8");
		JSONObject json=new JSONObject();
		int n=dao.emailCk(email);
		if(n>0) {
			json.put("msg", "error");
		}else {
			json.put("msg", "ok");
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		pw.close();
	}
}
