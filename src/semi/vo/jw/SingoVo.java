package semi.vo.jw;

import java.sql.Date;

public class SingoVo {
	private int sel_number;
	private int m_num;
	private int singo_num;
	private String singo_content;
	private int singo_status;
	private Date singo_date;
	
	public SingoVo() {};
	public SingoVo(int sel_number,int m_num,int singo_num,String singo_content,int singo_status,Date singo_date) {
		this.sel_number=sel_number;
		this.m_num=m_num;
		this.singo_content=singo_content;
		this.singo_num=singo_num;
		this.singo_status=singo_status;
		this.singo_date=singo_date;
	}
	public int getSel_number() {
		return sel_number;
	}
	public void setSel_number(int sel_number) {
		this.sel_number = sel_number;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public int getSingo_num() {
		return singo_num;
	}
	public void setSingo_num(int singo_num) {
		this.singo_num = singo_num;
	}
	public String getSingo_content() {
		return singo_content;
	}
	public void setSingo_content(String singo_content) {
		this.singo_content = singo_content;
	}
	public int getSingo_status() {
		return singo_status;
	}
	public void setSingo_status(int singo_status) {
		this.singo_status = singo_status;
	}
	public Date getSingo_date() {
		return singo_date;
	}
	public void setSingo_date(Date singo_date) {
		this.singo_date = singo_date;
	};
	
	
}
