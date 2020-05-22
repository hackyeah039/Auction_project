package semi.controller.jhmyinfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import semi.dao.jh.MembersDao;
@WebServlet("/myinfo/update.jh")
public class UpdateMyinfoController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String m_id=req.getParameter("m_id");
		String m_name=req.getParameter("m_name");
		String m_addr=req.getParameter("m_addr");
		String m_phone=req.getParameter("m_phone");
		MembersDao dao=MembersDao.getMembersDao();
		int n=dao.updateInfo(m_id, m_name, m_addr, m_phone);
		resp.setContentType("text/plain;charset=utf-8");
		JSONObject json=new JSONObject();
		if(n>0) {
			json.put("msg", "ok");
		}else {
			json.put("msg","no");
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
