package semi.controller.jhsingo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import semi.dao.jh.SingoDao;
@WebServlet("/singo/approval.jh")
public class SingoApprovalController extends HttpServlet{ //신고승인
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		int singo_num=Integer.parseInt(req.getParameter("singo_num"));
		SingoDao dao=SingoDao.getSingoDao();
		int trust=dao.getTrust(id);
		resp.setContentType("text/plain;charset=utf-8");
		String msg=null;
		if(trust<=0) {	//신뢰도가 0이면 json으로 max메세지 보내기
			msg="max";
		}else {			//신뢰도가 0이 아니면 신뢰도 깍고 ok메세지 보내기
			int n=dao.trustDown(id,singo_num);
			if(n==2) { //신뢰도 깍기 성공하면 ok메세지
				msg="ok";
			}else {		//실패하면 error메세지 전송
				msg="error";
			}
		}
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/admin/adminIndex.jsp?file=singoOk.jsp").forward(req, resp);
	}
}
