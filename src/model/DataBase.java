package model;

public class DataBase {
	private User[][] users;
	private Product[][] products;
	private Shipment[] shipmentHistory;

	private final int MAX_USERS_TYPE = 2;
	private final int MAX_USERS = 100;
	private final int MAX_CATEGORIES = 10;
	private final int MAX_PRODUCTS = 100;
	private final int MAX_SHIPMENTS = 100;

	public DataBase() {
		users = new User[MAX_USERS_TYPE][MAX_USERS];
		products = new Product[MAX_CATEGORIES][MAX_PRODUCTS];
		shipmentHistory = new Shipment[MAX_SHIPMENTS];
	}

	public User[][] getUsers() {
		return users;
	}

	public Product[][] getProducts() {
		return products;
	}

	public Shipment[] getShipmentHistory() {
		return shipmentHistory;
	}

	public void setUsers(User[][] users) {
		this.users = users;
	}

	public void setProducts(Product[][] products) {
		this.products = products;
	}

	public void setOrderHistory(Shipment[] shipmentHistory) {
		this.shipmentHistory = shipmentHistory;
	}

	public void addUser(Type type, User user) {
		for (int i = 0; i < users[type.getValue()].length; i++) {
			if (users[type.getValue()][i] == null) {
				users[type.getValue()][i] = user;
				break;
			}
		}
	}

	public void addProduct(Product product, int category) {
		for (int i = 0; i < products[category].length; i++) {
			if (products[category][i] == null) {
				products[category][i] = product;
				break;
			}
		}
	}

	public void addShipmentToShipmentHistory(Shipment shipment) {
		for (int i = 0; i < shipmentHistory.length; i++) {
			if (shipmentHistory[i] == null) {
				shipmentHistory[i] = shipment;
				break;
			}
		}
	}

	public void editUser(User user, Type type, int userPointer) {
		users[type.getValue()][userPointer] = user;
	}

	public void editCategory(Product[] category, int categoryPointer) {
		products[categoryPointer] = category;
	}

	public void editProduct(Product product, int categoryPointer, int productPointer) {
		products[categoryPointer][productPointer] = product;
	}

	public void editShipment(Shipment shipment, int shipmentPointer) {
		shipmentHistory[shipmentPointer] = shipment;
	}


}
