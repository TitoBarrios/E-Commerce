package model;

public class User {
	private String[] address;
	private String name;
	private String password;
	private int[] orderHistoryPointers;
	
	private final int MAX_ADDRESS = 10;
	
	public User(String address, String name, String password) {
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
	
	public int[] getOrderHistoryPointers() {
		return orderHistoryPointers;
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
	
	public void setOrderHistoryPointers(int[] orderHistoryPointers) {
		this.orderHistoryPointers = orderHistoryPointers;
	}
	
	public void addAddress(String address) {
		for(int i = 0; i < this.address.length; i++) {
			if(this.address[i] != null) {
				this.address[i] = address;
				break;
			}
		}
	}
	
	public void addOrderHistoryPointers(int orderHistoryPointer) {
		for(int i = 0; i < orderHistoryPointers.length; i++) {
			if(orderHistoryPointers[i] == 0) {
				orderHistoryPointers[i] = orderHistoryPointer;
			}
		}
	}
	
	public void editAddress(String address, int addressPointer) {
		this.address[addressPointer] = address;
	}
	
	public void editOrderHistoryPointer(int orderHistoryPointer, int pointer) {
		orderHistoryPointers[pointer] = orderHistoryPointer;
	}
}
