package semi.controller.shvo;

import java.sql.Date;

public class SHMembersVo {
	private int m_num;
	private String m_name;
	private String m_email;
	private int phone;
	private String addr;
	private String id;
	private String pwd;
	private int trust;
	private int type;
	private Date regdate;
	
	public SHMembersVo() {}

	public SHMembersVo(int m_num, String m_name, String m_email, int phone, String addr, String id, String pwd,
			int trust, int type, Date regdate) {
		super();
		this.m_num = m_num;
		this.m_name = m_name;
		this.m_email = m_email;
		this.phone = phone;
		this.addr = addr;
		this.id = id;
		this.pwd = pwd;
		this.trust = trust;
		this.type = type;
		this.regdate = regdate;
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getTrust() {
		return trust;
	}

	public void setTrust(int trust) {
		this.trust = trust;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
