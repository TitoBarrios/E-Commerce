package model;

public class Product {
	private String name;
	private int category;
	private int price;
	private boolean availability;
	private int quantity;
	private int sellerPointer;
	
	public Product(String name, int category, int price, int quantity, int sellerPointer) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.sellerPointer = sellerPointer;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCategory() {
		return category;
	}
	
	public int getPrice() {
		return price;
	}
	
	public boolean getAvailability() {
		return availability;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getSellerPointer() {
		return sellerPointer;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
