package semi.vo.yr;

import java.sql.Date;

public class PaymentVo {
	private int pay_num;
	private String pay_addr;
	private int pay_status;
	private int bid_number;
	private Date pay_deadline;
	private String pay_name;
	private String pay_phone;
	
	public PaymentVo(int pay_num, String pay_addr, int pay_status, int bid_number, Date pay_deadline, String pay_name,
			String pay_phone) {
		super();
		this.pay_num = pay_num;
		this.pay_addr = pay_addr;
		this.pay_status = pay_status;
		this.bid_number = bid_number;
		this.pay_deadline = pay_deadline;
		this.pay_name = pay_name;
		this.pay_phone = pay_phone;
	}

	public PaymentVo(int pay_num, String pay_addr, int pay_status, int bid_number, Date pay_deadline) {
		super();
		this.pay_num = pay_num;
		this.pay_addr = pay_addr;
		this.pay_status = pay_status;
		this.bid_number = bid_number;
		this.pay_deadline = pay_deadline;
	}

	
	
	public PaymentVo(int pay_num, String pay_addr, String pay_name, String pay_phone) {
		super();
		this.pay_num = pay_num;
		this.pay_addr = pay_addr;
		this.pay_name = pay_name;
		this.pay_phone = pay_phone;
	}



	@Override
	public String toString() {
		return "PaymentVo [pay_num=" + pay_num + ", pay_addr=" + pay_addr + ", pay_status=" + pay_status
				+ ", bid_number=" + bid_number + ", pay_deadline=" + pay_deadline + ", pay_name=" + pay_name
				+ ", pay_phone=" + pay_phone + "]";
	}
	public String getPay_name() {
		return pay_name;
	}
	public void setPay_name(String pay_name) {
		this.pay_name = pay_name;
	}
	public String getPay_phone() {
		return pay_phone;
	}
	public void setPay_phone(String pay_phone) {
		this.pay_phone = pay_phone;
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
