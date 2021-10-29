import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Biblioteca { // Contiene una lista de libros, sobre la que trabajaremos en todo el programa
	private List<Llibre> lista = new ArrayList<Llibre>(); // privada para la encapsulacion
	static int idUltimo = 0;

	public Biblioteca() { // En el constructor cargamos la lista desde el XML por posibles errores
		CargarLlibres();
	}

	public void AnyadirLibro(Llibre libro) { // metodo para añadir libros
		lista.add(libro);
	}

	public void LimpiarLista() { // limpia la lista
		lista.clear();
	}

	public List<Llibre> getListaLibros() { // getter de la lista
		return lista;
	}

	public void CargarLlibres() { // carga la lista desde el XML
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("Biblioteca2.xml"));

			Element raiz = document.getDocumentElement();
			NodeList nodelist = document.getElementsByTagName("Llibre");
			LimpiarLista(); // Borramos la lista por si hay duplicidad de elementos
			for (int i = 0; i < nodelist.getLength(); i++) {
				Node node = nodelist.item(i);
				Element eElement = (Element) node;
				String id = eElement.getAttribute("id");
				String titulo = eElement.getElementsByTagName("titulo").item(0).getTextContent();
				String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
				String anyo = eElement.getElementsByTagName("anyo_publicacion").item(0).getTextContent();
				String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
				String paginas = eElement.getElementsByTagName("paginas").item(0).getTextContent();
				Llibre libro = new Llibre(id, titulo, autor, anyo, editorial, paginas);
				AnyadirLibro(libro);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void MostrarLlibres() { // muestra y carga la lista
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("Biblioteca2.xml"));

			Element raiz = document.getDocumentElement();
			System.out.println("\nContenido XML:\t" + raiz.getNodeName() + ":");
			NodeList nodelist = document.getElementsByTagName("Llibre");
			LimpiarLista();// Borramos la lista por si hay duplicidad de elementos
			for (int i = 0; i < nodelist.getLength(); i++) {
				Node node = nodelist.item(i);
				Element eElement = (Element) node;
				String id = eElement.getAttribute("id");
				System.out.println("\n_____________________________________________");
				System.out.println("ID llibre:\t\t" + eElement.getAttribute("id"));
				System.out.println("Titulo:\t\t\t" + eElement.getElementsByTagName("titulo").item(0).getTextContent());
				String titulo = eElement.getElementsByTagName("titulo").item(0).getTextContent();
				String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
				String anyo = eElement.getElementsByTagName("anyo_publicacion").item(0).getTextContent();
				String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
				String paginas = eElement.getElementsByTagName("paginas").item(0).getTextContent();
				System.out.println("_____________________________________________");
				Llibre libro = new Llibre(id, titulo, autor, anyo, editorial, paginas);
				AnyadirLibro(libro);
				idUltimo = Integer.parseInt(id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ConsultarLlibre() { // mediante busqueda de id mostraremos la info del libro
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("Biblioteca2.xml"));

			Element raiz = document.getDocumentElement();
			NodeList nodelist = document.getElementsByTagName("Llibre");

			Scanner sc = new Scanner(System.in);

			int busqueda = 0;
			System.out.println("// ------------- Busqueda ------------- //");
			System.out.println("Introdueix la id:\t");
			System.out.println("// ------------- Busqueda ------------- //");
			busqueda = sc.nextInt();
			for (int i = 0; i < nodelist.getLength(); i++) {
				Node node = nodelist.item(i);
				Element eElement = (Element) node;
				String id = eElement.getAttribute("id");

				if (busqueda == Integer.parseInt(id)) { // id a buscar == a el id del XML
					System.out.println("// ------------- Informació ------------- //");
					System.out.println("ID llibre:\t\t" + eElement.getAttribute("id"));
					System.out.println(
							"Titulo:\t\t\t" + eElement.getElementsByTagName("titulo").item(0).getTextContent());
					System.out
							.println("Autor:\t\t\t" + eElement.getElementsByTagName("autor").item(0).getTextContent());
					System.out.println("Any de publicació:\t"
							+ eElement.getElementsByTagName("anyo_publicacion").item(0).getTextContent());
					System.out.println(
							"Editorial:\t\t" + eElement.getElementsByTagName("editorial").item(0).getTextContent());
					System.out.println(
							"Pagines:\t\t" + eElement.getElementsByTagName("paginas").item(0).getTextContent());
					System.out.println("// ------------- Informació ------------- //");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void BorrarLlibre() throws ParserConfigurationException { // mediante una id borraremos el libro
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("Biblioteca2.xml"));

			Element raiz = document.getDocumentElement();
			NodeList nodelist = document.getElementsByTagName("Llibre");

			Scanner sc = new Scanner(System.in);
			LimpiarLista();// Borramos la lista por si hay duplicidad de elementos
			int busqueda = 0;
			String pregunta = "";
			System.out.println("// ------------- Borrar ------------- //");
			System.out.println("Introdueix la id:\t");
			System.out.println("// ------------- Borrar ------------- //");
			busqueda = sc.nextInt();

			System.out.println("// ------------- Borrar confirmacio ------------- //");
			System.out.println("Está segur de continuar amb l'operacio (s/n):\t");
			System.out.println("// ------------- Borrar confirmacio ------------- //");
			pregunta = sc.next();

			if (pregunta.equals("s")) {
				for (int i = 0; i < nodelist.getLength(); i++) {
					Node node = nodelist.item(i);
					Element eElement = (Element) node;
					String id = eElement.getAttribute("id");

					if (busqueda != Integer.parseInt(id)) { // si el id es distinto al que buscamos cargamos toda la
															// lista
															// evitando así el elemento que queremos borrar, de esta
															// forma se borra
						System.out.println("\n_____________________________________________");
						System.out.println("ID llibre:\t\t" + eElement.getAttribute("id"));
						System.out.println(
								"Titulo:\t\t\t" + eElement.getElementsByTagName("titulo").item(0).getTextContent());
						String titulo = eElement.getElementsByTagName("titulo").item(0).getTextContent();
						String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
						String anyo = eElement.getElementsByTagName("anyo_publicacion").item(0).getTextContent();
						String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
						String paginas = eElement.getElementsByTagName("paginas").item(0).getTextContent();
						System.out.println("_____________________________________________");
						Llibre libro = new Llibre(id, titulo, autor, anyo, editorial, paginas);
						AnyadirLibro(libro);
						idUltimo = Integer.parseInt(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeXmlFile(getListaLibros()); // grabamos la lista en el xml
	}

	public void EditarLlibre() throws ParserConfigurationException { // edita mediante una id
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("Biblioteca2.xml"));

			Element raiz = document.getDocumentElement();
			NodeList nodelist = document.getElementsByTagName("Llibre");

			Scanner sc = new Scanner(System.in);
			int busqueda = 0;
			String pregunta = "";
			System.out.println("// ------------- Editar ------------- //");
			System.out.println("Introdueix la id:\t");
			System.out.println("// ------------- Editar ------------- //");
			busqueda = sc.nextInt();

			System.out.println("// ------------- Editar confirmacio ------------- //");
			System.out.println("Está segur de continuar amb l'operacio (s/n):\t");
			System.out.println("// ------------- Editar confirmacio ------------- //");
			pregunta = sc.next();

			if (pregunta.equals("s")) {
				for (int i = 0; i < nodelist.getLength(); i++) {
					Node node = nodelist.item(i);
					Element eElement = (Element) node;
					String id = eElement.getAttribute("id");

					if (busqueda == Integer.parseInt(id)) { // Si el id es el mismo que el que buscamos
															// creamos un libro con nuevos parametros
						System.out.println("Introduccio de dades:\t");
						System.out.println("/ ------------------- /");
						System.out.println("Títol:\t");
						String titulo = sc.nextLine();
						titulo = sc.nextLine(); // el escaner a veces falla, por si acaso
						System.out.println("Autor:\t");
						String autor = sc.nextLine();
						System.out.println("Any de Publicacio:\t");
						String anyo = sc.nextLine();
						System.out.println("Editorial:\t");
						String editorial = sc.nextLine();
						System.out.println("Pagines:\t");
						String paginas = sc.nextLine();
						System.out.println("/ ------------------- /");

						Llibre l = new Llibre(id, titulo, autor, anyo, editorial, paginas);
						lista.set(Integer.parseInt(id) - 1, l); // reemplazamos y el id -1 por el indice de arrays
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeXmlFile(getListaLibros());
	}

	public void CrearLLibre() throws ParserConfigurationException { // creamos un libro con una id autoincremental
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Anyadir un Llibre (s/n)?");
		try {
			String respuesta = sc.nextLine();
			if (respuesta == respuesta.toUpperCase() || respuesta == respuesta.toLowerCase()) { // Comprobamos si es
																								// minus o mayus
				if (respuesta.length() == 1) { // comprobar que solo se ha escrito un caracter
					while (respuesta.equals("s")) {
						System.out.println("Introduccio de dades:\t");
						System.out.println("/ ------------------- /");
						System.out.println("Títol:\t");
						String titulo = sc.nextLine();
						System.out.println("Autor:\t");
						String autor = sc.nextLine();
						System.out.println("Any de Publicacio:\t");
						String anyo = sc.nextLine();
						System.out.println("Editorial:\t");
						String editorial = sc.nextLine();
						System.out.println("Pagines:\t");
						String paginas = sc.nextLine();
						System.out.println("/ ------------------- /");
						idUltimo++;
						Llibre l = new Llibre(Integer.toString(idUltimo), titulo, autor, anyo, editorial, paginas);
						AnyadirLibro(l);
						System.out.println("¿Anyadir un nou Llibre (s/n)?"); // vuelta o salida del bucle
						respuesta = sc.nextLine();
					}
				} else {
					System.err.println("Error has introduit mes de un caracter");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeXmlFile(getListaLibros());
	}

	public void writeXmlFile(List<Llibre> list) throws ParserConfigurationException { // graba la lista y escribe el XML
		try {
			DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = dFact.newDocumentBuilder();
			Document doc = build.newDocument();

			Element raiz = doc.createElement("Biblioteca");
			doc.appendChild(raiz);

			for (Llibre l : list) {
				Element libro = doc.createElement("Llibre");
				String id = String.valueOf(l.getId());
				libro.setAttribute("id", id);
				raiz.appendChild(libro);

				Element titulo = doc.createElement("titulo");
				titulo.appendChild(doc.createTextNode(String.valueOf(l.getTitulo())));
				libro.appendChild(titulo);

				Element autor = doc.createElement("autor");
				autor.appendChild(doc.createTextNode(String.valueOf(l.getAutor())));
				libro.appendChild(autor);

				Element anyo = doc.createElement("anyo_publicacion");
				anyo.appendChild(doc.createTextNode(String.valueOf(l.getAnyo())));
				libro.appendChild(anyo);

				Element editorial = doc.createElement("editorial");
				editorial.appendChild(doc.createTextNode(String.valueOf(l.getEditorial())));
				libro.appendChild(editorial);

				Element paginas = doc.createElement("paginas");
				paginas.appendChild(doc.createTextNode(String.valueOf(l.getPaginas())));
				libro.appendChild(paginas);
			}

			TransformerFactory tfF = TransformerFactory.newInstance();
			Transformer atrans = tfF.newTransformer();

			atrans.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			atrans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			atrans.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			try {
				FileWriter fw = new FileWriter("Biblioteca2.xml");
				StreamResult resultado = new StreamResult(fw);
				atrans.transform(source, resultado);
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (TransformerException ex) {
			System.err.println("Error escribint el arxiu");
		}
	}

}
