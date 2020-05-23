package semi.vo.yh;

import java.sql.Date;

public class MembersVo {
	private int m_num; //회원번호(시퀀)
	private String m_name; //회원이름
	private String m_email; //이메일
	private int m_phone; //전번
	private String m_addr; //주소
	private String m_id; //아이디
	private String m_pwd; //비번
	private int trust; //신뢰도
	private int m_type; //회원타입
	private Date m_regdate;//가입일
	
	public MembersVo() {}

	public MembersVo(int m_num, String m_name, String m_email, int m_phone, String m_addr, String m_id, String m_pwd,
			int trust, int m_type, Date m_regdate) {
		super();
		this.m_num = m_num;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_phone = m_phone;
		this.m_addr = m_addr;
		this.m_id = m_id;
		this.m_pwd = m_pwd;
		this.trust = trust;
		this.m_type = m_type;
		this.m_regdate = m_regdate;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public int getM_phone() {
		return m_phone;
	}

	public void setM_phone(int m_phone) {
		this.m_phone = m_phone;
	}

	public String getM_addr() {
		return m_addr;
	}

	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_pwd() {
		return m_pwd;
	}

	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}

	public int getTrust() {
		return trust;
	}

	public void setTrust(int trust) {
		this.trust = trust;
	}

	public int getM_type() {
		return m_type;
	}

	public void setM_type(int m_type) {
		this.m_type = m_type;
	}

	public Date getM_regdate() {
		return m_regdate;
	}

	public void setM_regdate(Date m_regdate) {
		this.m_regdate = m_regdate;
	}
}
