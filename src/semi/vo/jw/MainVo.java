package semi.vo.jw;

import java.sql.Date;

public class MainVo {
	
	private String path;
	private int a_num;
	private String a_title;
	private String a_content;
	private String a_condition;
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
	private int s_num;
	private String s_way;
	private int s_price;
	private Date ans_regdate;
	
	public Date getAns_regdate() {
		return ans_regdate;
	}
	public void setAns_regdate(Date ans_regdate) {
		this.ans_regdate = ans_regdate;
	}
	int que_num;
	String que_title;
	String que_content;
	int m_num;
	int que_status;
	Date que_regdate;
	String b_content;
	int rnum;
	
	public int getQue_num() {
		return que_num;
	}
	public void setQue_num(int que_num) {
		this.que_num = que_num;
	}
	public String getQue_title() {
		return que_title;
	}
	public void setQue_title(String que_title) {
		this.que_title = que_title;
	}
	public String getQue_content() {
		return que_content;
	}
	public void setQue_content(String que_content) {
		this.que_content = que_content;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public int getQue_status() {
		return que_status;
	}
	public void setQue_status(int que_status) {
		this.que_status = que_status;
	}
	public Date getQue_regdate() {
		return que_regdate;
	}
	public void setQue_regdate(Date que_regdate) {
		this.que_regdate = que_regdate;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public MainVo() {};
	public MainVo(String path) {
		this.path=path;
	};
	//경매 전체
	public MainVo(int a_num,String a_title,String a_content,String a_condition,Date a_regdate,Date a_startdate,Date a_enddate,int a_check,int c_num,int a_jjim,int sel_number,int bidstatus,int a_startbid, int a_bidunit) {
		this.a_num =a_num;
		this.a_title =a_title;
		this.a_content=a_content;
		this.a_condition=a_condition;
		this.a_regdate =a_regdate;
		this.a_startdate=a_startdate;
		this.a_enddate=a_enddate;
		this.a_check=a_check;
		this.c_num=c_num;
		this.a_jjim=a_jjim;
		this.sel_number=sel_number;
		this.bidstatus=bidstatus;
		this.a_startbid=a_startbid;
		this.a_bidunit=a_bidunit;
	}
	//경매물품 상세
	public MainVo(int a_num,Date a_startdate,Date a_enddate,int a_startbid,int a_bidunit,String a_content,String a_title) {
		this.a_num=a_num;
		this.a_startdate =a_startdate;
		this.a_enddate=a_enddate;
		this.a_startbid=a_startbid;
		this.a_bidunit=a_bidunit;
		this.a_content=a_content;
		this.a_title=a_title;
	}
	
	//배송방법
	public MainVo(int s_num,String s_way,int s_price,int a_num) {
		this.s_num=s_num;
		this.s_way=s_way;
		this.s_price=s_price;
		this.a_num=a_num;
	}
	
	
	//문의게시판
	public MainVo(int que_num,String que_title,String que_content,int m_num,int que_status,Date que_regdate,String b_content,int rnum,Date ans_regdate) {
		this.que_num=que_num;
		this.que_title=que_title;
		this.que_content=que_content;
		this.m_num=m_num;
		this.que_status=que_status;
		this.que_regdate=que_regdate;
		this.b_content=b_content;
		this.rnum=rnum;
		this.ans_regdate =ans_regdate;
	};
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public String getS_way() {
		return s_way;
	}
	public void setS_way(String s_way) {
		this.s_way = s_way;
	}
	public int getS_price() {
		return s_price;
	}
	public void setS_price(int s_price) {
		this.s_price = s_price;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
	public String getA_condition() {
		return a_condition;
	}
	public void setA_condition(String a_condition) {
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
		return "MainVo [path=" + path + ", a_num=" + a_num + ", a_title=" + a_title + ", a_content=" + a_content
				+ ", a_condition=" + a_condition + ", a_regdate=" + a_regdate + ", a_startdate=" + a_startdate
				+ ", a_enddate=" + a_enddate + ", a_check=" + a_check + ", c_num=" + c_num + ", a_jjim=" + a_jjim
				+ ", sel_number=" + sel_number + ", bidstatus=" + bidstatus + ", a_startbid=" + a_startbid
				+ ", a_bidunit=" + a_bidunit + ", s_num=" + s_num + ", s_way=" + s_way + ", s_price=" + s_price + "]";
	}
	
	
}
