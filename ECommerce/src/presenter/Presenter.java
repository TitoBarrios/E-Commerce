package presenter;

import model.Calculator;
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
		do {
			view.showMessage("Bienvenido a nuestro E-Commerce\n1. Iniciar Sesión\n2. Registrarme\n3. Soy un vendedor\n0. Salir");
			try {
				option = view.readNumber();
			} catch(NumberFormatException e) {
				view.showMessage("Digita un número entero válido");
				option = -1;
				break;
			}
			switch(option) {
			case 1: view.showMessage("Digita tu nombre de usuario");
			String userName = view.readData();
			view.showMessage("Digita tu contraseña");
			String password = view.readData();
			if(calculator.logInUser(userName, password)) {
				view.showMessage("1. Categorias\n2. Mi carrito\n3. Historial de Compras\n4. Ver mi Perfil\n5. Cerrar Sesión\n");
				try {
					option = view.readNumber();
				} catch(NumberFormatException e) {
					view.showMessage("Digita un número entero válido");
					option = -1;
					break;
				}
				switch(option) {
				case 1: view.showMessage("Seleccione la categoría que más le llame la atención");
				for(int i = 0; i < calculator.getDataBase().getProducts().length; i++) {
					switch(i) {
					case 0: view.showMessage("1. Arte");
						break;
						
					case 1: view.showMessage("2. Computadoras");
						break;
						
					case 2: view.showMessage("3. Deportes");
						break;
						
					case 3: view.showMessage("4. Electrónicos");
						break;
						
					case 4: view.showMessage("5. Juguetes");
						break;
						
					case 5: view.showMessage("6. Hogar");
						break;
						
					case 6: view.showMessage("7. Mascotas");
						break;
						
					case 7: view.showMessage("8. Moda");
						break;
						
					case 8: view.showMessage("9. Películas");
						break;
						
					case 9: view.showMessage("10. Salud");
						break;
					}
				}
				try {
					category = view.readNumber() - 1;
				} catch(NumberFormatException e) {
					view.showMessage("Digita un número entero válido");
					option = -1;
					break;
				}
				for(int i = 0; i < calculator.getDataBase().getProducts()[category].length; i++) {
					if(calculator.getDataBase().getProducts()[category][i] != null) {
						view.showMessage(calculator.getDataBase().getProducts()[category][i].getName() + " " + calculator.getDataBase().getProducts()[category][i].getPrice());
					}
				}
					break;
				case 2:
					break;
				case 3: 
					break;
				case 4:
					break;
				case 5: option = -1;
					view.showMessage("Se ha cerrado sesión de forma exitosa\n");
					break;
				}
			}
				break;
			case 2:
				break;
			}
		} while(option != 0);
	}

	public static void main(String[] args) {
		Presenter presenter = new Presenter();

		presenter.run();
	}
}