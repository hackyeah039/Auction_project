package semi.controller.shvo;

import java.sql.Date;

public class SHAuctionVo {
	private int a_num;
	private String title;
	private String content;
	private int condition;
	private Date regdate;
	private Date startdate;
	private Date enddate;
	private int check;
	private int c_num;
	private int jjim;
	private int s_num;
	private int sel_number;
	private int bidstatus;
	private int startbid;
	private int bidunit;
	
	public SHAuctionVo() {}

	public SHAuctionVo(int a_num, String title, String content, int condition, Date regdate, Date startdate,
			Date enddate, int check, int c_num, int jjim, int s_num, int sel_number, int bidstatus, int startbid,
			int bidunit) {
		super();
		this.a_num = a_num;
		this.title = title;
		this.content = content;
		this.condition = condition;
		this.regdate = regdate;
		this.startdate = startdate;
		this.enddate = enddate;
		this.check = check;
		this.c_num = c_num;
		this.jjim = jjim;
		this.s_num = s_num;
		this.sel_number = sel_number;
		this.bidstatus = bidstatus;
		this.startbid = startbid;
		this.bidunit = bidunit;
	}

	public int getA_num() {
		return a_num;
	}

	public void setA_num(int a_num) {
		this.a_num = a_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public int getC_num() {
		return c_num;
	}

	public void setC_num(int c_num) {
		this.c_num = c_num;
	}

	public int getJjim() {
		return jjim;
	}

	public void setJjim(int jjim) {
		this.jjim = jjim;
	}

	public int getS_num() {
		return s_num;
	}

	public void setS_num(int s_num) {
		this.s_num = s_num;
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

	public int getStartbid() {
		return startbid;
	}

	public void setStartbid(int startbid) {
		this.startbid = startbid;
	}

	public int getBidunit() {
		return bidunit;
	}

	public void setBidunit(int bidunit) {
		this.bidunit = bidunit;
	}
	
	
}
