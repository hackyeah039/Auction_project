package semi.vo.yh;

public class IMGVo {
	private int a_num;
	private String i_path;
	
	public IMGVo() {}

	public IMGVo(int a_num, String i_path) {
		super();
		this.a_num = a_num;
		this.i_path = i_path;
	}

	public int getA_num() {
		return a_num;
	}

	public void setA_num(int a_num) {
		this.a_num = a_num;
	}

	public String getI_path() {
		return i_path;
	}

	public void setI_path(String i_path) {
		this.i_path = i_path;
	}
}
