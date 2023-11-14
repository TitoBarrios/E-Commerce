package model;

public class User {
	private String name;
	private String password;
	private String[] address;
	private Shipment[] orderHistory;
	private int wallet;

	private final int MAX_ADDRESS = 10;

	public User(String name, String password, String address) {
		this.address = new String[MAX_ADDRESS];
		this.address[0] = address;
		this.name = name;
		this.password = password;
	}

	public String[] getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Shipment[] getOrderHistory() {
		return orderHistory;
	}
	
	public int getWallet() {
		return wallet;
	}

	public void setAddress(String[] address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setOrderHistory(Shipment[] orderHistory) {
		this.orderHistory = orderHistory;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}
	
	public void addAddress(String address) {
		for (int i = 0; i < this.address.length; i++) {
			if (this.address[i] != null) {
				this.address[i] = address;
				break;
			}
		}
	}

	public void addShipment(Shipment order) {
		for (int i = 0; i < orderHistory.length; i++) {
			if (orderHistory[i] == null) {
				orderHistory[i] = order;
			}
		}
	}

	public void editAddress(String address, int addressPointer) {
		this.address[addressPointer] = address;
	}

	public void editOrderHistory(Shipment order, int pointer) {
		orderHistory[pointer] = order;
	}
}
