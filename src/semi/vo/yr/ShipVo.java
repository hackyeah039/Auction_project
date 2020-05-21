package semi.vo.yr;

public class ShipVo {
	private int s_num;
	private String s_way;
	private int s_price;
	private int a_num;
	private String courier;
	private int invoicenum;
	
	
	
	@Override
	public String toString() {
		return "ShipVo [s_num=" + s_num + ", s_way=" + s_way + ", s_price=" + s_price + ", a_num=" + a_num
				+ ", courier=" + courier + ", invoicenum=" + invoicenum + "]";
	}
	
	public ShipVo(int s_num, String s_way, int s_price, int a_num, String courier, int invoicenum) {
		super();
		this.s_num = s_num;
		this.s_way = s_way;
		this.s_price = s_price;
		this.a_num = a_num;
		this.courier = courier;
		this.invoicenum = invoicenum;
	}
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
	public int getA_num() {
		return a_num;
	}
	public void setA_num(int a_num) {
		this.a_num = a_num;
	}
	public String getCourier() {
		return courier;
	}
	public void setCourier(String courier) {
		this.courier = courier;
	}
	public int getInvoicenum() {
		return invoicenum;
	}
	public void setInvoicenum(int invoicenum) {
		this.invoicenum = invoicenum;
	}
	
	
}
