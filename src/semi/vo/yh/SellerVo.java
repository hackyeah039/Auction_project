package semi.vo.yh;

public class SellerVo {
	private Long account;
	private int m_num;
	private int sel_number;
	
	public SellerVo() {}

	public SellerVo(Long account, int m_num, int sel_number) {
		super();
		this.account = account;
		this.m_num = m_num;
		this.sel_number = sel_number;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public int getSel_number() {
		return sel_number;
	}

	public void setSel_number(int sel_number) {
		this.sel_number = sel_number;
	}
	
}
