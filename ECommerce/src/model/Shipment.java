package model;

import java.time.LocalDateTime;

public class Shipment {
	private LocalDateTime startingDate;
	private LocalDateTime shipmentDate;
	private int shipmentPlacePointer;
	private int userPointer;
	private int[] productPointer;
	private String status;

	
	public Shipment(LocalDateTime shipmentDate, int shipmentPlacePointer, int userPointer, int[] productPointer) {
		startingDate = LocalDateTime.now();
		status = "Orden Creada";
		this.shipmentDate = shipmentDate;
		this.shipmentPlacePointer = shipmentPlacePointer;
		this.userPointer = userPointer;
		this.productPointer = productPointer;
	}
	
	public LocalDateTime getStartingDate() {
		return startingDate;
	}
	
	public LocalDateTime getShipmentDate() {
		return shipmentDate;
	}
	
	public int getShipmentPlacePointer() {
		return shipmentPlacePointer;
	}
	
	public int getUserPointer() {
		return userPointer;
	}
	
	public int[] getProductPointer() {
		return productPointer;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setShipmentPlacePointer(int shipmentPlacePointer) {
		this.shipmentPlacePointer = shipmentPlacePointer;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
