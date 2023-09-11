package model;

import java.time.LocalDateTime;

public class Calculator {
	DataBase dataBase;
	
	public Calculator() {
		dataBase = new DataBase();
	}
	
	public boolean isUniqueUser(String name) {
		for(int  i = 0; i < dataBase.getUsers().length; i++) {
			if(dataBase.getUsers()[i] != null) {
				if(dataBase.getUsers()[i].getName().equals(name)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void createUser(String address, String name, String password) {
		User user = new User(address, name, password);
		dataBase.addUser(user);
	}
	
	public void becomeSeller(int userPointer, String startingPlace) {
		Seller seller = new Seller(dataBase.getUsers()[userPointer], startingPlace);
		dataBase.editUser(null, userPointer);
		dataBase.addSeller(seller);
	}
	
	public boolean logInUser(String name, String password) {
		for(int i = 0; i < dataBase.getUsers().length; i++) {
			if(dataBase.getUsers() == null) {
				return false;
			}
			if(dataBase.getUsers()[i].getName().equals(name) && dataBase.getUsers()[i].getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean logInSeller(String name, String password) {
		for(int i = 0; i < dataBase.getSellers().length; i++) {
			if(dataBase.getSellers()[i] != null) {
				if(dataBase.getSellers()[i].getName().equals(name) && dataBase.getSellers()[i].getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void createNewAddress(int userPointer, String address) {
		dataBase.getUsers()[userPointer].addAddress(address);
	}
	
	public void editAddress(int userPointer, int addressPointer, String address) {
		dataBase.getUsers()[userPointer].editAddress(address, addressPointer);;
	}
	
	public void createProduct(String name, int category, int price, int quantity, int sellerPointer) {
		Product product = new Product(name, category, price, quantity, sellerPointer);
		int productPointer = dataBase.returnAndAddProduct(product, category);
		dataBase.getSellers()[sellerPointer].addProductPointer(productPointer);
	}
	
	public boolean isProductAvailable(int[] productPointer) {
		if(dataBase.getProducts()[productPointer[0]][productPointer[1]].getQuantity() > 0) {
			return true;
		}
		return false;
	}
	
	public void editProductQuantity(int[] productPointer, int quantityChange) {
		dataBase.getProducts()[productPointer[0]][productPointer[1]].setQuantity(dataBase.getProducts()[productPointer[0]][productPointer[1]].getQuantity() + quantityChange);
	}
	
	
	public void createShipment(LocalDateTime shipmentDate, int userPointer, int addressPointer, int productPointer[]) {
		if(isProductAvailable(productPointer)) {
			editProductQuantity(productPointer, -1);
			Shipment shipment= new Shipment(shipmentDate, userPointer, addressPointer, productPointer);
			int shipmentPointer = dataBase.returnAndAddShipmentToShipmentHistory(shipment);
			User user = dataBase.getUsers()[userPointer];
			user.addOrderHistoryPointers(shipmentPointer);
			dataBase.editUser(user, userPointer);
			Seller seller = dataBase.getSellers()[dataBase.getProducts()[productPointer[0]][productPointer[1]].getSellerPointer()];
			seller.addOrderHistoryPointers(shipmentPointer);
			dataBase.editSeller(seller, dataBase.getProducts()[productPointer[0]][productPointer[1]].getSellerPointer());
		}	
	}
	
	public void changeShipmentStatus(int shipmentPointer, int statusInt) {
		Shipment shipment = dataBase.getShipmentHistory()[shipmentPointer];
		String status;
		switch(statusInt) {
		case 0: status = "Orden Creada";
			break;
		case 1: status = "Compra Verificada";
			break;
		case 2: status = "El vendedor envió el producto";
			break;
		case 3: status = "El producto llegó a una estación de reparto";
			break;
		case 4: status = "El producto ha salido de una estación de reparto";
			break;
		case 5: status = "El producto ha sido transferido a un repartidor local";
			break;
		case 6: status = "El producto ha llegado a un centro de entrega local";
			break;
		case 7: status = "El producto llegará hoy";
			break;
		case 8: status = "El producto está listo para recoger en un centro de entrega local";
			break;
		case 9: status = "El producto ha sido entregado";
			break;
		case 10: status = "Se está procesando la cancelación de la compra";
			break;
		case 11: status = "La compra no se ha podido cancelar";
			break;
		case 12: status = "La compra se ha cancelado";
			break;
		case 13: status = "Pendiente de llegada para su devolución";
			break;
		case 14: status = "El producto no ha llegado en las fechas establecidas, la devolución ha sido cancelada";
			break;
		case 15: status = "Se está procesando la devolución del producto";
			break;
		case 16: status = "No se ha aprobado la devolución, estamos procesando el envío de su producto";
			break;
		case 17: status = "La devolución ha sido aprobada, su dinero ha sido devuelto";
			break;
		case 18: status = "La devolución ha sido aprobada, estamos procesando el envío de un nuevo producto";
			break;
		case 19: status = "Un reemplazo ha sido enviado";
			break;
		case 20: status = "Ha ocurrido un retraso en el envío de su paquete";
			break;
		case 21: status = "El producto no se ha podido entregar";
			break;
		default: status = "Orden Creada";
			break;
		}
		shipment.setStatus(status);
		dataBase.editShipment(shipment, shipmentPointer);
	}
	
	public DataBase getDataBase() {
		return dataBase;
	}
}