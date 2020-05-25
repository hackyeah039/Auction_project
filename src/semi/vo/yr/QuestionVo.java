package semi.vo.yr;

import java.sql.Date;

/**
 * @author yurae
 *
 */
public class QuestionVo {
	private int que_num;
	private String que_title;
	private String que_content;
	private int m_num;
	private int a_num;
	private String que_status;
	private Date que_regdate;
	
	
	public QuestionVo(int que_num, String que_title, String que_content, int m_num, int a_num, String que_status,
			Date que_regdate) {
		super();
		this.que_num = que_num;
		this.que_title = que_title;
		this.que_content = que_content;
		this.m_num = m_num;
		this.a_num = a_num;
		this.que_status = que_status;
		this.que_regdate = que_regdate;
	}
	
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
	public int getA_num() {
		return a_num;
	}
	public void setA_num(int a_num) {
		this.a_num = a_num;
	}
	public String getQue_status() {
		return que_status;
	}
	public void setQue_status(String que_status) {
		this.que_status = que_status;
	}
	public Date getQue_regdate() {
		return que_regdate;
	}
	public void setQue_regdate(Date que_regdate) {
		this.que_regdate = que_regdate;
	}

	@Override
	public String toString() {
		return "QuestionVo [que_num=" + que_num + ", que_title=" + que_title + ", que_content=" + que_content
				+ ", m_num=" + m_num + ", a_num=" + a_num + ", que_status=" + que_status + ", que_regdate="
				+ que_regdate + "]";
	}
	
	
	
	
}
