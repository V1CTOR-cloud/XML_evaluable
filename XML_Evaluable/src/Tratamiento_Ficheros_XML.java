import org.w3c.dom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;

public class Tratamiento_Ficheros_XML {
	
	public static class ListaCanciones{
		private List <Cancion> lista = new ArrayList <Cancion>();
		public ListaCanciones() {}
		
		public void anyadirCancion(Cancion can) {
			lista.add(can);
		}
		
		public List<Cancion> getListaCanciones(){
			return lista;
		}
		
	}
	
	public static class Cancion{
		private String id, titulo, artista, anyo, formato;
		
		public Cancion(String id, String titulo, String artista, String anyo, String formato){
			this.id = id;
			this.titulo = titulo;
			this.artista = artista;
			this.anyo = anyo;
			this.formato= formato;
		}
		
		public String getId() {
			return id;
		}		
		public String getTitulo() {
			return titulo;
		}
		public String getArtista() {
			return artista;
		}
		public String getAnyo() {
			return anyo;
		}
		public String getFormato() {
			return formato;
		}
		
		
		
	}
	

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("canciones.xml"));
			
			Element raiz = document.getDocumentElement();
			System.out.println("Contenido XML:\t" + raiz.getNodeName()+ ":");
			NodeList nodelist = document.getElementsByTagName("cancion");
			
			for(int i = 0; i < nodelist.getLength(); i++) {
				Node node = nodelist.item(i);
				Element eElement = (Element) node;
				System.out.println("-------------------------------------------");
				System.out.println("ID cancion:\t\t" + eElement.getAttribute("id"));
				System.out.println("Titulo:\t\t\t" + eElement.getElementsByTagName("titulo").item(0).getTextContent());
				System.out.println("Artista:\t\t" + eElement.getElementsByTagName("artista").item(0).getTextContent());
				System.out.println("Año:\t\t\t" + eElement.getElementsByTagName("anyo").item(0).getTextContent());
				System.out.println("Formato:\t\t" + eElement.getElementsByTagName("formato").item(0).getTextContent());
				System.out.println("-------------------------------------------");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
