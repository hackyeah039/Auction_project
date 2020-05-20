package semi.controller.jhadminqna;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import semi.dao.jh.BoardDao;
import semi.vo.jh.B_answerVo;
@WebServlet("/admin/getAnswer.jh")
public class AdminGetAnswerController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int b_num=Integer.parseInt(req.getParameter("b_num"));
		BoardDao dao=BoardDao.getBoardDao();
		ArrayList<B_answerVo> list=new ArrayList<B_answerVo>();
		list=dao.adminAnswer(b_num);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		JSONObject json=new JSONObject();
		for(B_answerVo vo:list) {
			if(vo.getB_dap()==null || vo.getB_dap()=="") {
				json.put("b_dap", "no");
			}else {
				json.put("b_dap", vo.getB_dap());
				json.put("b_num", vo.getB_num());
				json.put("answerdate", vo.getAnswerdate());
			}
		}
		pw.println(json);
		pw.close();
	}
}
