package model;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Calculator {
	DataBase dataBase;

	public Calculator() {
		dataBase = new DataBase();
	}

	public boolean isUniqueUser(String name) {
		for (int i = 0; i < dataBase.getUsers().length; i++) {
			if (dataBase.getUsers()[i] != null) {
				if (dataBase.getUsers()[i].getName().equals(name)) {
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
		for (User user : dataBase.getUsers()) {
			if (user == null) {
				return false;
			}
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public boolean logInSeller(String name, String password) {
		for (Seller seller : dataBase.getSellers()) {
			if (seller != null) {
				if (seller.getName().equals(name) && seller.getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}

	public void createNewAddress(User user, String address) {
		user.addAddress(address);
	}

	public void editAddress(User user, int addressPointer, String address) {
		user.editAddress(address, addressPointer);
	}

	public void createProduct(String name, Seller seller, int category, int price, int quantity) {
		Product product = new Product(name, seller, category, price, quantity);
		dataBase.addProduct(product, category);
		seller.addProduct(product);
	}

	public boolean isProductAvailable(Product product) {
		if (product.getQuantity() > 0) {
			return true;
		}
		return false;
	}

	public void editProductQuantity(Product product, int quantity) {
		product.setQuantity(quantity);
	}

	public void createShipment(LocalDateTime shipmentDate, User user, String address, Product products[]) throws Exception {
		int total = 0;
		for (Product product : products) {
			total += product.getPrice();
			if (!isProductAvailable(product)) {
				throw new Exception("Estás intentando comprar un producto agotado");
			}
		}

		if (total > user.getWallet()) {
			throw new Exception("El usuario no tiene fondos suficientes para realizar la compra");
		} else {
			user.setWallet(user.getWallet() - total);
		}

		ArrayList<Seller> sellers = new ArrayList<>();
		ArrayList<ArrayList<Product>> soldProducts = new ArrayList<>();

		for (int i = 0; i < products.length; i++) {
			editProductQuantity(products[i], (products[i].getQuantity() - 1));
			if (i == 0) {
				sellers.add(products[i].getSeller());
				sellers.get(i).setWallet(sellers.get(i).getWallet() + products[i].getPrice());
				soldProducts.add(new ArrayList<Product>());
				soldProducts.get(i).add(products[i]);
				continue;
			}
			for (int j = 0; j < sellers.size(); j++) {
				if (sellers.get(j).equals(products[i].getSeller())) {
					sellers.get(j).setWallet(sellers.get(j).getWallet() + products[i].getPrice());
					soldProducts.get(j).add(products[i]);
				} else if (j == sellers.size() - 1) {
					int length = sellers.size();
					sellers.add(products[i].getSeller());
					sellers.get(length).setWallet(sellers.get(length).getWallet() + products[i].getPrice());
					soldProducts.add(new ArrayList<Product>());
					soldProducts.get(length).add(products[i]);
				}
			}
			products[i].setQuantity(products[i].getQuantity() - 1);
		}

		ArrayList<Shipment> shipments = new ArrayList<>();
		for (int i = 0; i < sellers.size(); i++) {
			shipments.add(new Shipment(user, soldProducts.get(i).toArray(new Product[soldProducts.size()]),
					shipmentDate, address));
			sellers.get(i).addShipment(shipments.get(i));
		}

		Shipment shipment = new Shipment(user, products, shipmentDate, address);
		dataBase.addShipmentToShipmentHistory(shipment);
		user.addShipment(shipment);
	}

	public void changeShipmentStatus(Shipment shipment, int statusInt) {
		String status;
		switch (statusInt) {
		case 0:
			status = "Orden Creada";
			break;
		case 1:
			status = "Compra Verificada";
			break;
		case 2:
			status = "El vendedor envió el producto";
			break;
		case 3:
			status = "El producto llegó a una estación de reparto";
			break;
		case 4:
			status = "El producto ha salido de una estación de reparto";
			break;
		case 5:
			status = "El producto ha sido transferido a un repartidor local";
			break;
		case 6:
			status = "El producto ha llegado a un centro de entrega local";
			break;
		case 7:
			status = "El producto llegará hoy";
			break;
		case 8:
			status = "El producto está listo para recoger en un centro de entrega local";
			break;
		case 9:
			status = "El producto ha sido entregado";
			break;
		case 10:
			status = "Se está procesando la cancelación de la compra";
			break;
		case 11:
			status = "La compra no se ha podido cancelar";
			break;
		case 12:
			status = "La compra se ha cancelado";
			break;
		case 13:
			status = "Pendiente de llegada para su devolución";
			break;
		case 14:
			status = "El producto no ha llegado en las fechas establecidas, la devolución ha sido cancelada";
			break;
		case 15:
			status = "Se está procesando la devolución del producto";
			break;
		case 16:
			status = "No se ha aprobado la devolución, estamos procesando el envío de su producto";
			break;
		case 17:
			status = "La devolución ha sido aprobada, su dinero ha sido devuelto";
			break;
		case 18:
			status = "La devolución ha sido aprobada, estamos procesando el envío de un nuevo producto";
			break;
		case 19:
			status = "Un reemplazo ha sido enviado";
			break;
		case 20:
			status = "Ha ocurrido un retraso en el envío de su paquete";
			break;
		case 21:
			status = "El producto no se ha podido entregar";
			break;
		default:
			status = "Orden Creada";
			break;
		}
		shipment.setStatus(status);
	}

	public DataBase getDataBase() {
		return dataBase;
	}
}