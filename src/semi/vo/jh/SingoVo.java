package semi.vo.jh;

import java.sql.Date;

public class SingoVo {
	private int sel_number;//판매자번호
	private int m_num;//신고자번호
	private int singo_num;//신고번호(시퀀)
	private String singo_content;//신고내용
	private int singo_status;//신고상태
	private Date singo_date;//신고날짜
	private String m_id;//신고자 아이디
	private String singoProcess; //처리상태
	private String singo_id;
	private int searchRow;
	public SingoVo() {}
	
	public SingoVo(int sel_number, int m_num, int singo_num, 
			String singo_content, int singo_status, Date singo_date) {
		super();
		this.sel_number = sel_number;
		this.m_num = m_num;
		this.singo_num = singo_num;
		this.singo_content = singo_content;
		this.singo_status = singo_status;
		this.singo_date = singo_date;
	}
	//신고자 아이디,처리상태 추가 vo
	public SingoVo(int sel_number, int m_num, int singo_num, String singo_content, 
			int singo_status, Date singo_date,String m_id,String singoProcess) {
		super();
		this.sel_number = sel_number;
		this.m_num = m_num;
		this.singo_num = singo_num;
		this.singo_content = singo_content;
		this.singo_status = singo_status;
		this.singo_date = singo_date;
		this.m_id = m_id;
		this.singoProcess = singoProcess;
	}
	
	//신고대상자 아이디 추가 vo(전체 신고리스트 할 때)
	public SingoVo(int sel_number, int m_num, int singo_num, 
			String singo_content, int singo_status, Date singo_date,String singo_id) {
		super();
		this.sel_number = sel_number;
		this.m_num = m_num;
		this.singo_num = singo_num;
		this.singo_content = singo_content;
		this.singo_status = singo_status;
		this.singo_date = singo_date;
		this.singo_id = singo_id;
	}
	
	//전체리스트--> 검색조건 있을 때 전체 글번호 리턴값 추가
	public SingoVo(int sel_number, int m_num, int singo_num, String singo_content, 
			int singo_status, Date singo_date,String m_id,String singoProcess,int searchRow) {
		super();
		this.sel_number = sel_number;
		this.m_num = m_num;
		this.singo_num = singo_num;
		this.singo_content = singo_content;
		this.singo_status = singo_status;
		this.singo_date = singo_date;
		this.m_id = m_id;
		this.singoProcess = singoProcess;
		this.searchRow = searchRow;
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
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getSingoProcess() {
		return singoProcess;
	}
	public void setSingoProcess(String singoProcess) {
		this.singoProcess = singoProcess;
	}
	
	public String getSingo_id() {
		return singo_id;
	}
	public void setSingo_id(String singo_id) {
		this.singo_id = singo_id;
	}
	
	public int getSearchRow() {
		return searchRow;
	}
	public void setsearchRow(int searchRow) {
		this.searchRow = searchRow;
	}
	
	
}
