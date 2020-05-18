package semi.vo.jh;

import java.sql.Date;

public class B_answerVo {
	private String b_dap;
	private int b_num;
	private Date answerdate;
	
	public B_answerVo() {}
	public B_answerVo(String b_dap, int b_num, Date answerdate) {
		super();
		this.b_dap = b_dap;
		this.b_num = b_num;
		this.answerdate = answerdate;
	}
	public String getB_dap() {
		return b_dap;
	}
	public void setB_dap(String b_dap) {
		this.b_dap = b_dap;
	}
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public Date getAnswerdate() {
		return answerdate;
	}
	public void setAnswerdate(Date answerdate) {
		this.answerdate = answerdate;
	}
	@Override
	public String toString() {
		return "B_answerVo [b_dap=" + b_dap + ", b_num=" + b_num + ", answerdate=" + answerdate + "]";
	}
	
	
}
