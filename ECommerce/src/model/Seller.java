package model;

public class Seller extends User {
	private String startingPlace;
	private int[] productsPointer;
	private int[] salesHistoryPointers;
	
	public Seller(String address, String name, String password, String startingPlace) {
		super(address, name, password);
		this.startingPlace = startingPlace;
	}
	
	public Seller(User user, String startingPlace) {
		super(user.getAddress()[0], user.getName(), user.getPassword());
		this.setOrderHistoryPointers(user.getOrderHistoryPointers());
		this.startingPlace = startingPlace;
	}

	public String getStartingPlace() {
		return startingPlace;
	}
	
	public int[] getProductsPointer() {
		return productsPointer;
	}
	
	public int[] getSalesHistoryPointers() {
		return salesHistoryPointers;
	}
	
	public void setStartingPlace(String startingPlace) {
		this.startingPlace = startingPlace;
	}
	
	public void setProductsPointer(int[] productsPointer) {
		this.productsPointer = productsPointer;
	}
	
	public void setSalesHistorypointers(int[] salesHistoryPointers) {
		this.salesHistoryPointers = salesHistoryPointers;
	}
	
	public void addProductPointer(int productPointer) {
		for(int i = 0; i < productsPointer.length; i++) {
			if(productsPointer[i] == 0) {
				productsPointer[i] = productPointer;
				break;
			}
		}
	}
	
	public void addSaleHistoryPointer(int salesHistoryPointer) {
		for(int i = 0; i < salesHistoryPointers.length; i++) {
			if(salesHistoryPointers[i] == 0) {
				salesHistoryPointers[i] = salesHistoryPointer;
				break;
			}
		}
	}
}
