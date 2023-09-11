package model;

public class DataBase {
	private User[] users;
	private Seller[] sellers;
	private Product[][] products;
	private Shipment[] shipmentHistory;
	
	private final int MAX_USERS = 100;
	private final int MAX_SELLERS = 100;
	private final int MAX_CATEGORIES = 10;
	private final int MAX_PRODUCTS = 100;
	private final int MAX_SHIPMENTS = 100;
	
	public DataBase() {
		users = new User[MAX_USERS];
		sellers = new Seller[MAX_SELLERS];
		products = new Product[MAX_CATEGORIES][MAX_PRODUCTS];
		shipmentHistory = new Shipment[MAX_SHIPMENTS];
	}

	public User[] getUsers() {
		return users;
	}
	
	public Seller[] getSellers() {
		return sellers;
	}
	
	public Product[][] getProducts(){
		return products;
	}
	
	public Shipment[] getShipmentHistory() {
		return shipmentHistory;
	}
	
	public void setUsers(User[] users) {
		this.users = users;
	}
	
	public void setSellers(Seller[] sellers) {
		this.sellers = sellers;
	}
	
	public void setProducts(Product[][] products) {
		this.products = products;
	}
	
	public void setOrderHistory(Shipment[] shipmentHistory) {
		this.shipmentHistory = shipmentHistory;
	}
	
	public void addUser(User user) {
		for(int  i = 0; i < users.length; i++) {
			if(users[i] == null) {
				users[i] = user;
				break;
			}
		}
	}
	
	public void addSeller(Seller seller) {
		for(int i = 0; i < sellers.length; i++) {
			if(sellers[i] == null) {
				sellers[i] = seller;
				break;
			}
		}
	}
	
	public void addProduct(Product product, int category) {
		for(int i = 0; i < products[category].length; i++) {
			if(products[category][i] == null) {
				products[category][i] = product;
				break;
			}
		}
	}
	
	public void addShipmentToShipmentHistory(Shipment shipment) {
		for(int i = 0; i < shipmentHistory.length; i++) {
			if(shipmentHistory[i] == null) {
				shipmentHistory[i] = shipment;
				break;
			}
		}
	}
	
	public int returnAndAddUser(User user) {
		for(int  i = 0; i < users.length; i++) {
			if(users[i] == null) {
				users[i] = user;
				return i;
			}
		}
		return 0;
	}
	
	public int returnAndAddSeller(Seller seller) {
		for(int i = 0; i < sellers.length; i++) {
			if(sellers[i] == null) {
				sellers[i] = seller;
				return i;
			}
		}
		return 0;
	}
	
	public int returnAndAddProduct(Product product, int category) {
		for(int i = 0; i < products[category].length; i++) {
			if(products[category][i] == null) {
				products[category][i] = product;
				return i;
			}
		}
		return 0;
	}
	
	public int returnAndAddShipmentToShipmentHistory(Shipment shipment) {
		for(int i = 0; i < shipmentHistory.length; i++) {
			if(shipmentHistory[i] == null) {
				shipmentHistory[i] = shipment;
				return i;
			}
		}
		return 0;
	}
	
	public void editUser(User user, int userPointer) {
		users[userPointer] = user;
	}
	
	public void editSeller(Seller seller, int sellerPointer) {
		sellers[sellerPointer] = seller;
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
