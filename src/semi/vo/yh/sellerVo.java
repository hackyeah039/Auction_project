package semi.vo.yh;

public class sellerVo {
	private int account;
	private int m_num;
	private int sel_num;
	
	public sellerVo() {}

	public sellerVo(int account, int m_num, int sel_num) {
		super();
		this.account = account;
		this.m_num = m_num;
		this.sel_num = sel_num;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public int getSel_num() {
		return sel_num;
	}

	public void setSel_num(int sel_num) {
		this.sel_num = sel_num;
	}
	
}
