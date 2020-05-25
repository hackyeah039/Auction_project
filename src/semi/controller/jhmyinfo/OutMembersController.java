package semi.controller.jhmyinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import semi.dao.jh.MembersDao;
@WebServlet("/myinfo/out.jh")
public class OutMembersController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int m_num=Integer.parseInt(req.getParameter("m_num"));
		System.out.println("아웃회원번호:"+m_num);
		System.out.println("테스트");
		MembersDao dao=MembersDao.getMembersDao();
		ArrayList<Integer> list=dao.getMembersOut(m_num);
		resp.setContentType("text/plain;charset=utf-8");
		JSONObject json=new JSONObject();
		
		if(list.size()>0) {
			json.put("msg", "no");
		}else {
			int n=dao.updateOut(m_num);
			if(n>0) {
				json.put("msg","ok");
				req.getSession().invalidate();
			}else {
				json.put("msg", "error");
			}
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
