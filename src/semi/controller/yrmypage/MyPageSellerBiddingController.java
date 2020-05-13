package semi.controller.yrmypage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.dao.yr.AuctionDao;
import semi.vo.yr.AuctionVo;

@WebServlet("/mypage/sellerBidding.do")
public class MyPageSellerBiddingController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = "test2";
		
//		회원 번호 session으로 가져오기
//		int m_num= (int)req.getSession().getAttribute("m_num");
		
		
		AuctionDao auctiondao  = new AuctionDao();
		
		//판매자 번호
		ArrayList<Integer> selNumberList = auctiondao.getSelnum(id);
		for (int selnum : selNumberList ) {
			System.out.println("판매자 번호 : " + selnum);
		}
		
		//현재 판매자가 입찰중인 판매 리스트
		ArrayList<Integer> anumList = auctiondao.sellerBidinglist(selNumberList);

		for (int anum : anumList) {
			System.out.println(anum);
		}
		
		//입찰 수
		HashMap<Integer, Integer> getBidCountList = auctiondao.getBidCount(anumList);

        for(int key : getBidCountList.keySet()){
 
            int value = getBidCountList.get(key);
 
            System.out.println(key+" : "+value);
 
        }
        
        //판매품 정보
	}	
}
