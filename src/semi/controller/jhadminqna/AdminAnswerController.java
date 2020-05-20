package semi.controller.jhadminqna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import semi.dao.jh.BoardDao;
import semi.vo.jh.B_answerVo;
@WebServlet("/board/approval.jh")
public class AdminAnswerController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int b_num=Integer.parseInt(req.getParameter("b_num"));
		String b_dap=req.getParameter("b_dap");
		BoardDao dao=BoardDao.getBoardDao();
		int i=dao.boardAnswer(b_num, b_dap);
		resp.setContentType("text/plain;charset=utf-8");
		JSONObject json=new JSONObject();
		if(i==2) {
			json.put("msg", "ok");
		}else {
			json.put("msg", "error");
		}
		PrintWriter pw = resp.getWriter();
		pw.print(json);
		pw.close();
	}
}
