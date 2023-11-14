package presenter;

import java.util.ArrayList;

import model.Calculator;
import model.Product;
import model.Seller;
import model.Shipment;
import model.Type;
import model.User;
import view.View;

public class Presenter {
	private View view;
	private Calculator calculator;

	public Presenter() {
		view = new View();
		calculator = new Calculator();
	}

	public void run() {
		int option = 0;
		int category;
		User currentUser = null;
		
		
		do {
			view.Log("Bienvenido a ShopPeTC\n1. Iniciar Sesión\n2. Registrarme \n0. Salir");

			try {
				option = view.readNumber();
			} catch (NumberFormatException e) {
				view.Log("Digita un número entero válido");
				option = -1;
				continue;
			}

			switch (option) {
			case 1:
				view.Log("Digita tu nombre de usuario");
				String userName = view.readData();
				view.Log("Digita tu contraseña");
				String password = view.readData();
				
				if (calculator.logInUser(userName, password)) {
					currentUser = calculator.searchUser(userName, password);
					
					if(currentUser.getClass().getName().equals("model.Seller")) {
						view.Log(
							"1.Categorias\n2. Mi carrito\n3. Historial de Compras" +
							"\n5. Cerrar Sesión\n");
						try {
							option = view.readNumber();
						} catch (NumberFormatException e) {
							view.Log("Digita un número entero válido");
							option = -1;
							break;
						}
						
						switch(option) {
						case 1:
							do{
								view.Log("Seleccione una categoria \n1.Tecnologia \n2.Moda \n3.Hogar \n4.Deportes \n5.Mascotas \n6.Juguetes \n7.Salud \n8.Belleza \n9.Joyeria \n10.Papeleria \n0.Volver");
								try {
									option = view.readNumber();
								} catch (NumberFormatException e) {
									view.Log("Digita un número entero válido");
									option = -1;
									break;
								}

								for(int i = 0; i < calculator.getDataBase().getProducts()[option].length; i++){
									Product current = calculator.getDataBase().getProducts()[option][i];
									view.Log((i + 1) + ". " + current.getName() + " Precio: " + current.getPrice() + " Cantidad: " + current.getQuantity());
								}
							
								int _op;
								int currentTotal = 0;
								ArrayList<Product> cart = new ArrayList<>();
								do{
									view.Log("Seleccione si desea añadir al carrito \n	Seleccione 0 si desea volver");
									try {
										_op = view.readNumber();
									} catch (NumberFormatException e) {
										view.Log("Digita un número entero válido");
										_op = -1;
										break;
									}
									cart.add(calculator.getDataBase().getProducts()[option][_op]);
									currentTotal += calculator.getDataBase().getProducts()[option][_op].getPrice();
									view.Log("Producto añadido exitosamente! \n Total: " + currentTotal);
									
								}while(_op != 0);

								view.Log("Elige una direccion de envio");
								for(int i = 0; i < currentUser.getAddress().length; i++){
									view.Log(currentUser.getAddress()[i]); 
								}

								int curretAddres;
								try {
									curretAddres = view.readNumber();
								} catch (NumberFormatException e) {
									view.Log("Digita un número entero válido");
									curretAddres = -1;
									break;
								}

								try{
									calculator.createShipment(currentUser, currentUser.getAddress()[curretAddres], (Product[]) cart.toArray());
								}catch (Exception ex){
									view.Log("No se ha podido crear la orden, agregue más fondos a su billetera e intente nuevamente :)");
								}
								break;

							}while(option != 0);
						}
					}else if(currentUser.getClass().getName().equals("model.User")) {
						view.Log(
								"1.Crear Productos \n2.Ver Productos \n3.Historial de ventas" +
								"\n4. \n5. Cerrar Sesión\n");
						do {	
							try {
								option = view.readNumber();
							} catch (NumberFormatException e) {
								view.Log("Digita un número entero válido");
								option = -1;
								break;
							}
							
							switch(option) {
								case 1:
									view.Log("Nombre del producto");
									String _name = view.readData();
									
									view.Log("Categoria");
									int _category = view.readNumber();
									
									view.Log("Precio");
									int _price = view.readNumber();
									
									view.Log("Cantidad");
									int _quantity = view.readNumber();

									view.Log("Tiempo estimado de entrega (en dias)");
									int deliveryDate = view.readNumber();

									((Seller) currentUser).addProduct(new Product(_name, ((Seller)currentUser), _category, _price, _quantity, deliveryDate));
									
									view.Log("El Producto ha sido creado y añadido a su catalogo!");
									break;
								
								case 2:
									for(int i = 0; i < ((Seller)currentUser).getProducts().length; i++) {
										view.Log((i + 1) + ". " + ((Seller)currentUser).getProducts()[i].getName() + 
												"\nCategoria: " + ((Seller)currentUser).getProducts()[i].getCategory() +
												"\nCantidad: " + ((Seller)currentUser).getProducts()[i].getQuantity() +
												"\nPrecio: " + ((Seller)currentUser).getProducts()[i].getPrice() + "\n ");
									}
									
									view.Log("Desea editar un producto? \n0.No \n1.Si");
									int _op;
									try {
										_op = view.readNumber();
									} catch (NumberFormatException e) {
										view.Log("Digita un número entero válido");
										_op = -1;
										break;
									}
									
										if(_op == 1) {
											view.Log("Seleccione el producto a modificar");
											try {
												option = view.readNumber();
											} catch (NumberFormatException e) {
												view.Log("Digita un número entero válido");
												option = -1;
												break;
											}
											view.Log("Nuevo Nombre");
											String _newName = view.readData();
											view.Log("Nuevo Nombre");
											int _newCategory = view.readNumber();
											view.Log("Nuevo Nombre");
											int _newPrice = view.readNumber();
											view.Log("Nuevo Nombre");
											int _newQuantity = view.readNumber();
											
											((Seller)currentUser).getProducts()[option].edit(_newName, _newCategory, _newPrice, _newQuantity);
										}
									break;
								
								case 3:
									view.Log("Historial de ventas:");
									for(int i = 0; i < ((Seller)currentUser).getSalesHistory().length; i++) {
										view.Log("Orden: " + (i + 1)+ ". " + ((Seller)currentUser).getSalesHistory()[i].getUser().getName() + "\n Salida: " +
												((Seller)currentUser).getSalesHistory()[i].getStartingDate() + "\n Llega el: " +
												((Seller)currentUser).getSalesHistory()[i].getShipmentDate() + "\n Lugar: " +
												((Seller)currentUser).getSalesHistory()[i].getShipmentPlace() + "\n Productos:");
										for(int j = 0; j < ((Seller)currentUser).getSalesHistory()[i].getProducts().length; j++) {
											view.Log(((Seller)currentUser).getSalesHistory()[i].getProducts()[j].getName() + " : $" +
													((Seller)currentUser).getSalesHistory()[i].getProducts()[j].getPrice());
										}
										view.Log("Estado: " + ((Seller)currentUser).getSalesHistory()[i].getStatus());
										view.Log("Total: $" + ((Seller)currentUser).getSalesHistory()[i].calculateTotalPrice());
									}
									
									view.Log("Desea cambiar el estado de alguna venta? \n0.No \n1.Si");
									try {
										_op = view.readNumber();
									} catch (NumberFormatException e) {
										view.Log("Digita un número entero válido");
										_op = -1;
										break;
									}
									
									if(_op == 1) {
										view.Log("Seleccione la venta a modificar");
										try {
											_op = view.readNumber();
										} catch (NumberFormatException e) {
											view.Log("Digita un número entero válido");
											_op = -1;
											break;
										}
										int order = _op;
										view.Log("Seleccione el nuevo estado \n"
												+ "1.Compra Verificada\n"
												+ "2.El vendedor envió el producto \n" 
												+ "3.El producto llegó a una estación de reparto \n"
												+ "4.El producto ha salido de una estación de reparto \n"
												+ "5.El producto ha sido transferido a un repartidor local \n"
												+ "6.El producto ha llegado a un centro de entrega local \n"
												+ "7.El producto llegará hoy \n"
												+ "8.El producto está listo para recoger en un centro de entrega local \n"
												+ "9.El producto ha sido entregado \n"
												+ "10.Se está procesando la cancelación de la compra \n"
												+ "11.La compra no se ha podido cancelar \n"
												+ "12.La compra se ha cancelado \n"
												+ "13.Pendiente de llegada para su devolución \n"
												+ "14.El producto no ha llegado en las fechas establecidas, la devolución ha sido cancelada \n"
												+ "15.Se está procesando la devolución del producto \n"
												+ "16.No se ha aprobado la devolución, estamos procesando el envío de su producto \n"
												+ "17.La devolución ha sido aprobada, su dinero ha sido devuelto \n"
												+ "18.La devolución ha sido aprobada, estamos procesando el envío de un nuevo producto \n"
												+ "19.Un reemplazo ha sido enviado \n"
												+ "20.Ha ocurrido un retraso en el envío de su paquete \n"
												+ "21.El producto no se ha podido entregar \n");
										
										option = view.readNumber();
										calculator.changeShipmentStatus(((Seller)currentUser).getOrderHistory()[order], option);
									}
									break;
							}
						}while(option != 5);
					}
				}
			
			case 2:
				view.Log("Eres Cliente o Vendedor? \n1.Soy Cliente \n2.Soy Vendedor");
				int _op = view.readNumber();
				
				view.Log("Ingrese nuevo usuario");
				String newUser;
				boolean isUnique = false;
				do{
				newUser = view.readData();
				isUnique = calculator.isUniqueUser(newUser);
					view.Log(isUnique ? "True" : "False");
				if(!isUnique){
					view.Log("Este usuario ya existe");
				}
				}while (!isUnique);
				
				view.Log("Ingrese nueva contraseña");
				String newPass = view.readData();

				view.Log("Ingrese la direccion");
				String newDir = view.readData();

				switch (_op) {
					case 1:
						calculator.createUser(Type.USER, newDir, newUser, newPass);
					break;

					case 2:
						calculator.createUser(Type.SELLER, newDir, newUser, newPass);
					break;
				}

				view.Log("El Usuario ha sido creado Exitosamente! \n");
				break;
			
			}
		} while (option != 0);
	}

	public static void main(String[] args) {
		Presenter presenter = new Presenter();

		presenter.run();
	}
}