package semi.controller.jwIndex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import semi.dao.jw.MainDao;
@WebServlet("/register.do")
public class RegisterController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("commmmmm");
		
		int anum= Integer.parseInt(req.getParameter("a_num"));
//		System.out.println(anum +" : anum");
		String title=req.getParameter("title");
//		System.out.println(title +" : 타이틀");
		String mnum2=req.getParameter("mnum");
//		System.out.println(mnum2 +" : mnum 임");
		String content=req.getParameter("context");
//		System.out.println(content +" : content 임");
		int mnum=0;
		String msg=null;
		if(mnum2!=null || mnum2!="") {
			mnum=Integer.parseInt(mnum2);
			MainDao dao = MainDao.getInstance();
			dao.register(title, mnum, content, anum);//String title,int mnum,String content,int anum
			msg="등록되었습니다.";
		}else {
			msg="회원만 사용하실수 있는 기능입니다.";
		}
		System.out.println(msg+" : 메세지 입니다.");

		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println("<?xml version=\'1.0\' encoding=\'UTF-8\'?>");
		pw.println("<all>");
		pw.println("<result>"+msg+"</result>");	
		pw.println("</all>");
		System.out.println("컨트롤러 끝");
	}
}
