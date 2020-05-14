package semi.controller.shvo;

import java.sql.Date;

public class SHAuctionVo {
	private int a_num;
	private String a_title;
	private String a_content;
	private int a_condition;
	private Date a_regdate;
	private Date a_startdate;
	private Date a_enddate;
	private int a_check;
	private int c_num;
	private int a_jjim;
	private int sel_number;
	private int bidstatus;
	private int a_startbid;
	private int a_bidunit;
	
	public SHAuctionVo() {}
	
	public SHAuctionVo(int a_num, String a_title, String a_content, int a_condition, Date a_regdate, Date a_startdate,
			Date a_enddate, int a_check, int c_num, int a_jjim, int sel_number, int bidstatus, int a_startbid,
			int a_bidunit) {
		super();
		this.a_num = a_num;
		this.a_title = a_title;
		this.a_content = a_content;
		this.a_condition = a_condition;
		this.a_regdate = a_regdate;
		this.a_startdate = a_startdate;
		this.a_enddate = a_enddate;
		this.a_check = a_check;
		this.c_num = c_num;
		this.a_jjim = a_jjim;
		this.sel_number = sel_number;
		this.bidstatus = bidstatus;
		this.a_startbid = a_startbid;
		this.a_bidunit = a_bidunit;
	}

	public int getA_num() {
		return a_num;
	}

	public void setA_num(int a_num) {
		this.a_num = a_num;
	}

	public String getA_title() {
		return a_title;
	}

	public void setA_title(String a_title) {
		this.a_title = a_title;
	}

	public String getA_content() {
		return a_content;
	}

	public void setA_content(String a_content) {
		this.a_content = a_content;
	}

	public int getA_condition() {
		return a_condition;
	}

	public void setA_condition(int a_condition) {
		this.a_condition = a_condition;
	}

	public Date getA_regdate() {
		return a_regdate;
	}

	public void setA_regdate(Date a_regdate) {
		this.a_regdate = a_regdate;
	}

	public Date getA_startdate() {
		return a_startdate;
	}

	public void setA_startdate(Date a_startdate) {
		this.a_startdate = a_startdate;
	}

	public Date getA_enddate() {
		return a_enddate;
	}

	public void setA_enddate(Date a_enddate) {
		this.a_enddate = a_enddate;
	}

	public int getA_check() {
		return a_check;
	}

	public void setA_check(int a_check) {
		this.a_check = a_check;
	}

	public int getC_num() {
		return c_num;
	}

	public void setC_num(int c_num) {
		this.c_num = c_num;
	}

	public int getA_jjim() {
		return a_jjim;
	}

	public void setA_jjim(int a_jjim) {
		this.a_jjim = a_jjim;
	}

	public int getSel_number() {
		return sel_number;
	}

	public void setSel_number(int sel_number) {
		this.sel_number = sel_number;
	}

	public int getBidstatus() {
		return bidstatus;
	}

	public void setBidstatus(int bidstatus) {
		this.bidstatus = bidstatus;
	}

	public int getA_startbid() {
		return a_startbid;
	}

	public void setA_startbid(int a_startbid) {
		this.a_startbid = a_startbid;
	}

	public int getA_bidunit() {
		return a_bidunit;
	}

	public void setA_bidunit(int a_bidunit) {
		this.a_bidunit = a_bidunit;
	}

	@Override
	public String toString() {
		return "SHAuctionVo [a_num=" + a_num + "]";
	}

	
}
