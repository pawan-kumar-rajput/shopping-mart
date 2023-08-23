package shopping.entity;

public class Product {
	private int pid;
	private String name;
	private String category;
	private Double price;
	private String image;
	public Product() {
	}
	public Product(int pid, String name, String category, Double price, String image) {
		super();
		this.pid = pid;
		this.name = name;
		this.category = category;
		this.price = price;
		this.image = image;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
