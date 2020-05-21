package semi.vo.yr;

import java.sql.Date;

public class BidVo {
	int m_num;
	int a_num;
	int bid_price;
	Date bid_date;
	int bid_num;
	
	public BidVo(int m_num, int a_num, int bid_price, Date bid_date, int bid_num) {
		super();
		this.m_num = m_num;
		this.a_num = a_num;
		this.bid_price = bid_price;
		this.bid_date = bid_date;
		this.bid_num = bid_num;
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
	public int getBid_num() {
		return bid_num;
	}
	public void setBid_num(int bid_num) {
		this.bid_num = bid_num;
	}
	
	@Override
	public String toString() {
		return getA_num() + "," + getBid_num() +","+ getBid_price() 
		+ "," + getM_num() + "," + getBid_date();
	}
}
