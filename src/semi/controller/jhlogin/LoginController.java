package semi.controller.jhlogin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if(list.isEmpty()) {
			System.out.println("널입니당");
		}else {
			System.out.println("리스트사이즈"+list.size());
		}
		
	}
}
