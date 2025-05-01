package ex0416.el;

public class Product {
	private String code="A01";
	private String name="꿀꽈배기";
	private int qty=5;
	private int price=3500;
	
	public Product() {
		System.out.println("Product 생성자 call");
	}
	
	public String getCode() {
		System.out.println("getCode() call");
		return code;
	}
	
	public String getName() {
		System.out.println("getName() call");
		return name;
	}
	
	public int getQty() {
		System.out.println("getQty() call");
		return qty;
	}
	
	public int getPrice() {
		System.out.println("getPrice() call");
		return price;
	}
}
