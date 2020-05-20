package semi.controller.jhlogin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import semi.dao.jh.LoginDao;
@WebServlet("/login/findId.jh")
public class FindPwdController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String m_id=req.getParameter("m_id");
		String m_name=req.getParameter("m_name");
		String m_phone=req.getParameter("m_phone");
		LoginDao dao=LoginDao.getLoginDao();
		String pwd=dao.findPwd(m_id, m_name, Integer.parseInt(m_phone));
		resp.setContentType("text/plain;charset=utf-8");
		JSONObject json=new JSONObject();
		if(pwd!=null) {//비밀번호가 있을 경우
			json.put("msg", "ok");
			json.put("pwd", pwd);
		}else {
			json.put("msg", "no");
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	
	
	}
}
