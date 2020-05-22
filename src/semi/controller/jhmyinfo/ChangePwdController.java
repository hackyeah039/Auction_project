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
@WebServlet("/myinfo/pwdChange.jh")
public class ChangePwdController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String pwd=req.getParameter("newpwd");
		HttpSession session = req.getSession();
		int m_num=(Integer)session.getAttribute("m_num");
		MembersDao dao=MembersDao.getMembersDao();
		System.out.println(pwd);
		System.out.println(m_num);
		int n=dao.changePWd(pwd, m_num);
		resp.setContentType("text/plain;charset=utf-8");
		JSONObject json=new JSONObject();
		System.out.println("결과:"+n);
		if(n>0) {
			json.put("msg", "ok");
		}else {
			json.put("msg", "error");
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
