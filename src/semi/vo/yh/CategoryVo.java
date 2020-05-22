package semi.vo.yh;

public class CategoryVo {
	private int c_num;
	private String c_des;
	
	public CategoryVo() {}

	public CategoryVo(int c_num, String c_des) {
		super();
		this.c_num = c_num;
		this.c_des = c_des;
	}

	public int getC_num() {
		return c_num;
	}

	public void setC_num(int c_num) {
		this.c_num = c_num;
	}

	public String getC_des() {
		return c_des;
	}

	public void setC_des(String c_des) {
		this.c_des = c_des;
	}

	//바로 출력할 수 있도록 
	@Override
	public String toString() {
		return "CategoryVo [c_num=" + c_num + ", c_des=" + c_des + "]";
	}
	
	
}
