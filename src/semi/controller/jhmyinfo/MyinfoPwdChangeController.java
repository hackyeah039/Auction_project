package semi.controller.jhmyinfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import semi.dao.jh.MembersDao;
@WebServlet("/myinfo/pwdCheck.jh")
public class MyinfoPwdChangeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String nowpwd=req.getParameter("nowpwd");
		int m_num=Integer.parseInt(req.getParameter("m_num"));
		MembersDao dao=MembersDao.getMembersDao();
		String pwd=dao.getPwd(m_num);
		resp.setContentType("text/plain;charset=utf-8");
		JSONObject json=new JSONObject();
		if(pwd.equals(nowpwd)) {
			json.put("msg", "ok");
		}else {
			json.put("msg", "error");
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		
		
	}
}
