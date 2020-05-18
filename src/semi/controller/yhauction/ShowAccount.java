package semi.controller.yhauction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yh.SellerDao;
import semi.vo.yh.SellerVo;
@WebServlet("/ShowAccount.do")
public class ShowAccount extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//int m_num =  (int)req.getAttribute("m_num");
		int m_num = 1;
		SellerDao sdao = SellerDao.getInstance();
		ArrayList<SellerVo> list = sdao.listAccount(m_num);
		System.out.println(list);
		req.setAttribute("list", list);
		//데이터가 변수에 들어갔는지 아닌지 구분하기 위해 size를 보냄
		req.setAttribute("size", list.size());
		
		req.getRequestDispatcher("/Auction/showAccount.jsp").forward(req , resp);
	}
}
