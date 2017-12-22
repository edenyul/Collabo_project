package order;

public class OrderVO {
	private int no; //�ֹ� �޴� ��ȣ
	private String menu; //�ֹ� �޴� �̸�
	private int price; //�ֹ� �޴� ����
	private int num; //�ֹ� �޴��� ����
	
	public OrderVO() {
		super();
	}
	public OrderVO(int no, int price, int num, String menu) {
		super();
		this.no = no;
		this.price = price;
		this.num = num;
		this.menu = menu;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	@Override
	public String toString() {
		return "OrderVO [no=" + no + ", menu=" + menu + ", price=" + price + ", num=" + num + "]";
	}
	
}
