package semi.vo.yh;

import java.sql.Date;

public class BiddingVo {
	private int m_num;
	private int a_num;
	private int bid_price;
	private Date bid_date;
	private int bid_number;

	public BiddingVo() {}

	public BiddingVo(int m_num, int a_num, int bid_price, Date bid_date, int bid_number) {
		super();
		this.m_num = m_num;
		this.a_num = a_num;
		this.bid_price = bid_price;
		this.bid_date = bid_date;
		this.bid_number = bid_number;
	}



	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public int getA_num() {
		return a_num;
	}

	public void setA_num(int a_num) {
		this.a_num = a_num;
	}

	public int getBid_price() {
		return bid_price;
	}

	public void setBid_price(int bid_price) {
		this.bid_price = bid_price;
	}

	public Date getBid_date() {
		return bid_date;
	}

	public void setBid_date(Date bid_date) {
		this.bid_date = bid_date;
	}

	public int getBid_number() {
		return bid_number;
	}

	public void setBid_number(int bid_number) {
		this.bid_number = bid_number;
	}
	
	
}
