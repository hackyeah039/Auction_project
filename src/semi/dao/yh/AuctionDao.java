package semi.dao.yh;

import semi.vo.yh.AuctionVo;

public class AuctionDao {
	private static AuctionDao instance = new AuctionDao();
	
	private AuctionDao() {}
	
	public static AuctionDao getInstance() {
		return instance;
	}
	public int insert(AuctionVo vo) {
		
	}
}
