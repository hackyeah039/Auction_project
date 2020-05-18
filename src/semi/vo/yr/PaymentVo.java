package semi.vo.yr;

import java.sql.Date;

public class PaymentVo {
	private int pay_num;
	private String pay_addr;
	private int pay_status;
	private int bid_number;
	private Date pay_deadline;
	
	
	public PaymentVo(int pay_num, String pay_addr, int pay_status, int bid_number, Date pay_deadline) {
		super();
		this.pay_num = pay_num;
		this.pay_addr = pay_addr;
		this.pay_status = pay_status;
		this.bid_number = bid_number;
		this.pay_deadline = pay_deadline;
	}
	
	public int getPay_num() {
		return pay_num;
	}
	public void setPay_num(int pay_num) {
		this.pay_num = pay_num;
	}
	public String getPay_addr() {
		return pay_addr;
	}
	public void setPay_addr(String pay_addr) {
		this.pay_addr = pay_addr;
	}
	public int getPay_status() {
		return pay_status;
	}
	public void setPay_status(int pay_status) {
		this.pay_status = pay_status;
	}
	public int getBid_number() {
		return bid_number;
	}
	public void setBid_number(int bid_number) {
		this.bid_number = bid_number;
	}
	public Date getPay_deadline() {
		return pay_deadline;
	}
	public void setPay_deadline(Date pay_deadline) {
		this.pay_deadline = pay_deadline;
	}
	
	
}
