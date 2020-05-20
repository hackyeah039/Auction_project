package semi.vo.yr;

import java.sql.Date;

public class BoardVo {
	private int b_num;
	private String b_title;
	private String b_content;
	private int b_status; //답변여부
	private int m_num;
	private Date b_regdate;
	private Date answerdate;
	
	public BoardVo() {}
	


	public BoardVo(int b_num, String b_title, String b_content, int b_status, int m_num, Date b_regdate,
			Date answerdate) {
		super();
		this.b_num = b_num;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_status = b_status;
		this.m_num = m_num;
		this.b_regdate = b_regdate;
		this.answerdate = answerdate;
	}

	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public int getB_status() {
		return b_status;
	}
	public void setB_status(int b_status) {
		this.b_status = b_status;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public Date getB_regdate() {
		return b_regdate;
	}
	public void setB_regdate(Date b_regdate) {
		this.b_regdate = b_regdate;
	}
	
	public Date getAnswerdate() {
		return answerdate;
	}



	public void setAnswerdate(Date answerdate) {
		this.answerdate = answerdate;
	}



	@Override
	public String toString() {
		return "BoardVo [b_num=" + b_num + ", b_title=" + b_title + ", b_content=" + b_content + ", b_status="
				+ b_status + ", m_num=" + m_num + ", b_regdate=" + b_regdate + ", answerdate=" + answerdate + "]";
	}
	
	
}
