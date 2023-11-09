package model;

public class Seller extends User {
	private String startingPlace;
	private Product[] products;
	private Shipment[] salesHistory;

	public Seller(String address, String name, String password, String startingPlace) {
		super(address, name, password);
		this.startingPlace = startingPlace;
	}

	public Seller(User user, String startingPlace) {
		super(user.getAddress()[0], user.getName(), user.getPassword());
		this.setOrderHistory(user.getOrderHistory());
		this.startingPlace = startingPlace;
	}

	public String getStartingPlace() {
		return startingPlace;
	}

	public Product[] getProducts() {
		return products;
	}

	public Shipment[] getSalesHistory() {
		return salesHistory;
	}

	public void setStartingPlace(String startingPlace) {
		this.startingPlace = startingPlace;
	}

	public void setProductsPointer(Product[] products) {
		this.products = products;
	}

	public void setSalesHistory(Shipment[] salesHistory) {
		this.salesHistory = salesHistory;
	}

	public void addProduct(Product product) {
		for (int i = 0; i < products.length; i++) {
			if (products[i] == null) {
				products[i] = product;
				break;
			}
		}
	}

	public void addSale(Shipment order) {
		for (int i = 0; i < salesHistory.length; i++) {
			if (salesHistory[i] == null) {
				salesHistory[i] = order;
				break;
			}
		}
	}
}
