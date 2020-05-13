package semi.vo.yh;

public class ShipVo {
	private int s_num;
	private String s_way;
	private int s_price;
	private int a_num;
	
	public ShipVo() {}

	public ShipVo(int s_num, String s_way, int s_price, int a_num) {
		super();
		this.s_num = s_num;
		this.s_way = s_way;
		this.s_price = s_price;
		this.a_num = a_num;
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
}
