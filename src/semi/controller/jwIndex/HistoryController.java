package semi.controller.jwIndex;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.jw.AuctionDao;
import semi.vo.jw.BidVo;
@WebServlet("/history.do")
//doGet으로 받고->deatilview.jsp() -> selectBox  
public class HistoryController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//여기서 BID 테이블에 있는 정보를 detailview에 AllList()메소드로 뿌리기
		//근데 selectBox는 처음에 10개가 고정이니깐 10개 만 뿌리는 페이징 처리 ㄱㄱ
		System.out.println("doGet이지롱");
		AuctionDao dao=new AuctionDao();
		BidVo vo=dao.list(11);//경매번호 파라미터
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//selectBox의 option value를 얻어와서 얻은걸 int로 형변환 &&
		//그걸 페이징처리하고 다시 AllList()메소드 써서 페이징처리 ㄱㄱ
		//Ajax?
	}
}
