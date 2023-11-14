package model;

import java.time.LocalDateTime;

public class Shipment {
	private User user;
	private Product[] products;
	private LocalDateTime startingDate;
	private LocalDateTime shipmentDate;
	private String shipmentPlace;
	private String status;

	public Shipment(User user, Product[] products, LocalDateTime shipmentDate, String shipmentPlace) {
		startingDate = LocalDateTime.now();
		status = "Orden Creada";
		this.shipmentDate = shipmentDate;
		this.shipmentPlace = shipmentPlace;
		this.user = user;
		this.products = products;
	}

	public int calculateTotalPrice() {
		int total = 0;
		for(int  i = 0; i < products.length;i++) {
			if(products[i] != null) {
				total += products[i].getPrice();
			}
		}
		return total;
	}
	
	public LocalDateTime getStartingDate() {
		return startingDate;
	}

	public LocalDateTime getShipmentDate() {
		return shipmentDate;
	}

	public String getShipmentPlace() {
		return shipmentPlace;
	}

	public User getUser() {
		return user;
	}

	public Product[] getProducts() {
		return products;
	}

	public String getStatus() {
		return status;
	}

	public void setShipmentPlace(String shipmentPlace) {
		this.shipmentPlace = shipmentPlace;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
