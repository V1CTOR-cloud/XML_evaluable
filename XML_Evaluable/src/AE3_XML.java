import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

public class AE3_XML {

	public static void main(String[] args) { // ejecuta el programa mediante un menu
		boolean salir = false;
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		Biblioteca b = new Biblioteca();
		System.out.println("// ------------- Benvingut a Florida's Library ------------- //");
		System.out.println("1. => Mostrar biblioteca");
		System.out.println("2. => Consultar llibre");
		System.out.println("3. => Crear nou llibre");
		System.out.println("4. => Actualizar llibre");
		System.out.println("5. => Borrar llibre");
		System.out.println("6. => Tancar Biblioteca");
		System.out.println("// ------------------- Florida's Library ------------------- //");
		do {
			System.out.println(">>>> Tria una opcio(1..6) <<<<");
			opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				b.MostrarLlibres();
				break;
			case 2:
				b.ConsultarLlibre();
				break;
			case 3:
				Llibre l = new Llibre();
				try {
					b.CrearLLibre();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}
				break;
			case 4:
				try {
					b.EditarLlibre();
				} catch (ParserConfigurationException e1) {
					e1.printStackTrace();
				}
				break;
			case 5:
				try {
					b.BorrarLlibre();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				}
				break;
			case 6:
				salir = true;
				System.out.println("\n\n\n\n/-------------------------/");
				System.out.println("Esperem que pase un bon dia");
				System.out.println("/-------------------------/");
				break;

			default:
				System.err.println("\n\n\n\n/---------------------------/");
				System.err.println("La opcio triada es incorrecta");
				System.err.println("/---------------------------/");
				break;
			}
		} while (!salir);
	}
}
