package model;

public class Product {
	private String name;
	private Seller seller;
	private int category;
	private int price;
	private boolean availability;
	private int quantity;

	public Product(String name, Seller seller, int category, int price, int quantity) {
		this.name = name;
		this.seller = seller;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}

	public Product(Product product) {
		this.name = product.getName();
		this.seller = product.getSeller();
		this.category = product.getCategory();
		this.price = product.getPrice();
		this.availability = product.getAvailability();
		this.quantity = product.getQuantity();
	}

	public String getName() {
		return name;
	}

	public Seller getSeller() {
		return seller;
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
